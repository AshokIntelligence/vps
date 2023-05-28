package com.app.vps.login.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "pur_supplier_mst")
public class SupplierModel implements Serializable {

    private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "supplier_code")
    private String supplierCode;
    
    @Column(name = "supplier_name")
    private String supplierName;
    
    @Column(name = "supplier_Password")
    private String supplierPassword;
    
    @Column(name = "supplier_contact_no")
    private String supplierContactNo;
    
    @Column(name = "supplier_contact_no2")
    private String supplierContactNo2;
    
    @Column(name = "supplier_email")
    private String supplierEmail;
    
    @Column(name = "supplier_deactivate_comment")
    private String supplierDeactivateComment;
    
    @Column(name = "reg_by")
    private long regBy;
    
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "reg_date")
	private LocalDate regDate;
	
	@DateTimeFormat(pattern = "hh:mm a")
	@Column(name = "reg_time")
	private LocalTime regTime;
    
    @Column(name = "status")
    private int status;
    
    @Column(name = "pr_status")
    private int prStatus;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSupplierCode() {
		return supplierCode;
	}

	public void setSupplierCode(String supplierCode) {
		this.supplierCode = supplierCode;
	}

	public String getSupplierName() {
		return supplierName;
	}

	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}

	public String getSupplierPassword() {
		return supplierPassword;
	}

	public void setSupplierPassword(String supplierPassword) {
		this.supplierPassword = supplierPassword;
	}

	public String getSupplierContactNo() {
		return supplierContactNo;
	}

	public void setSupplierContactNo(String supplierContactNo) {
		this.supplierContactNo = supplierContactNo;
	}

	public String getSupplierContactNo2() {
		return supplierContactNo2;
	}

	public void setSupplierContactNo2(String supplierContactNo2) {
		this.supplierContactNo2 = supplierContactNo2;
	}

	public String getSupplierEmail() {
		return supplierEmail;
	}

	public void setSupplierEmail(String supplierEmail) {
		this.supplierEmail = supplierEmail;
	}

	public String getSupplierDeactivateComment() {
		return supplierDeactivateComment;
	}

	public void setSupplierDeactivateComment(String supplierDeactivateComment) {
		this.supplierDeactivateComment = supplierDeactivateComment;
	}

	public long getRegBy() {
		return regBy;
	}

	public void setRegBy(long regBy) {
		this.regBy = regBy;
	}

	public LocalDate getRegDate() {
		return regDate;
	}

	public void setRegDate(LocalDate regDate) {
		this.regDate = regDate;
	}

	public LocalTime getRegTime() {
		return regTime;
	}

	public void setRegTime(LocalTime regTime) {
		this.regTime = regTime;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getPrStatus() {
		return prStatus;
	}

	public void setPrStatus(int prStatus) {
		this.prStatus = prStatus;
	}

    
}
