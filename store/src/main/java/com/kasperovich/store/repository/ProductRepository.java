package com.kasperovich.store.repository;

import com.kasperovich.store.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> {
}
