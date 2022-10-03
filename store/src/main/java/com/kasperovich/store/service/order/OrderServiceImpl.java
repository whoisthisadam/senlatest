package com.kasperovich.store.service.order;

import com.kasperovich.store.dto.OrderDTO;
import com.kasperovich.store.enums.ProductStatus;
import com.kasperovich.store.excepion.NotPossibleToCreateOrderException;
import com.kasperovich.store.model.Order;
import com.kasperovich.store.model.Product;
import com.kasperovich.store.repository.OrderRepository;
import com.kasperovich.store.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.*;
import java.util.function.Supplier;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    private final ProductRepository productRepository;

    @Override
    public List<Order> findSortedAllOrdersWithParam(Timestamp from, Timestamp to) {
        List<Order> allOrders=orderRepository.findAll();
        List<Order> resultList=new ArrayList<>();
        allOrders.forEach(x->{
            if (x.getCreatedAt().after(from)&&x.getCreatedAt().before(to))
                resultList.add(x);
        });
        resultList.sort(Order::compareTo);
        return resultList;
    }

    @Override
    public Order createOrder(OrderDTO orderDTO) throws NotPossibleToCreateOrderException {
        Order order=new Order();
        order.setUserId(Optional.ofNullable(orderDTO.getUserId()).orElseThrow(NotPossibleToCreateOrderException::new));
        order.setStatus(orderDTO.getStatus());
        order.setCreatedAt(new Timestamp(new Date().getTime()));

        Set<Product>products=productRepository
                                .findAllById(orderDTO.getProductSet())
                                .stream()
                                .filter(x-> !(x.getProductStatus().equals(ProductStatus.OUT_OF_STOCK))).collect(Collectors.toSet());


        if(products.isEmpty())throw new NotPossibleToCreateOrderException();
        order.setProducts(products);
        return orderRepository.save(order);
    }
}
