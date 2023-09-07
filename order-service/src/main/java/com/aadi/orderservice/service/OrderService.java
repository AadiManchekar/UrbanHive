package com.aadi.orderservice.service;

import com.aadi.orderservice.dto.OrderItemDto;
import com.aadi.orderservice.dto.OrderRequest;
import com.aadi.orderservice.model.Order;
import com.aadi.orderservice.model.OrderItem;
import com.aadi.orderservice.repository.OrderRepository;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService {

  private final OrderRepository orderRepository;

  public void placeOrder(OrderRequest orderRequest) {
    Order order = new Order();
    order.setOrderNumber(UUID.randomUUID().toString());

    List<OrderItem> orderItems = orderRequest
      .getOrderItems()
      .stream()
      .map(this::mapToEntity)
      .toList();

    order.setOrderItems(orderItems);
    orderRepository.save(order);
  }

  private OrderItem mapToEntity(OrderItemDto orderItemDto) {
    OrderItem orderItem = new OrderItem();
    orderItem.setPrice(orderItemDto.getPrice());
    orderItem.setQuantity(orderItemDto.getQuantity());
    orderItem.setSkuCode(orderItemDto.getSkuCode());

    return orderItem;
  }
}
