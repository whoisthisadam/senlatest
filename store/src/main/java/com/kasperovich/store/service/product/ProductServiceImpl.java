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
import java.util.Objects;
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
//        return productRepository.findAll().stream().map(x -> {
//            if(x.getIsDeleted().equals(false)) {
//                return new ProductDTO(x.getId(), x.getName(), x.getPrice(), x.getProductStatus(), x.getCreatedAt(), x.getIsDeleted());
//            }
//        }).collect(Collectors.toList());

        List<ProductDTO> list=new ArrayList<>();
        productRepository.findAll().forEach(x->{
            if(x.getIsDeleted().equals(false)){
                list.add(new ProductDTO(x.getId(), x.getName(), x.getPrice(), x.getProductStatus(), x.getCreatedAt(), x.getIsDeleted()));
            }
        });
        return list;
    }

    @Override
    public Product updateProduct(ProductDTO productDto) {
        return productRepository.save(new Product(productDto.getId(),productDto.getName(),productDto.getPrice(),
                productDto.getProductStatus(),productDto.getCreatedAt(), productDto.getIsDeleted()));
    }

    @Override
    public void deleteProduct(Long id) {
        List<Product> list=productRepository.findAll();
        list.forEach(x->{if(Objects.equals(x.getId(), id)){
            Product product=new Product(x.getId(),x.getName(),x.getPrice(),x.getProductStatus(),x.getCreatedAt(),true);
            productRepository.save(product);
        }});
    }
}

