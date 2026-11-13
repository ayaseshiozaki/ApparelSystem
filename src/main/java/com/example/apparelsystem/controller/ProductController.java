package com.example.apparelsystem.controller;

import com.example.apparelsystem.model.Product;
import com.example.apparelsystem.repository.ProductRepository;
import com.example.apparelsystem.repository.SupplierRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDate;

@Controller
public class ProductController {

    private final ProductRepository productRepository;
    private final SupplierRepository supplierRepository;

    public ProductController(ProductRepository productRepository, SupplierRepository supplierRepository) {
        this.productRepository = productRepository;
        this.supplierRepository = supplierRepository;
    }

    // 既存の商品一覧表示 (http://localhost:8080/products)
    @GetMapping("/products")
    public String productList(Model model) {
        model.addAttribute("products", productRepository.findAll());
        return "product";
    }

    // ① 商品登録（入力）画面を表示 (http://localhost:8080/product/register)
    @GetMapping("/product/register")
    public String showRegisterForm(Model model) {
        Product product = new Product();
        product.setProductRegistrationDate(LocalDate.now());

        model.addAttribute("product", product);
        model.addAttribute("suppliers", supplierRepository.findAll());
        // ★移動後のパスとファイル名に合わせる
        return "44-X-shouhin/44-shouhin_touroku";
    }
    // ② 商品登録（確認）画面を表示
    @PostMapping("/product/confirm")
    public String confirmRegister(@ModelAttribute Product product, Model model) {
        model.addAttribute("product", product);
        // ★フォルダ名とファイル名を実物と完全一致させる
        return "44-X-shouhin/45-shouhin_touroku_kakunin";
    }

    // ③ DBに保存して（完了）画面を表示
    @PostMapping("/product/complete")
    public String completeRegister(@ModelAttribute Product product) {
        productRepository.save(product);
        // ★フォルダ名とファイル名を実物と完全一致させる
        return "44-X-shouhin/46-shouhin_touroku_kanryou";
    }
}