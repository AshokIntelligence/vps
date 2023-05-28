package com.app.vps.supplier.controller;

import java.io.IOException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.app.vps.basecontroller.BaseController;
import com.app.vps.bo.BaseResponseBO;
import com.app.vps.constant.Constants;
import com.app.vps.exception.ApplicationException;
import com.app.vps.message.IResponseCode;
import com.app.vps.message.IResponseMessage;
import com.app.vps.supplier.model.InvoiceMasterModel;
import com.app.vps.supplier.model.ItemMasterModel;
import com.app.vps.supplier.model.ItemTransactionModel;
import com.app.vps.supplier.model.PoMasterModel;
import com.app.vps.supplier.model.RTVMasterModel;
import com.app.vps.supplier.service.ISupplierMasterService;

@CrossOrigin(origins="*", allowedHeaders="*")
@RestController
public class SupplierMasterController extends BaseController{

	@Autowired
	ISupplierMasterService iSupplierMasterService;
	
	@GetMapping(value = Constants.IMappingConstants.TOTAL + Constants.IMappingConstants.SUPPLIER+ Constants.IMappingConstants.COUNT)
	public ResponseEntity<BaseResponseBO> getTotalSupplierCount() {
		Object totalSupplierCount = null;
		try {
			totalSupplierCount = iSupplierMasterService.getTotalSupplierCount();
			return getResponseModel(totalSupplierCount, IResponseCode.SUCCESS, IResponseMessage.SUCCESS, null);

		} catch (ApplicationException e) {
			e.printStackTrace();
			return getResponseModel(null, e.getCode(), e.getMessage(), null);
		} catch (Exception e) {
			e.printStackTrace();
			return getResponseModel(null, IResponseCode.SERVER_ERROR, IResponseMessage.SERVER_ERROR, null);
		}
	}
	
	@GetMapping(value = Constants.IMappingConstants.SUPPLIER + Constants.IMappingConstants.PO + Constants.IMappingConstants.COUNT)
	public ResponseEntity<BaseResponseBO> getSupplierPoCount(@RequestParam("suppliercode") String suppliercode) {
		List<PoMasterModel> supplierPoCount = null;	
		try {
			supplierPoCount = iSupplierMasterService.getSupplierPoCount(suppliercode);
			return getResponseModel(supplierPoCount, IResponseCode.SUCCESS, IResponseMessage.SUCCESS, null);

		} catch (ApplicationException e) {
			e.printStackTrace();
			return getResponseModel(null, e.getCode(), e.getMessage(), null);
		} catch (Exception e) {
			e.printStackTrace();
			return getResponseModel(null, IResponseCode.SERVER_ERROR, IResponseMessage.SERVER_ERROR, null);
		}
	}
	
	@GetMapping(value = Constants.IMappingConstants.SUPPLIER + Constants.IMappingConstants.PO + Constants.IMappingConstants.LIST)
	public ResponseEntity<BaseResponseBO> getSupplierPoList(@RequestParam("poStatus") int poStatus,@RequestParam("suppliercode") String suppliercode) {
		List<PoMasterModel> supplierPoList = null;
		try {
			supplierPoList = iSupplierMasterService.getSupplierPoList(poStatus,suppliercode);
			return getResponseModel(supplierPoList, IResponseCode.SUCCESS, IResponseMessage.SUCCESS, null);

		} catch (ApplicationException e) {
			e.printStackTrace();
			return getResponseModel(null, e.getCode(), e.getMessage(), null);
		} catch (Exception e) {
			e.printStackTrace();
			return getResponseModel(null, IResponseCode.SERVER_ERROR, IResponseMessage.SERVER_ERROR, null);
		}
	}
	
	@GetMapping(value = Constants.IMappingConstants.SUPPLIER + Constants.IMappingConstants.ACTION+ Constants.IMappingConstants.UPDATE)
	public ResponseEntity<BaseResponseBO> updateSupplierPoAction(PoMasterModel poMasterModel) {
		try {
			
			iSupplierMasterService.updateSupplierPoAction(poMasterModel);
			return getResponseModel(null, IResponseCode.SUCCESS, IResponseMessage.SUCCESS, null);
		} catch (ApplicationException e) {
			e.printStackTrace();
			return getResponseModel(null, e.getCode(), e.getMessage(), null);
		} catch (Exception e) {
			e.printStackTrace();
			return getResponseModel(null, IResponseCode.SERVER_ERROR, IResponseMessage.SERVER_ERROR, null);
		}
	}
	
