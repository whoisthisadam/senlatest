package com.kasperovich.store.service.product;

import com.kasperovich.store.dto.ProductDTO;
import com.kasperovich.store.enums.ProductStatus;
import com.kasperovich.store.model.Product;
import com.kasperovich.store.repository.OrderRepository;
import com.kasperovich.store.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Override
    public Product createProduct(Product product) {
        product.setProductStatus(ProductStatus.values()[new Random().nextInt(3)]);
        return productRepository.save(product);
    }

    @Override
    public List<ProductDTO> findAll() {
        return productRepository.findAll().stream().map(x -> {
            return new ProductDTO(x.getId(), x.getName(), x.getPrice(), x.getProductStatus(), x.getCreatedAt());
        }).collect(Collectors.toList());
    }
}

