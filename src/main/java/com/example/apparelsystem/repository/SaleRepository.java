package com.example.apparelsystem.repository;

import com.example.apparelsystem.model.Sale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface SaleRepository extends JpaRepository<Sale, String> {

    // 売上日時の範囲で検索
    List<Sale> findBySaleDateAndTimeBetween(
            LocalDateTime start,
            LocalDateTime end
    );

    // 売上数量の合計
    @Query("SELECT SUM(s.quantity) FROM Sale s")
    Long getTotalSalesQuantity();

    // 指定した期間の売上数量
    @Query("""
            SELECT SUM(s.quantity)
            FROM Sale s
            WHERE s.saleDateAndTime >= :start
              AND s.saleDateAndTime < :end
            """)
    Long getSalesQuantityBetween(
            @Param("start") LocalDateTime start,
            @Param("end") LocalDateTime end
    );
}