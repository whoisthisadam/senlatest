package com.kasperovich.store.controller;


import com.kasperovich.store.dto.OrderDTO;
import com.kasperovich.store.excepion.NotPossibleToCreateOrderException;
import com.kasperovich.store.model.Order;
import com.kasperovich.store.service.order.OrderService;
import io.swagger.annotations.*;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
public class OrderController {
    @Autowired
    private OrderService orderService;

    @Operation(summary = "order")
    @GetMapping("/orders")
    @PostMapping("/orders")
    @ApiOperation(value = "Get Balances", responseContainer = "List")
    @ApiResponses(value = {@ApiResponse(code = 200, responseContainer = "List", message = "Returns List of accounts with balances"),
            @ApiResponse(code = 416, message = "No more records"),
            @ApiResponse(code = 401, message = "Not authorized"),
    }
    )

    public ResponseEntity<List<Order>> findAllProductsWithParam (@RequestParam(value = "from:", defaultValue = "2019-10-04 12:00:17", required = false)Timestamp from
    , @RequestParam(value = "to:", defaultValue = "2022-10-04 12:00:17", required = false)Timestamp to) {
        List<Order> list = orderService.findSortedAllOrdersWithParam(from, to);
        return ResponseEntity.ok(list);
    }

    @PostMapping
    public ResponseEntity<Order> createOrder(@RequestBody OrderDTO order) throws NotPossibleToCreateOrderException {
        if(order.getUserId()==null)throw new NotPossibleToCreateOrderException();
        return new ResponseEntity<>(orderService.createOrder(order), HttpStatus.CREATED);
    }
}
