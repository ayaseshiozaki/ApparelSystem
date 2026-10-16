package com.example.apparelsystem.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Column;

import java.time.LocalDateTime;

@Entity
@Table(name = "sale")
public class Sale {

    @Id
    @Column(name = "SaleID", length = 8, nullable = false)
    private String saleID;

    @Column(name = "LineID", length = 8, nullable = false)
    private String lineID;

    @Column(name = "AcceptanceID", length = 8, nullable = false)
    private String acceptanceID;

    @Column(name = "SaleDateAndTime")
    private LocalDateTime saleDateAndTime;

    @Column(name = "Quantity")
    private Integer quantity;

    @Column(name = "BuyersAgeGroup", length = 1)
    private String buyersAgeGroup;

    @Column(name = "BuyersGender", length = 1)
    private String buyersGender;

    @Column(name = "ContactPerson")
    private Integer contactPerson;

    @Column(name = "PaymentMethod", length = 1)
    private String paymentMethod;

    public Sale() {
    }

    public String getSaleID() {
        return saleID;
    }

    public void setSaleID(String saleID) {
        this.saleID = saleID;
    }

    public String getLineID() {
        return lineID;
    }

    public void setLineID(String lineID) {
        this.lineID = lineID;
    }

    public String getAcceptanceID() {
        return acceptanceID;
    }

    public void setAcceptanceID(String acceptanceID) {
        this.acceptanceID = acceptanceID;
    }

    public LocalDateTime getSaleDateAndTime() {
        return saleDateAndTime;
    }

    public void setSaleDateAndTime(LocalDateTime saleDateAndTime) {
        this.saleDateAndTime = saleDateAndTime;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getBuyersAgeGroup() {
        return buyersAgeGroup;
    }

    public void setBuyersAgeGroup(String buyersAgeGroup) {
        this.buyersAgeGroup = buyersAgeGroup;
    }

    public String getBuyersGender() {
        return buyersGender;
    }

    public void setBuyersGender(String buyersGender) {
        this.buyersGender = buyersGender;
    }

    public Integer getContactPerson() {
        return contactPerson;
    }

    public void setContactPerson(Integer contactPerson) {
        this.contactPerson = contactPerson;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }
}
