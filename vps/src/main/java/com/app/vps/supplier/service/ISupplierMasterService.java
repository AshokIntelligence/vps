package com.app.vps.supplier.service;

import java.util.List;
import org.springframework.web.multipart.MultipartFile;
import com.app.vps.exception.ApplicationException;
import com.app.vps.supplier.model.InvoiceMasterModel;
import com.app.vps.supplier.model.ItemMasterModel;
import com.app.vps.supplier.model.ItemTransactionModel;
import com.app.vps.supplier.model.PoMasterModel;
import com.app.vps.supplier.model.RTVMasterModel;

public interface ISupplierMasterService {

	Object getTotalSupplierCount()throws ApplicationException, Exception;

	List<PoMasterModel> getSupplierPoCount(String suppliercode)throws ApplicationException, Exception;

	List<PoMasterModel> getSupplierPoList(int poStatus, String suppliercode)throws ApplicationException, Exception;

	void updateSupplierPoAction(PoMasterModel poMasterModel)throws ApplicationException, Exception;

	void insertInvoiceData(MultipartFile invoiceAttachment1, ItemMasterModel itemMasterModel, List<Long> itemIdList, InvoiceMasterModel invoiceMasterModel)throws ApplicationException, Exception;

	List<InvoiceMasterModel> getSupplierInvoiceMasterList(String suppliercode)throws ApplicationException, Exception;

	Object getSupplierInvoiceCount(int status, String suppliercode)throws ApplicationException, Exception;

	List<RTVMasterModel> getSupplierRtvMasterList(String suppliercode)throws ApplicationException, Exception;

	Object getSupplierRtvCount(String suppliercode)throws ApplicationException, Exception;

	List<RTVMasterModel> getSupplierRtvItemWiseList(String invoiceNo)throws ApplicationException, Exception;

	List<InvoiceMasterModel> getSupplierInvoicePreviousList(String poNumber)throws ApplicationException, Exception;

	void updateItemQuantity(long itemId, double itemQty)throws ApplicationException, Exception;

	List<ItemTransactionModel> getItemTranDetails(long tranId)throws ApplicationException, Exception;

}
