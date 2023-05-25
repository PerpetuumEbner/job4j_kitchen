package ru.job4j.kitchen.persist;

import org.springframework.data.repository.CrudRepository;
import ru.job4j.kitchen.model.Products;

public interface ProductsPersist extends CrudRepository<Products, Integer> {
}