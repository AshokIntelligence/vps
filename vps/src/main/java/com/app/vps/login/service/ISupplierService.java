package com.app.vps.login.service;

import com.app.vps.exception.ApplicationException;
import com.app.vps.login.model.LoginSupplier;

public interface ISupplierService {

	LoginSupplier loadUserByUsername(String supplier_code, String supplier_pass)throws ApplicationException, Exception;

}
