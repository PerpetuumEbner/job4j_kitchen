package ru.job4j.kitchen.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import ru.job4j.kitchen.dto.OrderDTO;
import ru.job4j.kitchen.model.Order;

@Service
@AllArgsConstructor
@Slf4j
public class KitchenService {
    @KafkaListener(topics = "job4j_orders")
    public void receiveOrder(OrderDTO orderDTO) {
        log.debug(orderDTO.toString());
    }
}