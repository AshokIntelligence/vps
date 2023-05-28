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
@Table(name = "vps_invoice_master")
public class InvoiceMasterModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "po_no", length = 30)
    private String poNo;
    @ManyToOne()
   	@JoinColumn(name = "po_no", referencedColumnName = "po_no" ,insertable = false, updatable = false )
   	private PoMasterModel poMasterDetails;
    
    @Column(name = "supplier_code", length = 30)
    private String supplierCode;
    
    @Column(name = "invoice_no", length = 50)
    private String invoiceNo;
    
    @Column(name = "invoice_date", length = 20)
    private String invoiceDate;
    
    @Column(name = "invoice_attachment", length = 100)
    private String invoiceAttachment;
    
    @Column(name = "qr_code_attachment", length = 100)
    private String qrCodeAttachment;

	public InvoiceMasterModel() {
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

	public String getInvoiceNo() {
		return invoiceNo;
	}

	public void setInvoiceNo(String invoiceNo) {
		this.invoiceNo = invoiceNo;
	}

	public String getInvoiceDate() {
		return invoiceDate;
	}

	public void setInvoiceDate(String invoiceDate) {
		this.invoiceDate = invoiceDate;
	}

	public String getInvoiceAttachment() {
		return invoiceAttachment;
	}

	public void setInvoiceAttachment(String invoiceAttachment) {
		this.invoiceAttachment = invoiceAttachment;
	}

	public String getQrCodeAttachment() {
		return qrCodeAttachment;
	}

	public void setQrCodeAttachment(String qrCodeAttachment) {
		this.qrCodeAttachment = qrCodeAttachment;
	}

	public String getSupplierCode() {
		return supplierCode;
	}

	public void setSupplierCode(String supplierCode) {
		this.supplierCode = supplierCode;
	}

	public PoMasterModel getPoMasterDetails() {
		return poMasterDetails;
	}

	public void setPoMasterDetails(PoMasterModel poMasterDetails) {
		this.poMasterDetails = poMasterDetails;
	}

	
}
