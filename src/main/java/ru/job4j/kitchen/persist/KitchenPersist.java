package ru.job4j.kitchen.persist;

import org.springframework.data.repository.CrudRepository;
import ru.job4j.kitchen.model.Kitchen;

public interface KitchenPersist extends CrudRepository<Kitchen, Integer> {
}