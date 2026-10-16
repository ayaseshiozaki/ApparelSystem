package com.example.apparelsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.apparelsystem.model.Product;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, String> {

    // 商品名で検索
    Product findByProductName(String productName);

    // カテゴリで検索
    List<Product> findByItemCategory(String itemCategory);

    // 性別で検索（例：メンズ・レディース）
    List<Product> findByGender(String gender);

    // サイズで検索
    List<Product> findBySize(String size);

    // 仕入先IDで検索
    List<Product> findBySupplierID(String supplierID);
}
