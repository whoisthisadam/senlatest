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
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@Api(tags = {"Order"})
@RequestMapping(value = "/api/v1/adam", produces = MediaType.APPLICATION_JSON_VALUE)
@Validated
@Slf4j
public class ProductController {
    @Autowired
    private ProductService productService;

    @Operation(summary = "product")
    @GetMapping("/product")
    @PostMapping("/product")
    @ApiOperation(value = "Get Balances", responseContainer = "List")
    @ApiResponses(value = {@ApiResponse(code = 200, responseContainer = "List", message = "Returns List of accounts with balances"),
            @ApiResponse(code = 416, message = "No more records"),
            @ApiResponse(code = 401, message = "Not authorized"),
    }
    )

    public ResponseEntity<Product> Create(@RequestBody ProductDTO productDTO){
        Product product=new Product(productDTO.getId(),productDTO.getName(),productDTO.getPrice(),productDTO.getProductStatus()
        ,productDTO.getCreatedAt());
    }
}
