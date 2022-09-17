package com.kasperovich.store.service.product;

import com.kasperovich.store.dto.ProductDTO;
import com.kasperovich.store.model.Product;
import com.kasperovich.store.repository.OrderRepository;
import com.kasperovich.store.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Override
    public Product createProduct(Product product) {
        return productRepository.saveAndFlush(product);
    }

}

