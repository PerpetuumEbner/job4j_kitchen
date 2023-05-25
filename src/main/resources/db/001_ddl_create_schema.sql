CREATE TABLE IF NOT EXISTS kitchen
(
    id    SERIAL PRIMARY KEY,
    state TEXT NOT NULL
);

CREATE TABLE IF NOT EXISTS products
(
    id    SERIAL PRIMARY KEY,
    name  TEXT NOT NULL UNIQUE,
    count INT  NOT NULL
);

CREATE TABLE IF NOT EXISTS dishes
(
    id   SERIAL PRIMARY KEY,
    name TEXT NOT NULL UNIQUE
);

CREATE TABLE IF NOT EXISTS products_dishes
(
    id          SERIAL PRIMARY KEY,
    products_id INT NOT NULL REFERENCES products (id),
    dishes_id   INT NOT NULL REFERENCES dishes (id)
)