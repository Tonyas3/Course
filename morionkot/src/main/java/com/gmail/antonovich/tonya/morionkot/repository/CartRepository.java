package com.gmail.antonovich.tonya.morionkot.repository;

import com.gmail.antonovich.tonya.morionkot.entity.Cart;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface CartRepository  extends CrudRepository<Cart, Long> {

    Optional<List<Cart>> findByOrderId(Long order);
}
