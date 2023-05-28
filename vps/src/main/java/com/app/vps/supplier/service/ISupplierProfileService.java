package com.app.vps.supplier.service;

import java.util.List;

import com.app.vps.exception.ApplicationException;
import com.app.vps.login.model.SupplierModel;

public interface ISupplierProfileService {

	SupplierModel getSupplierDetailsBySupplier(String supplier_code)throws ApplicationException, Exception;

	void updateSupplierProfile(SupplierModel supplierModel)throws ApplicationException, Exception;

	void updateSupplierPassword(SupplierModel supplierModel)throws ApplicationException, Exception;

	List<SupplierModel> getSupplierAllList()throws ApplicationException, Exception;

	void updateSupplierComment(SupplierModel supplierModel)throws ApplicationException, Exception;

	void sendForgatePassUrl(String supplier_code, String base_url, String base_url2)throws ApplicationException, Exception;


}
