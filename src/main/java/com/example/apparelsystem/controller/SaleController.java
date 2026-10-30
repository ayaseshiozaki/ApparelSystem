package com.example.apparelsystem.controller;

import com.example.apparelsystem.model.Sale;
import com.example.apparelsystem.repository.SaleRepository;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/sales")
public class SaleController {

    private final SaleRepository saleRepository;

    public SaleController(SaleRepository saleRepository) {
        this.saleRepository = saleRepository;
    }

    /**
     * 売上登録画面表示
     */
    @GetMapping("/new")
    public String showSaleForm(Model model) {

        model.addAttribute(
                "sales",
                saleRepository.findAll());

        return "03-17-uriage/04-uriage_touroku";
    }

    /**
     * 売上登録
     */
    @PostMapping("/register")
    public String registerSale(
            @ModelAttribute Sale sale,
            RedirectAttributes redirectAttributes) {

        if (sale.getSaleID() == null
                || sale.getSaleID().isBlank()) {

            redirectAttributes.addFlashAttribute(
                    "errorMessage",
                    "売上IDを入力してください。");

            return "redirect:/sales/new";
        }

        if (sale.getSaleID().length() > 8) {

            redirectAttributes.addFlashAttribute(
                    "errorMessage",
                    "売上IDは8文字以内で入力してください。");

            return "redirect:/sales/new";
        }

        if (sale.getLineID() == null
                || sale.getLineID().isBlank()
                || sale.getAcceptanceID() == null
                || sale.getAcceptanceID().isBlank()) {

            redirectAttributes.addFlashAttribute(
                    "errorMessage",
                    "明細IDと受付IDを入力してください。");

            return "redirect:/sales/new";
        }

        if (sale.getLineID().length() > 8
                || sale.getAcceptanceID().length() > 8) {

            redirectAttributes.addFlashAttribute(
                    "errorMessage",
                    "明細IDと受付IDは8文字以内で入力してください。");

            return "redirect:/sales/new";
        }

        if (saleRepository.existsById(
                sale.getSaleID())) {

            redirectAttributes.addFlashAttribute(
                    "errorMessage",
                    "この売上IDはすでに使用されています。");

            return "redirect:/sales/new";
        }

        if (sale.getSaleDateAndTime() == null) {

            sale.setSaleDateAndTime(
                    java.time.LocalDateTime.now());
        }

        saleRepository.save(sale);

        redirectAttributes.addFlashAttribute(
                "message",
                "売上を登録しました。");

        return "redirect:/sales/new";
    }

    /**
     * 売上一覧表示
     */
    @GetMapping("/list")
    public String listSales(Model model) {

        model.addAttribute(
                "sales",
                saleRepository.findAll());

        return "03-17-uriage/04-uriage_touroku";
    }

    /**
     * 売上情報検索画面を表示
     */
    @GetMapping("/search-form")
    public String showSearchForm() {
        return "03-17-uriage/09-uriage_kensaku";
    }

    /**
     * 売上検索
     */
    @GetMapping("/search")
    public String searchSales(
            @RequestParam(required = false)
            String saleID,
            Model model) {

        List<Sale> sales;

        if (saleID == null
                || saleID.isBlank()) {

            sales = saleRepository.findAll();

        } else {

            sales =
                    saleRepository
                            .findBySaleIDContaining(
                                    saleID);
        }

        model.addAttribute(
                "sales",
                sales);

        return "03-17-uriage/13-uriage_syuusei_kennsaku_kekka";
    }

    /**
     * 修正画面表示
     */
    @GetMapping("/edit/{saleID}")
    public String editSale(
            @PathVariable String saleID,
            Model model) {

        Sale sale =
                saleRepository
                        .findById(saleID)
                        .orElse(null);

        model.addAttribute(
                "sale",
                sale);

        return "03-17-uriage/10-uriage_syuusei";
    }

    /**
     * 修正確認
     */
    @PostMapping("/confirm")
    public String confirmSale(
            @ModelAttribute Sale sale,
            Model model) {

        model.addAttribute(
                "sale",
                sale);

        return "03-17-uriage/11-uriage_syuusei_kakunin";
    }

    /**
     * 修正確認画面から修正入力画面へ戻る
     */
    @PostMapping("/edit-back")
    public String editBack(
            @ModelAttribute Sale sale,
            Model model) {

        model.addAttribute("sale", sale);

        return "03-17-uriage/10-uriage_syuusei";
    }
    /**
     * 修正実行
     */
    @PostMapping("/update")
    public String updateSale(
            @ModelAttribute Sale sale,
            RedirectAttributes redirectAttributes) {

        saleRepository.save(sale);

        redirectAttributes.addFlashAttribute(
                "message",
                "売上情報を修正しました。");

        return "redirect:/sales/complete";
    }

    /**
     * 修正完了画面
     */
    @GetMapping("/complete")
    public String complete() {

        return "03-17-uriage/06-uriage_syuusei_kanryou";
    }
}