package com.app.vps.admincontroller;

import java.io.File;
import java.io.FileInputStream;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.app.vps.admin.service.IAdminPoService;
import com.app.vps.basecontroller.BaseController;
import com.app.vps.bo.BaseResponseBO;
import com.app.vps.constant.Constants;
import com.app.vps.exception.ApplicationException;
import com.app.vps.message.IResponseCode;
import com.app.vps.message.IResponseMessage;
import com.app.vps.supplier.model.InvoiceMasterModel;
import com.app.vps.supplier.model.ItemMasterModel;
import com.app.vps.supplier.model.PoMasterModel;
import com.app.vps.supplier.model.RTVMasterModel;

@CrossOrigin(origins="*", allowedHeaders="*")
@RestController
public class AdminPoController extends BaseController{

	@Autowired
	IAdminPoService iAdminPoService;
	
	@GetMapping(value = Constants.IMappingConstants.ADMIN + Constants.IMappingConstants.PO + Constants.IMappingConstants.LIST)
	public ResponseEntity<BaseResponseBO> getAdminPoList(@RequestParam("poStatus") int poStatus,@RequestParam("poStatus2") int poStatus2) {
		List<PoMasterModel> adminPoList = null;
		try {
			adminPoList = iAdminPoService.getAdminPoList(poStatus,poStatus2);
			return getResponseModel(adminPoList, IResponseCode.SUCCESS, IResponseMessage.SUCCESS, null);

		} catch (ApplicationException e) {
			e.printStackTrace();
			return getResponseModel(null, e.getCode(), e.getMessage(), null);
		} catch (Exception e) {
			e.printStackTrace();
			return getResponseModel(null, IResponseCode.SERVER_ERROR, IResponseMessage.SERVER_ERROR, null);
		}
	}
	
	@GetMapping(value = Constants.IMappingConstants.ADMIN + Constants.IMappingConstants.PO+ Constants.IMappingConstants.COUNT)
	public ResponseEntity<BaseResponseBO> getAdminPoCount(@RequestParam("poStatus") int poStatus) {
		Object pendingPoCount = null;
		try {
			pendingPoCount = iAdminPoService.getAdminPoCount(poStatus);
			return getResponseModel(pendingPoCount, IResponseCode.SUCCESS, IResponseMessage.SUCCESS, null);

		} catch (ApplicationException e) {
			e.printStackTrace();
			return getResponseModel(null, e.getCode(), e.getMessage(), null);
		} catch (Exception e) {
			e.printStackTrace();
			return getResponseModel(null, IResponseCode.SERVER_ERROR, IResponseMessage.SERVER_ERROR, null);
		}
	}
	
	@GetMapping(value = Constants.IMappingConstants.ADMIN + Constants.IMappingConstants.INVOICE+ Constants.IMappingConstants.COUNT)
	public ResponseEntity<BaseResponseBO> getAdminInvoiceCount(@RequestParam("status") int status) {
		Object invoiceCount = null;
		try {
			invoiceCount = iAdminPoService.getAdminInvoiceCount(status);
			return getResponseModel(invoiceCount, IResponseCode.SUCCESS, IResponseMessage.SUCCESS, null);

		} catch (ApplicationException e) {
			e.printStackTrace();
			return getResponseModel(null, e.getCode(), e.getMessage(), null);
		} catch (Exception e) {
			e.printStackTrace();
			return getResponseModel(null, IResponseCode.SERVER_ERROR, IResponseMessage.SERVER_ERROR, null);
		}
	}
	
