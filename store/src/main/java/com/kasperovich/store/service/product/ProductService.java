package com.kasperovich.store.service.product;

import com.kasperovich.store.dto.ProductDTO;
import com.kasperovich.store.model.Product;

public interface ProductService {
    Product createProduct(Product product);
}
