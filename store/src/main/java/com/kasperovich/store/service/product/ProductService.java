package com.kasperovich.store.service.product;

import com.kasperovich.store.dto.ProductDTO;
import com.kasperovich.store.model.Product;

import java.util.List;

public interface ProductService {
    Product createProduct(Product product);

    List<Product> findAll();

    Product updateProduct(Long id, ProductDTO productDto);

    void deleteProduct(Long id);
}
