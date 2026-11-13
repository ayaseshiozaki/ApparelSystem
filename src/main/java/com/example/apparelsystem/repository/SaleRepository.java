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

    // 削除されていない売上を全件取得
    List<Sale> findByDeleteFlag(Integer deleteFlag);

    // 売上ID + 削除フラグで検索
    List<Sale> findBySaleIDContainingAndDeleteFlag(
            String saleID,
            Integer deleteFlag
    );
    /*
     * 削除用：複数条件で売上情報を検索
     * 削除されていないデータだけを取得する
     */
    @Query("""
        SELECT s
        FROM Sale s
        WHERE s.deleteFlag = 0
          AND (:saleID IS NULL OR :saleID = ''
               OR s.saleID LIKE CONCAT('%', :saleID, '%'))
          AND (:lineID IS NULL OR :lineID = ''
               OR s.lineID LIKE CONCAT('%', :lineID, '%'))
          AND (:startDate IS NULL
               OR s.saleDateAndTime >= :startDate)
          AND (:endDate IS NULL
               OR s.saleDateAndTime < :endDate)
          AND (:quantity IS NULL OR s.quantity = :quantity)
          AND (:ageGroup IS NULL OR :ageGroup = ''
               OR s.buyersAgeGroup = :ageGroup)
          AND (:gender IS NULL OR :gender = ''
               OR s.buyersGender = :gender)
          AND (:paymentMethod IS NULL OR :paymentMethod = ''
               OR s.paymentMethod = :paymentMethod)
        """)
    List<Sale> searchSalesForDelete(
            @Param("saleID") String saleID,
            @Param("lineID") String lineID,
            @Param("startDate") LocalDateTime startDate,
            @Param("endDate") LocalDateTime endDate,
            @Param("quantity") Integer quantity,
            @Param("ageGroup") String ageGroup,
            @Param("gender") String gender,
            @Param("paymentMethod") String paymentMethod
    );
}