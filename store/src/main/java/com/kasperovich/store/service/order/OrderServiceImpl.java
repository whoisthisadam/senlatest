package com.kasperovich.store.service.order;

import com.kasperovich.store.dto.OrderDTO;
import com.kasperovich.store.model.Order;
import com.kasperovich.store.repository.OrderRepository;
import com.kasperovich.store.service.order.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    @Override
    public List<Order> findSortedAllOrdersWithParam(Timestamp from, Timestamp to) {
        List<Order> allOrders=orderRepository.findAll();
        List<Order> resultList=new ArrayList<>();
        allOrders.forEach(x->{
            if (x.getCreatedAt().after(from)&&x.getCreatedAt().before(to))
                resultList.add(x);
        });
        Collections.sort(resultList, Order::compareTo);


        return resultList;
    }
}
