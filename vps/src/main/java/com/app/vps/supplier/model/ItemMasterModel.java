package com.app.vps.supplier.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "vps_item_master")
public class ItemMasterModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "item_code", length = 30)
    private String itemCode;
    
    @Column(name = "po_no", length = 30)
    private String poNo;
    @ManyToOne()
   	@JoinColumn(name = "po_no", referencedColumnName = "po_no" ,insertable = false, updatable = false )
   	private PoMasterModel poMasterDtl;
    
    @Column(name = "item_quantity", length = 20)
    private double itemQuantity;
    
    @Column(name = "item_relase_quantity", length = 20)
    private double itemRelaseQuantity;
    
    @Column(name = "item_remaning_quantity", length = 20)
    private double itemRemaningQuantity;
    
    @Column(name = "item_dynamic_quantity", length = 20)
    private double itemDynamicQuantity;
    
    @Column(name = "item_amt", length = 50)
    private double itemAmt;
    
    @Column(name = "item_uom")
    private String itemUom;
    
    @Column(name = "item_desc" , length = 300)
    private String itemDesc;
    
    @Column(name = "status", length = 5)
    private int status;
    
    public ItemMasterModel() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public double getItemAmt() {
		return itemAmt;
	}

	public void setItemAmt(double itemAmt) {
		this.itemAmt = itemAmt;
	}

	public String getItemUom() {
		return itemUom;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public double getItemQuantity() {
		return itemQuantity;
	}

	public String getItemDesc() {
		return itemDesc;
	}

	public void setItemQuantity(double itemQuantity) {
		this.itemQuantity = itemQuantity;
	}

	public void setItemUom(String itemUom) {
		this.itemUom = itemUom;
	}

	public void setItemDesc(String itemDesc) {
		this.itemDesc = itemDesc;
	}

	public PoMasterModel getPoMasterDtl() {
		return poMasterDtl;
	}

	public void setPoMasterDtl(PoMasterModel poMasterDtl) {
		this.poMasterDtl = poMasterDtl;
	}

	public double getItemRelaseQuantity() {
		return itemRelaseQuantity;
	}

	public void setItemRelaseQuantity(double itemRelaseQuantity) {
		this.itemRelaseQuantity = itemRelaseQuantity;
	}

	public double getItemRemaningQuantity() {
		return itemRemaningQuantity;
	}

	public void setItemRemaningQuantity(double itemRemaningQuantity) {
		this.itemRemaningQuantity = itemRemaningQuantity;
	}

	public double getItemDynamicQuantity() {
		return itemDynamicQuantity;
	}

	public void setItemDynamicQuantity(double itemDynamicQuantity) {
		this.itemDynamicQuantity = itemDynamicQuantity;
	}
}
