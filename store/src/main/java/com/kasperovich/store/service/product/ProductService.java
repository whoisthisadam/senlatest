package com.kasperovich.store.service.product;

import com.kasperovich.store.dto.ProductPOSTDto;
import com.kasperovich.store.dto.product.ProductGETDto;
import com.kasperovich.store.model.Product;

import java.util.List;

public interface ProductService {
    Product createProduct(Product product);

    List<ProductGETDto> findAll();

    Product updateProduct(Long id, ProductPOSTDto productDto);

    void deleteProduct(Long id);
}
