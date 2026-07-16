package com.example.apparelsystem.controller;

import com.example.apparelsystem.repository.ProductRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProductController {

    private final ProductRepository productRepository;

    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping("/products")
    public String productList(Model model) {
        model.addAttribute("products", productRepository.findAll());
        return "product";
    }
}