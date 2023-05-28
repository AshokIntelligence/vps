package com.app.vps.supplier.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.springframework.format.annotation.DateTimeFormat;
import com.app.vps.login.model.SupplierModel;

@Entity
@Table(name = "vps_po_master")
public class PoMasterModel implements Serializable {

    private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    
    @Column(name = "po_no", length = 20)
    private String poNo;
    
    @Column(name = "supplier_code", length = 20)
    private String supplierCode;
    @ManyToOne()
	@JoinColumn(name = "supplier_code", referencedColumnName = "supplier_code" ,insertable = false, updatable = false )
	private SupplierModel supplierDtl;
    
    @Column(name = "po_date", length = 20)
    private String poDate;
    
    @Column(name = "po_status", length = 20)
    private String poStatus;
    
    @DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate regDate;
	
	@DateTimeFormat(pattern = "hh:mm a")
	private LocalTime regTime;
	
    @Column(name = "status", length = 5)
    private int status;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPoNo() {
		return poNo;
	}

	public void setPoNo(String poNo) {
		this.poNo = poNo;
	}

	public String getSupplierCode() {
		return supplierCode;
	}

	public void setSupplierCode(String supplierCode) {
		this.supplierCode = supplierCode;
	}

	public SupplierModel getSupplierDtl() {
		return supplierDtl;
	}

	public void setSupplierDtl(SupplierModel supplierDtl) {
		this.supplierDtl = supplierDtl;
	}

	public String getPoDate() {
		return poDate;
	}

	public void setPoDate(String poDate) {
		this.poDate = poDate;
	}

	public String getPoStatus() {
		return poStatus;
	}

	public void setPoStatus(String poStatus) {
		this.poStatus = poStatus;
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

	public PoMasterModel() {
		super();
	}

	public PoMasterModel(Long id, int status) {
		this.id = id;
		this.status = status;
	}

}
