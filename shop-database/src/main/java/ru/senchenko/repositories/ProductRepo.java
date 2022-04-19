package ru.senchenko.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.senchenko.entities.Product;

public interface ProductRepo extends JpaRepository<Product, Integer> {

    Product findProductById(Integer id);
}
