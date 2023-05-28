package com.app.vps.supplier.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "vps_rtv_master")
public class RTVMasterModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    
    @Column(name = "po_no", length = 30)
    private String poNo;

    @Column(name = "item_code", length = 30)
    private String itemCode;
    
    @Column(name = "item_description", length = 200)
    private String itemDescription;
    
    @Column(name = "invoice_no", length = 50)
    private String invoiceNo;
    
    @Column(name = "item_number", length = 30)
    private int itemNumber;
    
    @Column(name = "vendor_code", length = 30)
    private String vendorCode;
    
    @Column(name = "vendor_name", length = 100)
    private String vendorName;
    
    @Column(name = "rtv_no", length = 30)
    private String rtvNo;
    
    @Column(name = "rtv_date", length = 30)
    private String rtvDate;
    
    @Column(name = "item_quantity", length = 20)
    private double itemQuantity;
    
    @Column(name = "reg_date", length = 30)
    private String regDate;
    
    @Column(name = "reg_time", length = 30)
    private String regTime;

	public RTVMasterModel() {
		super();
	}

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

	public String getItemCode() {
		return itemCode;
	}

	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}

	

	public int getItemNumber() {
		return itemNumber;
	}

	public void setItemNumber(int itemNumber) {
		this.itemNumber = itemNumber;
	}

	public String getVendorCode() {
		return vendorCode;
	}

	public void setVendorCode(String vendorCode) {
		this.vendorCode = vendorCode;
	}

	public String getVendorName() {
		return vendorName;
	}

	public void setVendorName(String vendorName) {
		this.vendorName = vendorName;
	}

	public String getRtvNo() {
		return rtvNo;
	}

	public void setRtvNo(String rtvNo) {
		this.rtvNo = rtvNo;
	}

	public String getRtvDate() {
		return rtvDate;
	}

	public void setRtvDate(String rtvDate) {
		this.rtvDate = rtvDate;
	}

	public double getItemQuantity() {
		return itemQuantity;
	}

	public void setItemQuantity(double itemQuantity) {
		this.itemQuantity = itemQuantity;
	}

	public String getRegDate() {
		return regDate;
	}

	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}

	public String getRegTime() {
		return regTime;
	}

	public void setRegTime(String regTime) {
		this.regTime = regTime;
	}

	public String getItemDescription() {
		return itemDescription;
	}

	public void setItemDescription(String itemDescription) {
		this.itemDescription = itemDescription;
	}

	public String getInvoiceNo() {
		return invoiceNo;
	}

	public void setInvoiceNo(String invoiceNo) {
		this.invoiceNo = invoiceNo;
	}

	public RTVMasterModel(Long id, String poNo, String itemCode, String itemDescription, String invoiceNo,
			int itemNumber, String vendorCode, String vendorName, String rtvNo, String rtvDate, double itemQuantity) {
		this.id = id;
		this.poNo = poNo;
		this.itemCode = itemCode;
		this.itemDescription = itemDescription;
		this.invoiceNo = invoiceNo;
		this.itemNumber = itemNumber;
		this.vendorCode = vendorCode;
		this.vendorName = vendorName;
		this.rtvNo = rtvNo;
		this.rtvDate = rtvDate;
		this.itemQuantity = itemQuantity;
	}
	
	
}
