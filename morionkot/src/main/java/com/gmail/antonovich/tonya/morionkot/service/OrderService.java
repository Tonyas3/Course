package com.gmail.antonovich.tonya.morionkot.service;

import com.gmail.antonovich.tonya.morionkot.exeption.NoSuchEntityException;
import com.gmail.antonovich.tonya.morionkot.repository.OrderRepository;
import com.gmail.antonovich.tonya.morionkot.dto.AddOrderDto;
import com.gmail.antonovich.tonya.morionkot.dto.GetProductFromCartDto;
import com.gmail.antonovich.tonya.morionkot.dto.NewCartDto;
import com.gmail.antonovich.tonya.morionkot.dto.OrderDto;
import com.gmail.antonovich.tonya.morionkot.entity.Cart;
import com.gmail.antonovich.tonya.morionkot.entity.Order;
import com.gmail.antonovich.tonya.morionkot.entity.Product;
import com.gmail.antonovich.tonya.morionkot.repository.CartRepository;
import com.gmail.antonovich.tonya.morionkot.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class OrderService {
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final CartRepository cartRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository, ProductRepository productRepository, CartRepository cartRepository) {
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
        this.cartRepository = cartRepository;
    }

    public List<Order> findOrders() {
        return orderRepository.findAll();
    }

    public void addOrder(AddOrderDto newOrder) throws NoSuchEntityException {
        Order order = new Order();
        order.setDelivery_date(newOrder.order.delivery_date);
        order.setCost(newOrder.order.cost);
        order.setDelivery_address(newOrder.order.delivery_address);
        order.setPhone_number(newOrder.order.phone_number);
        order.setPayment_method(newOrder.order.payment_method);
        order.setStatus(newOrder.order.status);
        order.setWay_of_reception(newOrder.order.way_of_reception);
        order.setCustomer_name(newOrder.order.customer_name);
        orderRepository.save(order);


        for (NewCartDto newCart : newOrder.cart) {
            Product product = productRepository.findById(newCart.id)
                    .orElseThrow(() -> new NoSuchEntityException("product not found"));

            Cart cart = new Cart();
            cart.setOrder(order);
            cart.setProduct(product);
            cart.setSelectedPrice(newCart.selectedPrice);
            cart.setSelectedWeight(newCart.selectedWeight);
            cart.setQuantity(newCart.quantity);
            cartRepository.save(cart);

        }
    }

    public List<GetProductFromCartDto> getProductFromCart(OrderDto orderDto) throws NoSuchEntityException {
        List<Cart> carts = cartRepository.findByOrderId(orderDto.id)
                .orElseThrow(() -> new NoSuchEntityException("carts not found"));
        List<GetProductFromCartDto> getProductFromCartDtos = new ArrayList<>();
        for (Cart cart : carts) {
            GetProductFromCartDto getProductFromCart = new GetProductFromCartDto();
            Product product = productRepository.findById(cart.getProduct().getId())
                    .orElseThrow(() -> new NoSuchEntityException("product not found"));
            getProductFromCart.nameProduct = product.getName();
            getProductFromCart.quantity = cart.getQuantity();
            getProductFromCart.selectedPrice = cart.getSelectedPrice();
            getProductFromCart.selectedWeight = cart.getSelectedWeight();
            getProductFromCartDtos.add(getProductFromCart);
        }
        return getProductFromCartDtos;
    }

    public void changeStatusOrder(OrderDto orderDto) throws NoSuchEntityException {
        Order order = orderRepository.findById(orderDto.id)
                .orElseThrow(() -> new NoSuchEntityException("order not found"));
        order.setStatus(orderDto.status);
        orderRepository.save(order);
    }

    public void deleteOrder(OrderDto orderDto) throws NoSuchEntityException {
        orderRepository.deleteById(orderDto.id);
    }
}
