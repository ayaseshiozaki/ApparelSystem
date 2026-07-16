package com.example.apparelsystem.controller;

import com.example.apparelsystem.model.Supplier;
import com.example.apparelsystem.repository.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class SupplierController {

    @Autowired
    private SupplierRepository supplierRepository;

    @GetMapping("/suppliers")
    public  String showSupplierList(Model model){
        List<Supplier> supplierList= supplierRepository.findAll();

        model.addAttribute("suppliers",supplierList);

        return "supplier_list";
    }
}
