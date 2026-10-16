package com.example.apparelsystem.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "sales")
public class Sale {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 商品（アパレルなので Product と紐付け）
    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    // 売れた数量
    private int quantity;

    // 単価（Product に price があるなら不要）
    private int unitPrice;

    // 合計金額（quantity × unitPrice）
    private int totalPrice;

    // 売上日時
    private LocalDateTime saleDate;

    public Sale() {}

    public Sale(Product product, int quantity, int unitPrice, LocalDateTime saleDate) {
        this.product = product;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.totalPrice = quantity * unitPrice;
        this.saleDate = saleDate;
    }

    // getter / setter
    public Long getId() { return id; }

    public Product getProduct() { return product; }
    public void setProduct(Product product) { this.product = product; }

    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }

    public int getUnitPrice() { return unitPrice; }
    public void setUnitPrice(int unitPrice) { this.unitPrice = unitPrice; }

    public int getTotalPrice() { return totalPrice; }
    public void setTotalPrice(int totalPrice) { this.totalPrice = totalPrice; }

    public LocalDateTime getSaleDate() { return saleDate; }
    public void setSaleDate(LocalDateTime saleDate) { this.saleDate = saleDate; }
}
