
package com.example.apparelsystem.controller;

import com.example.apparelsystem.model.Supplier;
import com.example.apparelsystem.repository.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

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

    @GetMapping("/supplier/menu")
    public String shouMenu(){
        return "30-43-siiresaki/30-shiiresaki_kanri";
    }

    @GetMapping("/supplier/register")
    public String shouRegisterForm(Model model){
        model.addAttribute("supplier",new Supplier());
        return "30-43-siiresaki/31-shiiresaki_touroku";
    }

    @PostMapping("/supplier/confirm")
        public String confirmRegister(@ModelAttribute Supplier supplier,Model model){
        model.addAttribute("supplier",supplier);
        return "30-43-siiresaki/32-siiresaki_touroku_kakunin";
    }

    @PostMapping("/supplier/complete")
    public String completeRegister(@ModelAttribute Supplier supplier){
    return "30-43-siiresaki/33-siiresaki_touroku_kanryou";
        }
}
