package com.example.apparelsystem.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.LocalDate;

@Entity
@Table(name = "product")
public class Product {

    @Id
    @Column(name = "ProductID")
    private String productID;

    @Column(name = "SupplierID")
    private String supplierID;

    @Column(name = "ProductRegistrationDate")
    private LocalDate productRegistrationDate;

    @Column(name = "productName") // ※DBの実カラム名に合わせる
    private String productName;

    @Column(name = "itemCategory")
    private String itemCategory;

    @Column(name = "color")
    private String color;

    @Column(name = "size")
    private String size;

    @Column(name = "gender")
    private String gender;

    @Column(name = "targetSalesAgeGroup")
    private String targetSalesAgeGroup;

    @Column(name = "PurchasePrice")
    private Integer purchasePrice;

    // --- Getter / Setter ---
    public String getProductID() { return productID; }
    public void setProductID(String productID) { this.productID = productID; }

    public String getSupplierID() { return supplierID; }
    public void setSupplierID(String supplierID) { this.supplierID = supplierID; }

    public LocalDate getProductRegistrationDate() { return productRegistrationDate; }
    public void setProductRegistrationDate(LocalDate productRegistrationDate) { this.productRegistrationDate = productRegistrationDate; }

    public String getProductName() { return productName; }
    public void setProductName(String productName) { this.productName = productName; }

    public String getItemCategory() { return itemCategory; }
    public void setItemCategory(String itemCategory) { this.itemCategory = itemCategory; }

    public String getColor() { return color; }
    public void setColor(String color) { this.color = color; }

    public String getSize() { return size; }
    public void setSize(String size) { this.size = size; }

    public String getGender() { return gender; }
    public void setGender(String gender) { this.gender = gender; }

    public String getTargetSalesAgeGroup() { return targetSalesAgeGroup; }
    public void setTargetSalesAgeGroup(String targetSalesAgeGroup) { this.targetSalesAgeGroup = targetSalesAgeGroup; }

    public Integer getPurchasePrice() { return purchasePrice; }
    public void setPurchasePrice(Integer purchasePrice) { this.purchasePrice = purchasePrice; }
}