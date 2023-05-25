# Микросервисный проект - Доставка еды "Голодный волк".

[![Java CI](https://github.com/PerpetuumEbner/job4j_order/actions/workflows/maven.yml/badge.svg)](https://github.com/PerpetuumEbner/job4j_order/actions/workflows/maven.yml)

## Общее описание:

Заказ блюд на дом. Блок кухня.
* [Блок заказов](https://github.com/PerpetuumEbner/job4j_order)
* [Блок блюда](https://github.com/PerpetuumEbner/job4j_dish)
* [Блок уведомления](https://github.com/PerpetuumEbner/job4j_notification)

***

## Реализовано:

* Принятие заказа
* Приготовление блюда
* Изменение статуса заказа

***

## Технологии:

[![java](https://img.shields.io/badge/java-17-red)](https://www.java.com/)
[![maven](https://img.shields.io/badge/apache--maven-3.8.3-blue)](https://maven.apache.org/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.6.0-brightgreen)](https://spring.io/projects/spring-boot)
[![PostgresSQL](https://img.shields.io/badge/PostgreSQL-15-blue)](https://www.postgresql.org/)
[![Lombok](https://img.shields.io/badge/Lombok-1.18.26-red)](https://projectlombok.org/)
[![Liquibase](https://img.shields.io/badge/Liquibase-4.17.2-orange)](https://www.liquibase.org/)
[![Apache Kafka](https://img.shields.io/badge/Kafka-3.4.0-%20%23000000)](https://kafka.apache.org/)

***

## Запуск проекта:

* создать базу данных `kitchen`
* `maven install`
* `java -jar target/kitchen-0.0.1-SNAPSHOT.jar`

***

## Структура проекта:

### Принятие заказа
Кухня принимает заказ через брокер сообщений.

![1](img/1.jpg)

### Приготовление блюда
Кухня может приготовить блюдо, до тех пор, пока есть для него продукты. Для каждого блюда существуют свои ингредиенты,
их количество уменьшается. Если ингредиенты закончились, то блюдо не может быт приготовлено.

### Изменение статуса заказа
После приготовления статус заказа меняется на "Собран" через брокер сообщений. Если блюдо не может быть приготовлено,
то статус меняется на "Отменён".