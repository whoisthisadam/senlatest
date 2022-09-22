package com.kasperovich.store.controller;
import java.sql.Timestamp;
import com.kasperovich.store.dto.OrderDTO;
import com.kasperovich.store.dto.ProductDTO;
import com.kasperovich.store.model.Product;
import com.kasperovich.store.service.order.OrderService;
import com.kasperovich.store.service.product.ProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.lang.annotation.Repeatable;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@Api(tags = {"Product"})
@RequestMapping(value = "/api/v1/adam", produces = MediaType.APPLICATION_JSON_VALUE)
@Validated
@Slf4j
public class ProductController {
    @Autowired
    private ProductService productService;

    @Operation(summary = "product")
    @GetMapping("/products")
    @PostMapping("/products")
    @ApiOperation(value = "Get Balances", responseContainer = "List")
    @ApiResponses(value = {@ApiResponse(code = 200, responseContainer = "List", message = "Returns List of accounts with balances"),
            @ApiResponse(code = 416, message = "No more records"),
            @ApiResponse(code = 401, message = "Not authorized"),
    }
    )

    public ResponseEntity<List<ProductDTO>> findAllProd(){
        return ResponseEntity.ok(productService.findAll());
    }

    @PostMapping("/products")
    public ResponseEntity<Map<String, Product>> createProduct(@RequestBody ProductDTO productDTO){
        Product product=new Product(productDTO.getId(),productDTO.getName(),productDTO.getPrice(),productDTO.getProductStatus(),productDTO.getCreatedAt(), productDTO.getIsDeleted());
        productService.createProduct(product);
//        return ResponseEntity.ok("Product:"+product.getName());
        return new ResponseEntity<>(Collections.singletonMap("New product:", product), HttpStatus.CREATED);
    }

    @PutMapping("/products")
    public ResponseEntity<String> updateProduct(@RequestBody ProductDTO productDTO){
        productService.updateProduct(productDTO);
        return new ResponseEntity<>("Updated product with id="+productDTO.getId(), HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/products/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable(value = "id") Long id){
        productService.deleteProduct(id);
        return ResponseEntity.ok("Deleted product with id:"+id);
    }
}
