package com.example.apparelsystem.repository;

import com.example.apparelsystem.model.Sale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface SaleRepository extends JpaRepository<Sale, String> {

    /**
     * 売上ID部分一致検索
     */
    List<Sale> findBySaleIDContaining(String saleID);

    /**
     * 明細ID部分一致検索
     */
    List<Sale> findByLineIDContaining(String lineID);

    /**
     * 年代検索
     */
    List<Sale> findByBuyersAgeGroup(String buyersAgeGroup);

    /**
     * 性別検索
     */
    List<Sale> findByBuyersGender(String buyersGender);

    /**
     * 支払方法検索
     */
    List<Sale> findByPaymentMethod(String paymentMethod);

    /**
     * 指定期間の売上検索
     */
    @Query("""
        SELECT s
        FROM Sale s
        WHERE s.saleDateAndTime >= :start
          AND s.saleDateAndTime < :end
        """)
    List<Sale> findSalesBetween(
            @Param("start") LocalDateTime start,
            @Param("end") LocalDateTime end
    );

    /**
     * 売上数量合計
     */
    @Query("""
        SELECT SUM(s.quantity)
        FROM Sale s
        """)
    Long getTotalSalesQuantity();

    /**
     * 指定期間の売上数量合計
     */
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