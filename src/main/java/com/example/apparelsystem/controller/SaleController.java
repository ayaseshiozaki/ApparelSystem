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

        // 売上IDが入力されていない場合は登録しない
        if (sale.getSaleID() == null || sale.getSaleID().isBlank()) {
            redirectAttributes.addFlashAttribute(
                    "errorMessage", "売上IDを入力してください。");
            return "redirect:/sales/new";
        }

        // 売上IDが8文字を超えている場合は登録しない
        if (sale.getSaleID().length() > 8) {
            redirectAttributes.addFlashAttribute(
                    "errorMessage", "売上IDは8文字以内で入力してください。");
            return "redirect:/sales/new";
        }

        // 必須項目の確認
        if (sale.getLineID() == null || sale.getLineID().isBlank()
                || sale.getAcceptanceID() == null
                || sale.getAcceptanceID().isBlank()) {
            redirectAttributes.addFlashAttribute(
                    "errorMessage", "明細IDと受付IDを入力してください。");
            return "redirect:/sales/new";
        }

        // IDの長さを確認
        if (sale.getLineID().length() > 8
                || sale.getAcceptanceID().length() > 8) {
            redirectAttributes.addFlashAttribute(
                    "errorMessage", "明細IDと受付IDは8文字以内で入力してください。");
            return "redirect:/sales/new";
        }

        // 売上IDの重複を確認
        if (saleRepository.existsById(sale.getSaleID())) {
            redirectAttributes.addFlashAttribute(
                    "errorMessage", "この売上IDはすでに使用されています。");
            return "redirect:/sales/new";
        }

        // 売上日時が未入力なら現在日時を設定
        if (sale.getSaleDateAndTime() == null) {
            sale.setSaleDateAndTime(java.time.LocalDateTime.now());
        }

        // データベースに登録
        saleRepository.save(sale);

        redirectAttributes.addFlashAttribute(
                "message", "売上を登録しました。");

        return "redirect:/sales/new";
    }
}