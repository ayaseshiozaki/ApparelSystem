package com.example.apparelsystem.repository;

import com.example.apparelsystem.model.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SupplierRepository extends JpaRepository<Supplier,String>{

    @Query("SELECT s FROM Supplier s WHERE"+
            "(:supplierID = '' OR s.supplierID LIKE %:supplierID%) AND " +
            "(:supplierName = '' OR s.supplierName LIKE %:supplierName%) AND " +
            "(:supplierAddress = '' OR s.supplierAddress LIKE %:supplierAddress%) AND " +
            "(:phoneNumber = '' OR s.phoneNumber LIKE %:phoneNumber%) AND " +
            "(:contactPersonName = '' OR s.contactPersonName LIKE %:contactPersonName%) AND " +
            "(:mailAddress = '' OR s.mailAddress LIKE %:mailAddress%)")
    List<Supplier> searchSuppliers(
            @Param("supplierID") String supplierID,
            @Param("supplierName") String supplierName,
            @Param("supplierAddress") String supplierAddress,
            @Param("phoneNumber") String phoneNumber,
            @Param("contactPersonName") String contactPersonName,
            @Param("mailAddress") String mailAddress
    );
}