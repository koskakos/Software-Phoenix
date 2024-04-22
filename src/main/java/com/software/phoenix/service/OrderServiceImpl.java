package com.software.phoenix.service;

import com.software.phoenix.model.Order;
import com.software.phoenix.model.User;
import com.software.phoenix.model.request.OrderRequest;
import com.software.phoenix.repository.OrderRepository;
import com.software.phoenix.service.interfaces.OrderService;
import com.software.phoenix.service.interfaces.UserService;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;


    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public Order createOrder(OrderRequest request, User user) {
        Order order = Order.builder()
                .product(request.getProduct())
                .quantity(request.getQuantity())
                .address(request.getAddress())
                .phoneNumber(request.getPhoneNumber())
                .user(user)
                .build();
        return orderRepository.save(order);
    }

    public Order deleteOrder(Long id, User user) {
        Order order = getOrder(id);
        if(!isHavePermission(order, user)) return null;
        orderRepository.delete(order);
        return order;
    }

    public Order updateOrder(Long id, User user, OrderRequest request) {
        Order order = getOrder(id);
        if(!isHavePermission(order, user)) return null;

        if (request.getProduct() != null) {
            order.setProduct(request.getProduct());
        }
        if (request.getAddress() != null) {
            order.setAddress(request.getAddress());
        }
        if (request.getQuantity() != null) {
            order.setQuantity(request.getQuantity());
        }
        if (request.getPhoneNumber() != null) {
            order.setPhoneNumber(request.getPhoneNumber());
        }

        orderRepository.save(order);
        return order;
    }

    public Order getOrder(Long id) {
        return orderRepository.findById(id).orElseThrow(()
                -> new NoSuchElementException(String.format("Order with id '%d' not found", id)));
    }

    private boolean isHavePermission(Order order, User user) {
        return order.getUser().getId() == user.getId();
    }
}
