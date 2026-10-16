package com.example.apparelsystem.controller;

import com.example.apparelsystem.model.Sale;
import com.example.apparelsystem.repository.SaleRepository;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/sales")
public class SaleController {

    private final SaleRepository saleRepository;

    public SaleController(SaleRepository saleRepository) {
        this.saleRepository = saleRepository;
    }

    // 売上登録画面を表示
    @GetMapping("/new")
    public String showSaleForm() {
        return "forward:/sale.html";
    }

    // 売上データを登録
    @PostMapping("/register")
    public String registerSale(
            @ModelAttribute Sale sale,
            RedirectAttributes redirectAttributes) {

        saleRepository.save(sale);

        redirectAttributes.addFlashAttribute(
                "message", "売上を登録しました。");

        return "redirect:/sales/new";
    }
}
