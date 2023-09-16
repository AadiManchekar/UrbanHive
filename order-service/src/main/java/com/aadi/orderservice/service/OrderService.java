package com.aadi.orderservice.service;

import com.aadi.orderservice.dto.OrderItemDto;
import com.aadi.orderservice.dto.OrderRequest;
import com.aadi.orderservice.model.Order;
import com.aadi.orderservice.model.OrderItem;
import com.aadi.orderservice.repository.OrderRepository;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
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
    log.info("order {} saved successfuly", order.getOrderNumber());
  }

  private OrderItem mapToEntity(OrderItemDto orderItemDto) {
    OrderItem orderItem = new OrderItem();
    BeanUtils.copyProperties(orderItemDto, orderItem);
    return orderItem;
  }
}