	@GetMapping(value = Constants.IMappingConstants.ADMIN + Constants.IMappingConstants.PO +Constants.IMappingConstants.DETAILS +Constants.IMappingConstants.BYID)
	public ResponseEntity<BaseResponseBO> getAdminPoDetail(@RequestParam("po_id") long po_id) {
		PoMasterModel poDetails = null;	
		try {
			poDetails = iAdminPoService.getAdminPoDetail(po_id);	          
			return getResponseModel(poDetails, IResponseCode.SUCCESS, IResponseMessage.SUCCESS, null);
		
		} catch (ApplicationException e) {
			e.printStackTrace();
			return getResponseModel(null, e.getCode(), e.getMessage(), null);
		} catch (Exception e) {
			e.printStackTrace();
			return getResponseModel(null, IResponseCode.SERVER_ERROR, IResponseMessage.SERVER_ERROR, null);
		}
	}
	@GetMapping(value = Constants.IMappingConstants.ITEM + Constants.IMappingConstants.PO + Constants.IMappingConstants.LIST)
	public ResponseEntity<BaseResponseBO> getAdminPoItemList(@RequestParam("poNo") String poNo) {
		List<ItemMasterModel> adminPoItemList = null;
		try {
			adminPoItemList = iAdminPoService.getAdminPoItemList(poNo);
			return getResponseModel(adminPoItemList, IResponseCode.SUCCESS, IResponseMessage.SUCCESS, null);

		} catch (ApplicationException e) {
			e.printStackTrace();
			return getResponseModel(null, e.getCode(), e.getMessage(), null);
		} catch (Exception e) {
			e.printStackTrace();
			return getResponseModel(null, IResponseCode.SERVER_ERROR, IResponseMessage.SERVER_ERROR, null);
		}
	}
	
	@GetMapping(value = Constants.IMappingConstants.INVOICE + Constants.IMappingConstants.ITEM + Constants.IMappingConstants.LIST)
	public ResponseEntity<BaseResponseBO> getInvoiceItemList(@RequestParam("poNo") String poNo,@RequestParam("status") int status) {
		List<ItemMasterModel> adminPoItemList = null;
		try {
			adminPoItemList = iAdminPoService.getInvoiceItemList(poNo,status);
			return getResponseModel(adminPoItemList, IResponseCode.SUCCESS, IResponseMessage.SUCCESS, null);

		} catch (ApplicationException e) {
			e.printStackTrace();
			return getResponseModel(null, e.getCode(), e.getMessage(), null);
		} catch (Exception e) {
			e.printStackTrace();
			return getResponseModel(null, IResponseCode.SERVER_ERROR, IResponseMessage.SERVER_ERROR, null);
		}
	}
	
	@GetMapping(value = Constants.IMappingConstants.INVOICE + Constants.IMappingConstants.MASTER + Constants.IMappingConstants.LIST)
	public ResponseEntity<BaseResponseBO> getInvoiceMasterList(@RequestParam("status") int status) {
		List<InvoiceMasterModel> invoiceMasterList = null;
		try {
			invoiceMasterList = iAdminPoService.getInvoiceMasterList(status);
			return getResponseModel(invoiceMasterList, IResponseCode.SUCCESS, IResponseMessage.SUCCESS, null);

		} catch (ApplicationException e) {
			e.printStackTrace();
			return getResponseModel(null, e.getCode(), e.getMessage(), null);
		} catch (Exception e) {
			e.printStackTrace();
			return getResponseModel(null, IResponseCode.SERVER_ERROR, IResponseMessage.SERVER_ERROR, null);
		}
	}
	
	@GetMapping(value = Constants.IMappingConstants.INVOICE + Constants.IMappingConstants.MASTER +Constants.IMappingConstants.DETAILS +Constants.IMappingConstants.BYINVOICENO)
	public ResponseEntity<BaseResponseBO> getAdminInvoiceDetail(@RequestParam("invoiceId") long invoiceId) {
		Optional<InvoiceMasterModel> invoiceDetails = null;	
		try {
			invoiceDetails = iAdminPoService.getAdminInvoiceDetail(invoiceId);	          
			return getResponseModel(invoiceDetails, IResponseCode.SUCCESS, IResponseMessage.SUCCESS, null);
		
		} catch (ApplicationException e) {
			e.printStackTrace();
			return getResponseModel(null, e.getCode(), e.getMessage(), null);
		} catch (Exception e) {
			e.printStackTrace();
			return getResponseModel(null, IResponseCode.SERVER_ERROR, IResponseMessage.SERVER_ERROR, null);
		}
	}
	
