package com.example.apparelsystem.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.LocalDateTime;

@Entity
@Table(name = "sale")
public class Sale {

    // 売上ID：主キー、8文字以内
    @Id
    @Column(
            name = "SaleID",
            length = 8,
            nullable = false
    )
    private String saleID;

    // 明細ID：8文字以内
    @Column(
            name = "LineID",
            length = 8,
            nullable = false
    )
    private String lineID;

    // 受付ID：8文字以内
    @Column(
            name = "AcceptanceID",
            length = 8,
            nullable = false
    )
    private String acceptanceID;

    // 売上日時
    @Column(
            name = "SaleDateAndTime",
            nullable = false
    )
    private LocalDateTime saleDateAndTime;

    // 販売数量
    @Column(
            name = "Quantity",
            nullable = false
    )
    private Integer quantity;

    // 購入者の年代区分
    @Column(
            name = "BuyersAgeGroup",
            length = 1,
            nullable = false
    )
    private String buyersAgeGroup;

    // 購入者の性別区分
    @Column(
            name = "BuyersGender",
            length = 1,
            nullable = false
    )
    private String buyersGender;

    // 担当者番号
    @Column(
            name = "ContactPerson",
            nullable = false
    )
    private Integer contactPerson;

    // 支払方法区分
    @Column(
            name = "PaymentMethod",
            length = 1,
            nullable = false
    )
    private String paymentMethod;

    /*
     * JPAで必要な引数なしコンストラクタ
     */
    public Sale() {
    }

    /*
     * CSVからSaleを作成するときに使用するコンストラクタ
     */
    public Sale(
            String saleID,
            String lineID,
            String acceptanceID,
            LocalDateTime saleDateAndTime,
            Integer quantity,
            String buyersAgeGroup,
            String buyersGender,
            Integer contactPerson,
            String paymentMethod
    ) {
        this.saleID = saleID;
        this.lineID = lineID;
        this.acceptanceID = acceptanceID;
        this.saleDateAndTime = saleDateAndTime;
        this.quantity = quantity;
        this.buyersAgeGroup = buyersAgeGroup;
        this.buyersGender = buyersGender;
        this.contactPerson = contactPerson;
        this.paymentMethod = paymentMethod;
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

    public void setSaleDateAndTime(
            LocalDateTime saleDateAndTime
    ) {
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

    public void setBuyersAgeGroup(
            String buyersAgeGroup
    ) {
        this.buyersAgeGroup = buyersAgeGroup;
    }

    public String getBuyersGender() {
        return buyersGender;
    }

    public void setBuyersGender(
            String buyersGender
    ) {
        this.buyersGender = buyersGender;
    }

    public Integer getContactPerson() {
        return contactPerson;
    }

    public void setContactPerson(
            Integer contactPerson
    ) {
        this.contactPerson = contactPerson;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(
            String paymentMethod
    ) {
        this.paymentMethod = paymentMethod;
    }
}