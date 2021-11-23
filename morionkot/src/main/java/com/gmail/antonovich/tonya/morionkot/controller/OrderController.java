package com.gmail.antonovich.tonya.morionkot.controller;

import com.gmail.antonovich.tonya.morionkot.dto.AddOrderDto;
import com.gmail.antonovich.tonya.morionkot.dto.GetProductFromCartDto;
import com.gmail.antonovich.tonya.morionkot.dto.OrderDto;
import com.gmail.antonovich.tonya.morionkot.dto.*;
import com.gmail.antonovich.tonya.morionkot.exeption.NoSuchEntityException;
import com.gmail.antonovich.tonya.morionkot.service.OrderService;
import com.gmail.antonovich.tonya.morionkot.util.Mapper;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("orders")
    public List<OrderDto> findOrders(){
        return Mapper.mapAll(orderService.findOrders(), OrderDto.class);
    }

    @PostMapping("addOrder")
    public void addOrder(@RequestBody AddOrderDto addOrderDto) throws NoSuchEntityException {
      orderService.addOrder(addOrderDto);
    }

    @PostMapping("product-from-cart")
    public List<GetProductFromCartDto> getProductFromCart(@RequestBody OrderDto orderDto) throws NoSuchEntityException {
        return orderService.getProductFromCart(orderDto);
    }

    @PostMapping("change-status")
    public void changeStatusOrder(@RequestBody OrderDto orderDto) throws NoSuchEntityException {
        orderService.changeStatusOrder(orderDto);
    }

    @PostMapping("delete-order")
    public void deleteOrder(@RequestBody OrderDto orderDto) throws NoSuchEntityException {
        orderService.deleteOrder(orderDto);
    }
}
