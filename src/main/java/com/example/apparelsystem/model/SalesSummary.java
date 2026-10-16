package com.example.apparelsystem.model;

public class SalesSummary {

    private int totalQuantity;   // 合計販売数
    private int totalAmount;     // 合計売上金額
    private String period;       // 集計期間（例：2024-10, 今日, 今週など）

    public SalesSummary(int totalQuantity, int totalAmount, String period) {
        this.totalQuantity = totalQuantity;
        this.totalAmount = totalAmount;
        this.period = period;
    }

    public int getTotalQuantity() { return totalQuantity; }
    public int getTotalAmount() { return totalAmount; }
    public String getPeriod() { return period; }
}
