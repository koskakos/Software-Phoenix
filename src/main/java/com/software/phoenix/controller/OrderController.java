package com.software.phoenix.controller;


import com.software.phoenix.model.Order;
import com.software.phoenix.model.request.OrderRequest;
import com.software.phoenix.service.interfaces.OrderService;
import com.software.phoenix.service.interfaces.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/order")
public class OrderController {
    private final UserService userService;
    private final OrderService orderService;

    public OrderController(UserService userService, OrderService orderService) {
        this.userService = userService;
        this.orderService = orderService;
    }

    @PostMapping("")
    public ResponseEntity<?> createOrder(@Valid @RequestBody OrderRequest orderRequest) {
        return ResponseEntity.ok(orderService.createOrder(orderRequest, userService.getAuthenticatedUser()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getOrder(@PathVariable Long id) {
        return ResponseEntity.ok(orderService.getOrder(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateOrder(@PathVariable Long id, @Valid @RequestBody OrderRequest orderRequest) {
        Order order = orderService.updateOrder(id, userService.getAuthenticatedUser(), orderRequest);
        if (order == null) return ResponseEntity.status(HttpStatusCode.valueOf(403)).build();
        return ResponseEntity.ok(order);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteOrder(@PathVariable Long id) {
        Order order = orderService.deleteOrder(id, userService.getAuthenticatedUser());
        if (order == null) return ResponseEntity.status(HttpStatusCode.valueOf(403)).build();
        return ResponseEntity.ok(order);
    }
}
