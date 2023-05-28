package com.app.vps.login.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.app.vps.basecontroller.BaseController;
import com.app.vps.bo.BaseResponseBO;
import com.app.vps.constant.Constants;
import com.app.vps.exception.ApplicationException;
import com.app.vps.login.model.LoginSupplier;
import com.app.vps.login.service.ISupplierService;
import com.app.vps.message.IResponseCode;
import com.app.vps.message.IResponseMessage;

@CrossOrigin(origins="*", allowedHeaders="*") 
@RestController
public class SupplierLoginController extends BaseController  {

	@Autowired
	ISupplierService iSupplierService;
	
	@Autowired
	LoginSupplier loginSupplier;
	
	@GetMapping(value = Constants.IMappingConstants.SUPPLIER+Constants.IMappingConstants.LOGIN)
	public ResponseEntity<BaseResponseBO> login(@RequestParam("supplier_code") String supplier_code,@RequestParam("supplier_pass") String supplier_pass) throws ApplicationException {
		LoginSupplier loginSupplier = new LoginSupplier();
		try {
			loginSupplier = (LoginSupplier) iSupplierService.loadUserByUsername(String.valueOf(supplier_code),String.valueOf(supplier_pass));
			return getResponseModel(loginSupplier, IResponseCode.SUCCESS, IResponseMessage.SUCCESS, null);
		} catch (Exception e) {
			e.printStackTrace();
			return getResponseModel(null, IResponseCode.UNAUTHORIZED, IResponseMessage.UNAUTHORIZED_USER, null);
		}
	}
}
