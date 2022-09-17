package com.kasperovich.store.service.order;

import com.kasperovich.store.dto.OrderDTO;
import com.kasperovich.store.model.Order;

import java.sql.Timestamp;
import java.util.List;

public interface OrderService {

    List<Order> findSortedAllOrdersWithParam(Timestamp from, Timestamp to);
}
