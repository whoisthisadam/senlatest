package com.kasperovich.store.service.product;

import com.kasperovich.store.dto.ProductPOSTDto;
import com.kasperovich.store.dto.product.ProductGETDto;
import com.kasperovich.store.enums.ProductStatus;
import com.kasperovich.store.excepion.NotPossibleToDeleteProductException;
import com.kasperovich.store.model.Product;
import com.kasperovich.store.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Override
    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public List<ProductGETDto> findAll() {
        List<ProductGETDto> list=new ArrayList<>();
        productRepository.findAll(Sort.by("orders")).forEach(x->{
            if(x.getIsDeleted().equals(false)){
                list.add(new ProductGETDto(x.getName(),x.getPrice(), (long) x.getOrders().size()));
            }
        });
        return list;
    }

    @Override
    public Product updateProduct(Long id, ProductPOSTDto productDto) {
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
            Product product=new Product(x.getId(),x.getName(),x.getPrice(),x.getProductStatus(),x.getCreatedAt(),true, x.getOrders());
            if(product.getProductStatus()!=ProductStatus.OUT_OF_STOCK) throw new NotPossibleToDeleteProductException();
            productRepository.save(product);
        }});
    }
}

