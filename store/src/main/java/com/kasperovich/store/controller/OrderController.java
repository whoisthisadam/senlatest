package com.kasperovich.store.controller;


import com.kasperovich.store.dto.OrderDTO;
import com.kasperovich.store.service.order.OrderService;
import io.swagger.annotations.*;
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

    public ResponseEntity<List<OrderDTO>> findAllProductsWithParam (@RequestParam(value = "from:", defaultValue = "10-04-19 12:00:17", required = false)Timestamp from
    , @RequestParam(value = "to:", defaultValue = "10-04-22 12:00:17", required = false)Timestamp to) {
        List<OrderDTO> list =
                orderService.findSortedAllOrdersWithParam(from, to).stream().map(x->{
                    return new OrderDTO(x.getId(),x.getUserId(),x.getStatus(),x.getCreatedAt());}).collect(Collectors.toList());
        return ResponseEntity.ok(list);
    }
}