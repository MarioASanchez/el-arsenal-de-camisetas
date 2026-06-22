package com.elarsenaldecamisetas.app.service.impl;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.elarsenaldecamisetas.app.dto.CreateOrderRequest;
import com.elarsenaldecamisetas.app.dto.OrderDTO;
import com.elarsenaldecamisetas.app.entity.Order;
import com.elarsenaldecamisetas.app.entity.OrderDetail;
import com.elarsenaldecamisetas.app.entity.Product;
import com.elarsenaldecamisetas.app.entity.User;
import com.elarsenaldecamisetas.app.enums.Status;
import com.elarsenaldecamisetas.app.exception.ResourceNotFoundException;
import com.elarsenaldecamisetas.app.mapper.OrderMapper;
import com.elarsenaldecamisetas.app.repository.OrderRepository;
import com.elarsenaldecamisetas.app.repository.ProductRepository;
import com.elarsenaldecamisetas.app.repository.UserRepository;
import com.elarsenaldecamisetas.app.service.OrderService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    @Override
    @Transactional
    public OrderDTO createOrder(Long userId, CreateOrderRequest request) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));

        Order order = new Order();
        order.setUser(user);
        order.setDate(LocalDate.now());
        order.setStatus(Status.CONFIRMED);

        BigDecimal total = BigDecimal.ZERO;
        List<OrderDetail> details = new ArrayList<>();

        for (CreateOrderRequest.CreateOrderItem item : request.getItems()) {
            Product product = productRepository.findById(item.getProductId())
                    .orElseThrow(() -> new ResourceNotFoundException("Product", "id", item.getProductId()));

            BigDecimal subTotal = product.getPrice().multiply(BigDecimal.valueOf(item.getQuantity()));
            total = total.add(subTotal);

            OrderDetail detail = new OrderDetail();
            detail.setOrder(order);
            detail.setProduct(product);
            detail.setQuantity((long)item.getQuantity());
            detail.setSize(item.getSize());
            detail.setPrice(product.getPrice());
            details.add(detail);
        }

        order.setTotalPrice(total);
        order.setDetails(details);

        Order saved = orderRepository.save(order);
        return orderMapper.toDTO(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public List<OrderDTO> getOrdersByUserId(Long userId) {
        return orderRepository.findByUserId(userId).stream()
                .map(orderMapper::toDTO)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<OrderDTO> getCurrentUserOrders() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User", "email", email));
        return getOrdersByUserId(user.getId());
    }

    @Override
    @Transactional(readOnly = true)
    public OrderDTO findById(Long id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Order", "id", id));
        return orderMapper.toDTO(order);
    }

    @Override
    @Transactional
    public OrderDTO updateOrderStatus(Long id, Status status) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Order", "id", id));
        order.setStatus(status);
        Order updated = orderRepository.save(order);
        return orderMapper.toDTO(updated);
    }

    @Override
    @Transactional
    public void updatePaypalOrderId(Long id, String paypalOrderId) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Order", "id", id));
        order.setPaypalOrderId(paypalOrderId);
        orderRepository.save(order);
    }
}
