package ru.job4j.kitchen.persist;

import org.springframework.data.repository.CrudRepository;
import ru.job4j.kitchen.dto.ProductsDTO;

public interface ProductsDTOPersist extends CrudRepository<ProductsDTO, Integer> {
}