	@GetMapping(value = Constants.IMappingConstants.ADMIN + Constants.IMappingConstants.SUPPLIER_WISE + Constants.IMappingConstants.PO + Constants.IMappingConstants.LIST)
	public ResponseEntity<BaseResponseBO> getAdminSupplierWisePoList(@RequestParam("supplierCode") String supplierCode) {
		List<PoMasterModel> adminPoList = null;
		try {
			adminPoList = iAdminPoService.getAdminSupplierWisePoList(supplierCode);
			return getResponseModel(adminPoList, IResponseCode.SUCCESS, IResponseMessage.SUCCESS, null);

		} catch (ApplicationException e) {
			e.printStackTrace();
			return getResponseModel(null, e.getCode(), e.getMessage(), null);
		} catch (Exception e) {
			e.printStackTrace();
			return getResponseModel(null, IResponseCode.SERVER_ERROR, IResponseMessage.SERVER_ERROR, null);
		}
	}
	
	@GetMapping(value = "/fileInvoiceDownload/file")
	public ResponseEntity<Object> fileDownload(@RequestParam("file_name") String file_name,@RequestParam("file_path") String file_path) throws ApplicationException, Exception{
		try {
            String filePath = file_path;
            File file = new File(filePath);
            InputStreamResource resource = new InputStreamResource(new FileInputStream(filePath));
            String extension=file_name.substring(file_name.lastIndexOf("."));
            if(extension.equals(".pdf")) {
            	return ResponseEntity.ok()
    	            	.contentLength(file.length())
    			        .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + file.getName() + "\"")
    			        .contentType(MediaType.parseMediaType("application/pdf"))
    			        .body(resource);
            }else if(extension.equals(".png")) {
            	return ResponseEntity.ok()
    	            	.contentLength(file.length())
    			        .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + file.getName() + "\"")
    			        .contentType(MediaType.IMAGE_PNG)
    			        .body(resource);
            }else {
            	return ResponseEntity.ok()
    	            	.contentLength(file.length())
    			        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getName() + "\"")
    			        .body(resource);
            }
		}catch(Exception ex) {
			ex.getMessage();
		}
		return null;
	}
	
	@GetMapping(value = Constants.IMappingConstants.ADMIN + Constants.IMappingConstants.RTV + Constants.IMappingConstants.LIST)
	public ResponseEntity<BaseResponseBO> getAllRtvList() {
		List<RTVMasterModel> rtvList = null;
		try {
			rtvList = iAdminPoService.getAllRtvList();
			return getResponseModel(rtvList, IResponseCode.SUCCESS, IResponseMessage.SUCCESS, null);

		} catch (ApplicationException e) {
			e.printStackTrace();
			return getResponseModel(null, e.getCode(), e.getMessage(), null);
		} catch (Exception e) {
			e.printStackTrace();
			return getResponseModel(null, IResponseCode.SERVER_ERROR, IResponseMessage.SERVER_ERROR, null);
		}
	}
	
	@GetMapping(value = Constants.IMappingConstants.ADMIN + Constants.IMappingConstants.RTV+ Constants.IMappingConstants.COUNT)
	public ResponseEntity<BaseResponseBO> getAdminRtvCount() {
		Object rtvCount = null;
		try {
			rtvCount = iAdminPoService.getAdminRtvCount();
			return getResponseModel(rtvCount, IResponseCode.SUCCESS, IResponseMessage.SUCCESS, null);

		} catch (ApplicationException e) {
			e.printStackTrace();
			return getResponseModel(null, e.getCode(), e.getMessage(), null);
		} catch (Exception e) {
			e.printStackTrace();
			return getResponseModel(null, IResponseCode.SERVER_ERROR, IResponseMessage.SERVER_ERROR, null);
		}
	}
}
