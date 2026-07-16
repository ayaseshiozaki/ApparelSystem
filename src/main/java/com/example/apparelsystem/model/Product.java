package com.example.apparelsystem.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.LocalDate;

@Entity
@Table(name = "product")
public class Product {

    @Id
    private String productID;

    private String supplierID;

    private LocalDate productRegistrationDate;

    private String productName;

    private String itemCategory;

    private String color;

    private String size;

    private String gender;

    private String targetSalesAgeGroup;

    private Integer purchasePrice;

    // Getter
    public String getProductID() {
        return productID;
    }

    public String getSupplierID() {
        return supplierID;
    }

    public LocalDate getProductRegistrationDate() {
        return productRegistrationDate;
    }

    public String getProductName() {
        return productName;
    }

    public String getItemCategory() {
        return itemCategory;
    }

    public String getColor() {
        return color;
    }

    public String getSize() {
        return size;
    }

    public String getGender() {
        return gender;
    }

    public String getTargetSalesAgeGroup() {
        return targetSalesAgeGroup;
    }

    public Integer getPurchasePrice() {
        return purchasePrice;
    }

    // Setter
    public void setProductID(String productID) {
        this.productID = productID;
    }

    public void setSupplierID(String supplierID) {
        this.supplierID = supplierID;
    }

    public void setProductRegistrationDate(LocalDate productRegistrationDate) {
        this.productRegistrationDate = productRegistrationDate;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setItemCategory(String itemCategory) {
        this.itemCategory = itemCategory;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setTargetSalesAgeGroup(String targetSalesAgeGroup) {
        this.targetSalesAgeGroup = targetSalesAgeGroup;
    }

    public void setPurchasePrice(Integer purchasePrice) {
        this.purchasePrice = purchasePrice;
    }
}