package model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import org.springframework.web.bind.annotation.BindParam;

import java.time.LocalDate;

@Entity
@Table(name="Supplier")
public class Supplier {

    @Id
    @Column(name="SupplierID")
    private String supplierID;

    @Column(name="SupplierName")
    private String supplierName;

    @Column(name="SupplierAddress")
    private String supplierAddress;

    @Column(name="PhoneNumber")
    private String PhoneNumber;

    @Column(name="ContactPersonName")
    private String contactPersonName;

    @Column(name="MailAddress")
    private String mailAddress;

    @Column(name="RegistrationDate")
    private LocalDate registrationDate;

    //getter・setterの作成…メインメニュー ⇒ コード ⇒ 生成 ⇒ getterとsetter ⇒　作成する項目を選択してOK
    public String getSupplierID() {
        return supplierID;
    }
    public void setSupplierID(String supplierID) {
        this.supplierID = supplierID;
    }

    public String getSupplierName() {
        return supplierName;
    }
    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public String getSupplierAddress() {
        return supplierAddress;
    }
    public void setSupplierAddress(String supplierAddress) {
        this.supplierAddress = supplierAddress;
    }

    public String getPhoneNumber() {
        return PhoneNumber;
    }
    public void setPhoneNumber(String phoneNumber) {
        PhoneNumber = phoneNumber;
    }

    public String getContactPersonName() {
        return contactPersonName;
    }
    public void setContactPersonName(String contactPersonName) {
        this.contactPersonName = contactPersonName;
    }

    public String getMailAddress() {
        return mailAddress;
    }
    public void setMailAddress(String mailAddress) {
        this.mailAddress = mailAddress;
    }

    public LocalDate getRegistrationDate() {
        return registrationDate;
    }
    public void setRegistrationDate(LocalDate registrationDate) {
        this.registrationDate = registrationDate;
    }
}
