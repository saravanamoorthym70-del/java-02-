CREATE DATABASE IF NOT EXISTS library_management;
USE library_management;

CREATE TABLE IF NOT EXISTS author (
    id BIGINT NOT NULL AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255),
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS book (
    id BIGINT NOT NULL AUTO_INCREMENT,
    title VARCHAR(255),
    author_id BIGINT,
    price FLOAT NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT fk_book_author FOREIGN KEY (author_id) REFERENCES author(id)
);

CREATE TABLE IF NOT EXISTS price (
    id BIGINT NOT NULL AUTO_INCREMENT,
    amount DECIMAL(10, 2) NOT NULL,
    currency VARCHAR(3) NOT NULL DEFAULT 'INR',
    PRIMARY KEY (id)
);

INSERT INTO price (amount, currency)
SELECT 299.00, 'INR'
WHERE NOT EXISTS (SELECT 1 FROM price WHERE amount = 299.00 AND currency = 'INR');

INSERT INTO price (amount, currency)
SELECT 499.00, 'INR'
WHERE NOT EXISTS (SELECT 1 FROM price WHERE amount = 499.00 AND currency = 'INR');

INSERT INTO price (amount, currency)
SELECT 799.00, 'INR'
WHERE NOT EXISTS (SELECT 1 FROM price WHERE amount = 799.00 AND currency = 'INR');
