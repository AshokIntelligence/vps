package com.app.vps.supplier.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.app.vps.basecontroller.BaseController;
import com.app.vps.bo.BaseResponseBO;
import com.app.vps.constant.Constants;
import com.app.vps.exception.ApplicationException;
import com.app.vps.login.model.SupplierModel;
import com.app.vps.message.IResponseCode;
import com.app.vps.message.IResponseMessage;
import com.app.vps.supplier.service.ISupplierProfileService;

@CrossOrigin(origins="*", allowedHeaders="*")
@RestController
public class SupplierProfileController extends BaseController{

	@Autowired
	ISupplierProfileService iSupplierProfileService;
	
	@GetMapping(value = Constants.IMappingConstants.SUPPLIER + Constants.IMappingConstants.DETAILS + Constants.IMappingConstants.GETBYSUPPLIERCODE)
	public ResponseEntity<BaseResponseBO> getSupplierDetailsBySupplierCode(@RequestParam("supplier_code") String supplier_code) throws ApplicationException {
		SupplierModel supplierDetails = null;	
		try {
			supplierDetails = iSupplierProfileService.getSupplierDetailsBySupplier(supplier_code);	          
			return getResponseModel(supplierDetails, IResponseCode.SUCCESS, IResponseMessage.SUCCESS, null);
		} catch (Exception e) {
			e.printStackTrace();
			return getResponseModel(null, IResponseCode.SERVER_ERROR, IResponseMessage.SERVER_ERROR, null);
		}
	}
	
	@PutMapping(value = Constants.IMappingConstants.SUPPLIER + Constants.IMappingConstants.PROFILE + Constants.IMappingConstants.UPDATE)
	public ResponseEntity<BaseResponseBO> updateSupplierProfile(SupplierModel supplierModel) {
		try {
			iSupplierProfileService.updateSupplierProfile(supplierModel);
			return getResponseModel(null, IResponseCode.SUCCESS, IResponseMessage.PROFILE_UPDATED_SUCCESSFULLY, null);
		} catch (ApplicationException e) {
			e.printStackTrace();
			return getResponseModel(null, e.getCode(), e.getMessage(), null);
		} catch (Exception e) {
			e.printStackTrace();
			return getResponseModel(null, IResponseCode.SERVER_ERROR, IResponseMessage.SERVER_ERROR, null);
		}
	}
	
	@PutMapping(value = Constants.IMappingConstants.SUPPLIER + Constants.IMappingConstants.PASSWORD + Constants.IMappingConstants.UPDATE)
	public ResponseEntity<BaseResponseBO> updateSupplierPassword(SupplierModel supplierModel) {
		try {
			iSupplierProfileService.updateSupplierPassword(supplierModel);
			return getResponseModel(null, IResponseCode.SUCCESS, IResponseMessage.PASSWORD_UPDATED_SUCCESSFULLY, null);
		} catch (ApplicationException e) {
			e.printStackTrace();
			return getResponseModel(null, e.getCode(), e.getMessage(), null);
		} catch (Exception e) {
			e.printStackTrace();
			return getResponseModel(null, IResponseCode.SERVER_ERROR, IResponseMessage.SERVER_ERROR, null);
		}
	}
	
	@PutMapping(value = Constants.IMappingConstants.SUPPLIER + Constants.IMappingConstants.COMMENT + Constants.IMappingConstants.UPDATE)
	public ResponseEntity<BaseResponseBO> updateSupplierComment(SupplierModel supplierModel) {
		try {
			iSupplierProfileService.updateSupplierComment(supplierModel);
			return getResponseModel(null, IResponseCode.SUCCESS, IResponseMessage.PASSWORD_UPDATED_SUCCESSFULLY, null);
		} catch (ApplicationException e) {
			e.printStackTrace();
			return getResponseModel(null, e.getCode(), e.getMessage(), null);
		} catch (Exception e) {
			e.printStackTrace();
			return getResponseModel(null, IResponseCode.SERVER_ERROR, IResponseMessage.SERVER_ERROR, null);
		}
	}
	
	@GetMapping(value = Constants.IMappingConstants.SUPPLIER + Constants.IMappingConstants.ALL + Constants.IMappingConstants.LIST)
	public ResponseEntity<BaseResponseBO> getSupplierAllList() {
		List<SupplierModel> supplierAllList = null;
		try {
			supplierAllList = iSupplierProfileService.getSupplierAllList();
			return getResponseModel(supplierAllList, IResponseCode.SUCCESS, IResponseMessage.SUCCESS, null);

		} catch (ApplicationException e) {
			e.printStackTrace();
			return getResponseModel(null, e.getCode(), e.getMessage(), null);
		} catch (Exception e) {
			e.printStackTrace();
			return getResponseModel(null, IResponseCode.SERVER_ERROR, IResponseMessage.SERVER_ERROR, null);
		}
	}
	
	@GetMapping(value = Constants.IMappingConstants.SUPPLIER + Constants.IMappingConstants.EMAIL + Constants.IMappingConstants.FORGATE)
	public ResponseEntity<BaseResponseBO> sendForgatePassUrl(@RequestParam("supplier_code") String supplier_code,@RequestParam("supplier_code2") String supplier_code2,@RequestParam("base_url") String base_url) {
		try {
			iSupplierProfileService.sendForgatePassUrl(supplier_code,supplier_code2,base_url);
			return getResponseModel(null, IResponseCode.SUCCESS, IResponseMessage.PASSWORD_UPDATED_SUCCESSFULLY, null);
		} catch (ApplicationException e) {
			e.printStackTrace();
			return getResponseModel(null, e.getCode(), e.getMessage(), null);
		} catch (Exception e) {
			e.printStackTrace();
			return getResponseModel(null, IResponseCode.SERVER_ERROR, IResponseMessage.SERVER_ERROR, null);
		}
	}
}
