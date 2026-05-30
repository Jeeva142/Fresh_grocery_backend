package com.example.demo.controller;

import com.example.demo.entity.Order;
import com.example.demo.repository.OrderRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
@CrossOrigin(origins = "http://localhost:3000")
public class OrderController {

    @Autowired
    private OrderRepository orderRepository;

    // ✅ Place Order
    @PostMapping
    public Order placeOrder(@RequestBody Order order) {

        order.setStatus("PLACED");

        return orderRepository.save(order);
    }

    // ✅ Get All Orders
    @GetMapping
    public List<Order> getOrders() {

        return orderRepository.findAll();
    }

    // ✅ Update Order Status
    @PutMapping("/{id}")
    public Order updateStatus(
            @PathVariable Long id,
            @RequestBody Order updatedOrder
    ) {

        Order order = orderRepository
                .findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        order.setStatus(updatedOrder.getStatus());

        return orderRepository.save(order);
    }
}