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
    public Order createOrder(OrderDTO orderDTO){
        Order order=new Order();
        order.setUserId(orderDTO.getUserId());
        order.setStatus(orderDTO.getStatus());
        order.setCreatedAt(new Timestamp(new Date().getTime()));

        Set<Product>products= new HashSet<>(productRepository.findAllById(orderDTO.getProductSet()));
//        products.forEach(x->{
//            if(x.getProductStatus().equals(ProductStatus.OUT_OF_STOCK)) throw new NotPossibleToCreateOrderException();
//        });

        productRepository.findAllById(orderDTO.getProductSet()).forEach(x->{
            if(x.getProductStatus().equals(ProductStatus.OUT_OF_STOCK)) try {
                throw new NotPossibleToCreateOrderException();
            } catch (NotPossibleToCreateOrderException e) {
                throw new RuntimeException(e);
            }
        });
        order.setProducts(products);
        return orderRepository.save(order);
    }
}
