package com.elarsenaldecamisetas.app.service;

import com.elarsenaldecamisetas.app.dto.OrderDTO;
import com.elarsenaldecamisetas.app.dto.CreateOrderRequest;
import com.elarsenaldecamisetas.app.enums.Status;

import java.util.List;

public interface OrderService {
    public OrderDTO createOrder(Long userId, CreateOrderRequest request);
    public List <OrderDTO> getOrdersByUserId(Long userId);
    public List <OrderDTO> getCurrentUserOrders();
    public OrderDTO findById(Long id);
    public OrderDTO updateOrderStatus(Long id, Status status);
    public void updatePaypalOrderId(Long id, String paypalOrderId);
}
