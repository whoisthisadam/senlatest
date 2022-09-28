package com.kasperovich.store.repository;

import com.kasperovich.store.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends JpaRepository<Product, Long>, CrudRepository<Product, Long> {
}
