package com.example.antrasdarbas.repos;

import com.example.antrasdarbas.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRep extends JpaRepository<Cart, Integer> {
}
