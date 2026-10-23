package com.example.apparelsystem.controller;

import com.example.apparelsystem.model.Sale;
import com.example.apparelsystem.repository.SaleRepository;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.time.LocalDateTime;

@Controller
public class CsvUploadController {

    private final SaleRepository saleRepository;

    public CsvUploadController(
            SaleRepository saleRepository) {

        this.saleRepository = saleRepository;
    }

    @PostMapping("/sales/upload")
    public String uploadCsv(
            @RequestParam("csvFile")
            MultipartFile file)
            throws Exception {

        BufferedReader br =
                new BufferedReader(
                        new InputStreamReader(
                                file.getInputStream()));

        br.readLine();

        String line;

        while ((line = br.readLine()) != null) {

            String[] data = line.split(",");

            Sale sale = new Sale();

            sale.setSaleID(data[0]);
            sale.setLineID(data[1]);
            sale.setAcceptanceID(data[2]);

            sale.setSaleDateAndTime(
                    LocalDateTime.parse(data[3]));

            sale.setQuantity(
                    Integer.parseInt(data[4]));

            sale.setBuyersAgeGroup(data[5]);
            sale.setBuyersGender(data[6]);

            sale.setContactPerson(
                    Integer.parseInt(data[7]));

            sale.setPaymentMethod(data[8]);

            saleRepository.save(sale);
        }

        return "redirect:/sales/list";
    }
}