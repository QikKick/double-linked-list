package com.example.antrasdarbas.repos;

import com.example.antrasdarbas.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRep extends JpaRepository<Product, Integer>{
}
