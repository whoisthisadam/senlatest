package com.kasperovich.store.service.order;

import com.kasperovich.store.dto.order.OrderPOSTDto;
import com.kasperovich.store.excepion.NotPossibleToCreateOrderException;
import com.kasperovich.store.model.Order;

import java.sql.Timestamp;
import java.util.List;

public interface OrderService {

    List<Order> findSortedAllOrdersWithParam(Timestamp from, Timestamp to);

    Order createOrder(OrderPOSTDto orderPOSTDto) throws NotPossibleToCreateOrderException;
}