	@PostMapping(value = Constants.IMappingConstants.INVOICE + Constants.IMappingConstants.DETAILS + Constants.IMappingConstants.SAVE)
	@ResponseBody
	public ResponseEntity<BaseResponseBO> insertInvoiceData(@ModelAttribute("frm_invoice") ItemMasterModel itemMasterModel,InvoiceMasterModel invoiceMasterModel,@RequestParam("itemIdList[]") List<Long> itemIdList,
			@RequestParam(value="invoiceAttachment1" , required = false) MultipartFile invoiceAttachment1) throws ApplicationException, IOException {
		try {
			iSupplierMasterService.insertInvoiceData(invoiceAttachment1,itemMasterModel,itemIdList,invoiceMasterModel);
				return getResponseModel(null, IResponseCode.SUCCESS, IResponseMessage.INVOICE_DETAILS_SAVE_SUCCESSFULLY,null);	   
			}
		catch (ApplicationException e) {
			e.printStackTrace();
			return getResponseModel(null, e.getCode(), e.getMessage(), null);
		}catch(Exception e) {
				e.printStackTrace();
				return getResponseModel(null, IResponseCode.SERVER_ERROR, IResponseMessage.SERVER_ERROR, null);
			}		
		}
	

	@GetMapping(value = Constants.IMappingConstants.SUPPLIER + Constants.IMappingConstants.INVOICE + Constants.IMappingConstants.MASTER + Constants.IMappingConstants.LIST)
	public ResponseEntity<BaseResponseBO> getSupplierInvoiceMasterList(@RequestParam("suppliercode") String suppliercode) {
		List<InvoiceMasterModel> supplierInvoiceMasterList = null;
		try {
			supplierInvoiceMasterList = iSupplierMasterService.getSupplierInvoiceMasterList(suppliercode);
			return getResponseModel(supplierInvoiceMasterList, IResponseCode.SUCCESS, IResponseMessage.SUCCESS, null);

		} catch (ApplicationException e) {
			e.printStackTrace();
			return getResponseModel(null, e.getCode(), e.getMessage(), null);
		} catch (Exception e) {
			e.printStackTrace();
			return getResponseModel(null, IResponseCode.SERVER_ERROR, IResponseMessage.SERVER_ERROR, null);
		}
	}
	
	@GetMapping(value = Constants.IMappingConstants.SUPPLIER + Constants.IMappingConstants.INVOICE + Constants.IMappingConstants.PRIVIOUS + Constants.IMappingConstants.LIST)
	public ResponseEntity<BaseResponseBO> getSupplierInvoicePreviousList(@RequestParam("poNumber") String poNumber) {
		List<InvoiceMasterModel> supplierInvoicePreviousList = null;
		try {
			supplierInvoicePreviousList = iSupplierMasterService.getSupplierInvoicePreviousList(poNumber);
			return getResponseModel(supplierInvoicePreviousList, IResponseCode.SUCCESS, IResponseMessage.SUCCESS, null);

		} catch (ApplicationException e) {
			e.printStackTrace();
			return getResponseModel(null, e.getCode(), e.getMessage(), null);
		} catch (Exception e) {
			e.printStackTrace();
			return getResponseModel(null, IResponseCode.SERVER_ERROR, IResponseMessage.SERVER_ERROR, null);
		}
	}
	
	
	@GetMapping(value = Constants.IMappingConstants.SUPPLIER + Constants.IMappingConstants.INVOICE+ Constants.IMappingConstants.COUNT)
	public ResponseEntity<BaseResponseBO> getSupplierInvoiceCount(@RequestParam("status") int status,@RequestParam("suppliercode") String suppliercode) {
		Object invoiceCount = null;
		try {
			invoiceCount = iSupplierMasterService.getSupplierInvoiceCount(status,suppliercode);
			return getResponseModel(invoiceCount, IResponseCode.SUCCESS, IResponseMessage.SUCCESS, null);

		} catch (ApplicationException e) {
			e.printStackTrace();
			return getResponseModel(null, e.getCode(), e.getMessage(), null);
		} catch (Exception e) {
			e.printStackTrace();
			return getResponseModel(null, IResponseCode.SERVER_ERROR, IResponseMessage.SERVER_ERROR, null);
		}
	}
	
