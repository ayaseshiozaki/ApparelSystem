package com.example.apparelsystem.repository;

import com.example.apparelsystem.model.Sale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface SaleRepository extends JpaRepository<Sale, Long> {

    // 商品別売上
    List<Sale> findByProductId(Long productId);

    // 日付範囲で検索
    List<Sale> findBySaleDateBetween(LocalDateTime start, LocalDateTime end);

    // 売上合計
    @Query("SELECT SUM(s.totalPrice) FROM Sale s")
    Integer getTotalSalesAmount();

    // 今日の売上
    @Query("SELECT SUM(s.totalPrice) FROM Sale s WHERE DATE(s.saleDate) = CURRENT_DATE")
    Integer getTodaySalesAmount();
}
