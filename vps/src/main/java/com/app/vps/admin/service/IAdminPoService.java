package com.app.vps.admin.service;

import java.util.List;
import java.util.Optional;

import com.app.vps.exception.ApplicationException;
import com.app.vps.supplier.model.InvoiceMasterModel;
import com.app.vps.supplier.model.ItemMasterModel;
import com.app.vps.supplier.model.PoMasterModel;
import com.app.vps.supplier.model.RTVMasterModel;

public interface IAdminPoService {

	List<PoMasterModel> getAdminPoList(int poStatus, int poStatus2)throws ApplicationException, Exception;

	Object getAdminPoCount(int poStatus)throws ApplicationException, Exception;

	PoMasterModel getAdminPoDetail(long po_id)throws ApplicationException, Exception;

	List<ItemMasterModel> getAdminPoItemList(String poNo)throws ApplicationException, Exception;

	List<ItemMasterModel> getInvoiceItemList(String poNo, int status)throws ApplicationException, Exception;

	List<InvoiceMasterModel> getInvoiceMasterList(int status)throws ApplicationException, Exception;

	Object getAdminInvoiceCount(int status)throws ApplicationException, Exception;

	Optional<InvoiceMasterModel> getAdminInvoiceDetail(long invoiceId)throws ApplicationException, Exception;

	List<PoMasterModel> getAdminSupplierWisePoList(String supplierCode)throws ApplicationException, Exception;

	List<RTVMasterModel> getAllRtvList()throws ApplicationException, Exception;

	Object getAdminRtvCount()throws ApplicationException, Exception;


}