	@GetMapping(value = Constants.IMappingConstants.SUPPLIER + Constants.IMappingConstants.RTV + Constants.IMappingConstants.MASTER + Constants.IMappingConstants.LIST)
	public ResponseEntity<BaseResponseBO> getSupplierRtvMasterList(@RequestParam("suppliercode") String suppliercode) {
		List<RTVMasterModel> supplierRtvMasterList = null;
		try {
			supplierRtvMasterList = iSupplierMasterService.getSupplierRtvMasterList(suppliercode);
			return getResponseModel(supplierRtvMasterList, IResponseCode.SUCCESS, IResponseMessage.SUCCESS, null);

		} catch (ApplicationException e) {
			e.printStackTrace();
			return getResponseModel(null, e.getCode(), e.getMessage(), null);
		} catch (Exception e) {
			e.printStackTrace();
			return getResponseModel(null, IResponseCode.SERVER_ERROR, IResponseMessage.SERVER_ERROR, null);
		}
	}
	
	@GetMapping(value = Constants.IMappingConstants.SUPPLIER + Constants.IMappingConstants.RTV+ Constants.IMappingConstants.COUNT)
	public ResponseEntity<BaseResponseBO> getSupplierRtvCount(@RequestParam("suppliercode") String suppliercode) {
		Object rtvCount = null;
		try {
			rtvCount = iSupplierMasterService.getSupplierRtvCount(suppliercode);
			return getResponseModel(rtvCount, IResponseCode.SUCCESS, IResponseMessage.SUCCESS, null);

		} catch (ApplicationException e) {
			e.printStackTrace();
			return getResponseModel(null, e.getCode(), e.getMessage(), null);
		} catch (Exception e) {
			e.printStackTrace();
			return getResponseModel(null, IResponseCode.SERVER_ERROR, IResponseMessage.SERVER_ERROR, null);
		}
	}
	
	@GetMapping(value = Constants.IMappingConstants.SUPPLIER + Constants.IMappingConstants.RTV + Constants.IMappingConstants.ITEM_WISE + Constants.IMappingConstants.LIST)
	public ResponseEntity<BaseResponseBO> getSupplierRtvItemWiseList(@RequestParam("invoiceNo") String invoiceNo) {
		List<RTVMasterModel> supplierRtvItemWiseList = null;
		try {
			supplierRtvItemWiseList = iSupplierMasterService.getSupplierRtvItemWiseList(invoiceNo);
			return getResponseModel(supplierRtvItemWiseList, IResponseCode.SUCCESS, IResponseMessage.SUCCESS, null);

		} catch (ApplicationException e) {
			e.printStackTrace();
			return getResponseModel(null, e.getCode(), e.getMessage(), null);
		} catch (Exception e) {
			e.printStackTrace();
			return getResponseModel(null, IResponseCode.SERVER_ERROR, IResponseMessage.SERVER_ERROR, null);
		}
	}
	
	@PostMapping(value = Constants.IMappingConstants.ITEM + Constants.IMappingConstants.QUANTITY+ Constants.IMappingConstants.UPDATE)
	public ResponseEntity<BaseResponseBO> updateItemQuantity(@RequestParam("itemId") long itemId,@RequestParam("itemQty") double itemQty) {
		try {
			
			iSupplierMasterService.updateItemQuantity(itemId,itemQty);
			return getResponseModel(null, IResponseCode.SUCCESS, IResponseMessage.SUCCESS, null);
		} catch (ApplicationException e) {
			e.printStackTrace();
			return getResponseModel(null, e.getCode(), e.getMessage(), null);
		} catch (Exception e) {
			e.printStackTrace();
			return getResponseModel(null, IResponseCode.SERVER_ERROR, IResponseMessage.SERVER_ERROR, null);
		}
	}
	
	@GetMapping(value = Constants.IMappingConstants.ITEM + Constants.IMappingConstants.TRANACTION + Constants.IMappingConstants.DETAILS + Constants.IMappingConstants.LIST)
	public ResponseEntity<BaseResponseBO> getItemTranDetails(@RequestParam("tranId") long tranId) {
		List<ItemTransactionModel> itemTranId = null;
		try {
			itemTranId = iSupplierMasterService.getItemTranDetails(tranId);
			return getResponseModel(itemTranId, IResponseCode.SUCCESS, IResponseMessage.SUCCESS, null);

		} catch (ApplicationException e) {
			e.printStackTrace();
			return getResponseModel(null, e.getCode(), e.getMessage(), null);
		} catch (Exception e) {
			e.printStackTrace();
			return getResponseModel(null, IResponseCode.SERVER_ERROR, IResponseMessage.SERVER_ERROR, null);
		}
	}
	
}
