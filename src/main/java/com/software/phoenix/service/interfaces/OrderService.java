package com.software.phoenix.service.interfaces;

import com.software.phoenix.model.Order;
import com.software.phoenix.model.User;
import com.software.phoenix.model.request.OrderRequest;

public interface OrderService {
    Order createOrder(OrderRequest request, User user);
    Order getOrder(Long id);
    Order deleteOrder(Long id, User user);
    Order updateOrder(Long id, User user, OrderRequest request);
}
