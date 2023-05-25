package ru.job4j.kitchen.persist;

import org.springframework.data.repository.CrudRepository;
import ru.job4j.kitchen.model.Dish;

public interface DishesPersist extends CrudRepository<Dish, Integer> {
}