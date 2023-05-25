package ru.job4j.kitchen.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.job4j.kitchen.dto.DishDTO;
import ru.job4j.kitchen.dto.OrderDTO;
import ru.job4j.kitchen.dto.ProductsDTO;
import ru.job4j.kitchen.model.Dish;
import ru.job4j.kitchen.model.Kitchen;
import ru.job4j.kitchen.model.Products;
import ru.job4j.kitchen.persist.KitchenPersist;
import ru.job4j.kitchen.persist.ProductsDTOPersist;
import ru.job4j.kitchen.persist.ProductsPersist;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class KitchenService {
    private final KitchenPersist kitchenPersist;

    private final KafkaTemplate<String, Object> kafkaTemplate;

    private final ProductsPersist productsPersist;

    private final ProductsDTOPersist productsDTOPersist;

    private final ModelMapper modelMapper;

    @Transactional
    @KafkaListener(topics = "preorder")
    public void receiveOrder(OrderDTO orderDTO) {
        log.debug(orderDTO.toString());
        if (cooking(orderDTO)) {
            kitchenBuilder(
                    orderDTO,
                    "Заказ № " + orderDTO.getId() + " Готов к выдаче."
            );
            orderDTO.setStatus("Собран");

        } else {
            kitchenBuilder(
                    orderDTO,
                    "Заказ № " + orderDTO.getId() + " не может быть выполнен."
            );
            orderDTO.setStatus("Отменён");
        }
        kafkaTemplate.send("cooked_order", orderDTO);
    }

    public void kitchenBuilder(OrderDTO orderDTO, String status) {
        kitchenPersist.save(
                Kitchen.builder()
                        .id(orderDTO.getId())
                        .state(status)
                        .build()
        );
    }

    @Transactional
    public boolean cooking(OrderDTO orderDTO) {
        boolean result = false;
        List<DishDTO> dishList = orderDTO.getDishes();
        List<Products> productsList = (List<Products>) productsPersist.findAll();
        for (DishDTO dishDTO : dishList) {
            for (Products products : productsList) {
                List<Dish> dishes = products.getDishes();
                for (Dish dish : dishes) {
                    if (dishDTO.getName().equals(dish.getName())) {
                        int count = products.getCount();
                        if (count == 0) {
                            result = false;
                            break;
                        } else {
                            ProductsDTO productsDTO = convertToProductsDTO(products);
                            productsDTO.setCount(count - 1);
                            productsDTOPersist.save(productsDTO);
                            result = true;
                        }
                    }
                }
            }
        }
        return result;
    }

    public ProductsDTO convertToProductsDTO(Products products) {
        return modelMapper.map(products, ProductsDTO.class);
    }
}