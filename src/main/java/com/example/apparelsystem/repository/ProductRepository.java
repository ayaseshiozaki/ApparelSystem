package com.example.apparelsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.apparelsystem.model.Product;

public interface ProductRepository extends JpaRepository<Product, String> {

}