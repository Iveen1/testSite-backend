package ru.iveen.testsite.domain.repository;

import ru.iveen.testsite.domain.entity.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
