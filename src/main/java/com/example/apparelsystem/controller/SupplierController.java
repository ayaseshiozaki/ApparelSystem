package com.example.apparelsystem.controller;

import com.example.apparelsystem.model.Supplier;
import com.example.apparelsystem.repository.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class SupplierController {

    @Autowired
    private SupplierRepository supplierRepository;

    // 一覧表示
    @GetMapping("/suppliers")
    public String showSupplierList(Model model) {
        List<Supplier> supplierList = supplierRepository.findAll();
        model.addAttribute("suppliers", supplierList);
        return "supplier_list";
    }

    // 管理メニュー画面
    @GetMapping("/supplier/menu")
    public String shouMenu() {
        return "30-43-siiresaki/30-siiresaki_kannri";
    }

    // --- 登録機能 ---
    @GetMapping("/supplier/register")
    public String shouRegisterForm(Model model) {
        model.addAttribute("supplier", new Supplier());
        return "30-43-siiresaki/31-siiresaki_touroku";
    }

    @PostMapping("/supplier/confirm")
    public String confirmRegister(@ModelAttribute Supplier supplier, Model model) {
        model.addAttribute("supplier", supplier);
        return "30-43-siiresaki/32-siiresaki_touroku_kakunin";
    }

    @PostMapping("/supplier/complete")
    public String completeRegister(@ModelAttribute Supplier supplier) {
        supplierRepository.save(supplier);
        return "30-43-siiresaki/33-siiresaki_touroku_kanryou";
    }

    // --- 検索機能 ---
    @GetMapping("/supplier/search")
    public String showSearchForm() {
        return "30-43-siiresaki/34-siiresaki_syuusei";
    }

    @GetMapping("/supplier/search/result")
    public String searchSuppliers(
            @RequestParam(name = "supplierID", defaultValue = "") String supplierID,
            @RequestParam(name = "supplierName", defaultValue = "") String supplierName,
            @RequestParam(name = "supplierAddress", defaultValue = "") String supplierAddress,
            @RequestParam(name = "phoneNumber", defaultValue = "") String phoneNumber,
            @RequestParam(name = "contactPersonName", defaultValue = "") String contactPersonName,
            @RequestParam(name = "mailAddress", defaultValue = "") String mailAddress,
            Model model) {

        List<Supplier> searchResults = supplierRepository.searchSuppliers(
                supplierID, supplierName, supplierAddress, phoneNumber, contactPersonName, mailAddress
        );
        model.addAttribute("suppliers", searchResults);
        return "30-43-siiresaki/35-siiresaki_syuusei_kennsaku_kekka";
    }

    // --- 修正機能（ここが抜けていたため404になっていました） ---
    // 修正入力画面（36）の表示
    @GetMapping("/supplier/edit")
    public String showEditForm(@RequestParam("id") String id, Model model) {
        Supplier supplier = supplierRepository.findById(id).orElse(new Supplier());
        model.addAttribute("supplier", supplier);
        return "30-43-siiresaki/36-siiresaki_syuusei_zikkou";
    }

    // 修正確認画面（37）の表示
    @PostMapping("/supplier/edit/confirm")
    public String confirmEdit(@ModelAttribute Supplier supplier, Model model) {
        model.addAttribute("supplier", supplier);
        return "30-43-siiresaki/37-siiresaki_syuusei_kakunin";
    }

    // 修正完了処理（38）：DB上書き更新
    @PostMapping("/supplier/edit/complete")
    public String completeEdit(@ModelAttribute Supplier supplier) {
        supplierRepository.save(supplier); // 主キーが一致するレコードをUPDATE
        return "30-43-siiresaki/38-siiresaki_syuusei_kanryou";
    }
}