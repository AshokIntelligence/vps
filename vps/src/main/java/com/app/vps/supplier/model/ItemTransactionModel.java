package com.app.vps.supplier.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "vps_item_tranaction")
public class ItemTransactionModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    
    @Column(name = "invoice_id")
    private Long invoiceId;

    @Column(name = "item_code", length = 30)
    private String itemCode;
    
    @Column(name = "po_no", length = 30)
    private String poNo;
    
    @Column(name = "item_quantity", length = 20)
    private double itemQuantity;
    
    @Column(name = "item_amt", length = 50)
    private double itemAmt;
    
    @Column(name = "item_uom")
    private String itemUom;
    
    @Column(name = "item_desc" , length = 300)
    private String itemDesc;
    
    @Column(name = "status", length = 5)
    private int status;
    
    @Column(name = "reg_date" , length = 20)
    private String regDate;
    
    @Column(name = "reg_time" , length = 20)
    private String regTime;
    
	public ItemTransactionModel() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}


	public Long getInvoiceId() {
		return invoiceId;
	}

	public void setInvoiceId(Long invoiceId) {
		this.invoiceId = invoiceId;
	}

	public String getItemCode() {
		return itemCode;
	}

	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}

	public String getPoNo() {
		return poNo;
	}

	public void setPoNo(String poNo) {
		this.poNo = poNo;
	}

	public double getItemQuantity() {
		return itemQuantity;
	}

	public void setItemQuantity(double itemQuantity) {
		this.itemQuantity = itemQuantity;
	}

	public double getItemAmt() {
		return itemAmt;
	}

	public void setItemAmt(double itemAmt) {
		this.itemAmt = itemAmt;
	}

	public String getItemUom() {
		return itemUom;
	}

	public void setItemUom(String itemUom) {
		this.itemUom = itemUom;
	}

	public String getItemDesc() {
		return itemDesc;
	}

	public void setItemDesc(String itemDesc) {
		this.itemDesc = itemDesc;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
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
	
}
