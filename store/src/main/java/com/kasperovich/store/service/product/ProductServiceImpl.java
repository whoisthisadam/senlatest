package com.kasperovich.store.service.product;

import com.kasperovich.store.dto.ProductDTO;
import com.kasperovich.store.enums.ProductStatus;
import com.kasperovich.store.excepion.NotPossibleToDeleteProductException;
import com.kasperovich.store.model.Order;
import com.kasperovich.store.model.Product;
import com.kasperovich.store.repository.OrderRepository;
import com.kasperovich.store.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Override
    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public List<Product> findAll() {
        List<Product> list=new ArrayList<>();
        productRepository.findAll().forEach(x->{
            if(x.getIsDeleted().equals(false)){
                list.add(new Product(x.getId(), x.getName(), x.getPrice(), x.getProductStatus(), x.getCreatedAt(), x.getIsDeleted(),x.getOrderSet()));
            }
        });
        return list;
    }

    @Override
    public Product updateProduct(Long id, ProductDTO productDto) {
        Product product=new Product();
        product.setId(id);
        product.setName(Optional.ofNullable(productDto.getName()).orElse(productRepository.findById(id).get().getName()));
        product.setPrice(Optional.ofNullable(productDto.getPrice()).orElse(productRepository.findById(id).get().getPrice()));
        product.setProductStatus(Optional.ofNullable(productDto.getProductStatus()).orElse(productRepository.findById(id).get().getProductStatus()));
        product.setCreatedAt(productRepository.findById(product.getId()).orElse(null).getCreatedAt());
        product.setIsDeleted(false);
        return productRepository.save(product);
    }

    @Override
    public void deleteProduct(Long id) throws NotPossibleToDeleteProductException {
        List<Product> list=productRepository.findAll();
        list.forEach(x->{if(Objects.equals(x.getId(), id)){
            Product product=new Product(x.getId(),x.getName(),x.getPrice(),x.getProductStatus(),x.getCreatedAt(),true,x.getOrderSet());
            if(product.getProductStatus()!=ProductStatus.OUT_OF_STOCK) throw new NotPossibleToDeleteProductException();
            productRepository.save(product);
        }});
    }
}

