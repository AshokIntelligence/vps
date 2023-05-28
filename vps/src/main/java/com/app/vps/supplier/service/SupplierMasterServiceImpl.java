package com.app.vps.supplier.service;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import javax.xml.bind.DatatypeConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import com.app.vps.constant.Constants;
import com.app.vps.exception.ApplicationException;
import com.app.vps.login.model.LoginAccess;
import com.app.vps.login.repository.LoginAccessRepository;
import com.app.vps.login.repository.SupplierRepository;
import com.app.vps.mail.IMailService;
import com.app.vps.supplier.dao.PoMasterDao;
import com.app.vps.supplier.model.InvoiceMasterModel;
import com.app.vps.supplier.model.ItemMasterModel;
import com.app.vps.supplier.model.ItemTransactionModel;
import com.app.vps.supplier.model.PoMasterModel;
import com.app.vps.supplier.model.RTVMasterModel;
import com.app.vps.supplier.repository.InvoiceMasterRepository;
import com.app.vps.supplier.repository.ItemMasterRepository;
import com.app.vps.supplier.repository.ItemTranactionRepository;
import com.app.vps.supplier.repository.PoMasterRepository;
import com.app.vps.supplier.repository.RTVMasterRepository;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;

@EnableAsync
@Service
@Transactional(rollbackFor = {ApplicationException.class, Exception.class })
public class SupplierMasterServiceImpl implements ISupplierMasterService {
	
	@Autowired
	SupplierRepository supplierRepository;
	
	@Autowired
	PoMasterRepository poMasterRepository;
	
	@Autowired
	PoMasterDao poMasterDao;
	
	@Autowired
	ItemMasterRepository itemMasterRepository;
	
	@Autowired
	IMailService iMailService;
	
	@Autowired
	LoginAccessRepository loginAccessRepository;
	
	@Autowired
	RTVMasterRepository rtvMasterRepository;
	
	@Autowired
	ItemTranactionRepository itemTranactionRepository;
	
	@Autowired
	InvoiceMasterRepository invoiceMasterRepository;
	
	@Override
	public Object getTotalSupplierCount() throws ApplicationException, Exception {
		Object totalSupplierCount=null;
		totalSupplierCount= supplierRepository.getTotalSupplierCount();
		return totalSupplierCount;
	}

	@Override
	public List<PoMasterModel> getSupplierPoCount(String suppliercode) throws ApplicationException, Exception {
		return poMasterDao.getTotalSupplierPoCount(suppliercode);
	}

	@Override
	public List<PoMasterModel> getSupplierPoList(int poStatus, String suppliercode) throws ApplicationException, Exception {
		return  poMasterRepository.findByStatusAndSupplierCode(poStatus,suppliercode);
	}

	@Override
	public void updateSupplierPoAction(PoMasterModel poMasterModel) throws ApplicationException, Exception {
		Optional<PoMasterModel> updateAction= poMasterRepository.findByPoNo(poMasterModel.getPoNo());
		updateAction.get().setStatus(poMasterModel.getStatus());
		poMasterRepository.save(updateAction.get());
		
		List<LoginAccess> adminList = loginAccessRepository.findAll();
		String[] emailIds= new String[adminList.size()];
		int i=0;
		for(LoginAccess emailList:adminList){  
			emailIds[i++]= emailList.getUser().getEmployeeEmail();  
		   }  
		
		if(!updateAction.get().getSupplierDtl().getSupplierEmail().isEmpty()) {
			if(poMasterModel.getStatus()==1) {
				String to = updateAction.get().getSupplierDtl().getSupplierEmail();
				String cc = null;
				String msgSubject = "PO Number "+updateAction.get().getPoNo()+" is accepted by "+updateAction.get().getSupplierDtl().getSupplierName();
				String msgBody = "Dear Sir/Ma'am, <br>"
		 				 +" PO Number "+updateAction.get().getPoNo()+" is accepted by "+updateAction.get().getSupplierDtl().getSupplierName()+"\n"
		                 +"<table style='border:none;'><tr style='text-align:left;'><th colspan='2'><u>PO DETAILS: </u></th><tr>"
		                 +"<tr><td><b>PO NO.</b></td><td>"+updateAction.get().getPoNo()+"</td></tr>"
		                 +"<tr><td><b>PO DATE </b></td><td>"+updateAction.get().getPoDate()+"</td></tr>"
		                 +"<tr><td><b>APPROVED BY </b></td><td>"+updateAction.get().getSupplierDtl().getSupplierName()+"</td></tr>"
		                 +"</td></tr></table><br><br><b>Best Regards,</b><br>"
		                 +" "+updateAction.get().getSupplierDtl().getSupplierName()+"<br>";
		    	iMailService.sendMail(to, cc, emailIds, msgBody, msgSubject, Constants.IMailConstants.FROM);
			}else {
				String to = updateAction.get().getSupplierDtl().getSupplierEmail();
				String cc = null;
				String msgSubject = "PO Number "+updateAction.get().getPoNo()+" is rejected by "+updateAction.get().getSupplierDtl().getSupplierName();
				String msgBody = "Dear Sir/Ma'am, <br>"
		 				 +" PO Number "+updateAction.get().getPoNo()+" is rejected by "+updateAction.get().getSupplierDtl().getSupplierName()+"\n"
		                 +"<table style='border:none;'><tr style='text-align:left;'><th colspan='2'><u>PO DETAILS: </u></th><tr>"
		                 +"<tr><td><b>PO NO.</b></td><td>"+updateAction.get().getPoNo()+"</td></tr>"
		                 +"<tr><td><b>PO DATE </b></td><td>"+updateAction.get().getPoDate()+"</td></tr>"
		                 +"<tr><td><b>REJECTED BY </b></td><td>"+updateAction.get().getSupplierDtl().getSupplierName()+"</td></tr>"
		                 +"</td></tr></table><br><br><b>Best Regards,</b><br>"
		                 +" "+updateAction.get().getSupplierDtl().getSupplierName()+"<br>";
		    	iMailService.sendMail(to, cc, emailIds, msgBody, msgSubject, Constants.IMailConstants.FROM);
			}
		}
		
	}


	@Override
	public void insertInvoiceData(MultipartFile invoiceAttachment1, ItemMasterModel itemMasterModel,List<Long> itemIdList,InvoiceMasterModel invoiceMasterModel) throws ApplicationException, Exception {
		LocalDate today = LocalDate.now();
		String pattern = "hh:mm a";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		String time = simpleDateFormat.format(new Date());
		String target=null; 
		String target1=null; 
		if(!invoiceAttachment1.isEmpty()) {
		    target=Constants.IFileConstants.UPLOAD_PATH+"/invoice/"+invoiceMasterModel.getInvoiceNo()+"/";
		    target1=Constants.IFileConstants.UPLOAD_PATH+"/invoice/"+invoiceMasterModel.getInvoiceNo()+"/"+invoiceAttachment1.getOriginalFilename();
			File file = new File(target);
			file.setReadable(true, false);
			file.setExecutable(true, false);
			file.setWritable(true, false);
			if(!file.exists()) {
			file.mkdirs();
			}
			file.setReadable(true, false);
			file.setExecutable(true, false);
			file.setWritable(true, false);
			BufferedInputStream bufferedInputStream = new BufferedInputStream(invoiceAttachment1.getInputStream());
			OutputStream outputStream = new FileOutputStream(target+invoiceAttachment1.getOriginalFilename());
			byte[] buffer = new byte[4 * 1024];
	        int read;
	        while ((read = bufferedInputStream.read(buffer, 0, buffer.length)) != -1) {
	            outputStream.write(buffer, 0, read);
	        }
	        bufferedInputStream.close();
	        outputStream.close();
	        outputStream.flush();
			}
		int i=0;
		Optional<PoMasterModel> poDetails = poMasterRepository.findByPoNo(itemMasterModel.getPoNo());
		InvoiceMasterModel invoiceMasterSave = new InvoiceMasterModel();
		invoiceMasterSave.setInvoiceAttachment(target1);
		invoiceMasterSave.setInvoiceDate(invoiceMasterModel.getInvoiceDate());
		invoiceMasterSave.setInvoiceNo(invoiceMasterModel.getInvoiceNo());
		invoiceMasterSave.setPoNo(itemMasterModel.getPoNo());
		invoiceMasterSave.setSupplierCode(poDetails.get().getSupplierCode());
		InvoiceMasterModel invoiceId=invoiceMasterRepository.save(invoiceMasterSave);
		
		List<String> item_list = new ArrayList();
		String target4=Constants.IFileConstants.UPLOAD_PATH+"/QRCODE/"+invoiceMasterModel.getInvoiceNo()+"/"+invoiceMasterModel.getInvoiceNo()+"_qrCode.png";
		String pono=null;
		for(i=0;i<itemIdList.size();i++) {
			Optional<ItemMasterModel> updateItemStatus=itemMasterRepository.findById(itemIdList.get(i));
			updateItemStatus.get().setItemRelaseQuantity(updateItemStatus.get().getItemDynamicQuantity()+updateItemStatus.get().getItemRelaseQuantity());
			updateItemStatus.get().setItemRemaningQuantity(updateItemStatus.get().getItemQuantity()-updateItemStatus.get().getItemRelaseQuantity());
			itemMasterRepository.save(updateItemStatus.get());
			
			ItemTransactionModel itemTransactionSave = new ItemTransactionModel();
			itemTransactionSave.setInvoiceId(invoiceId.getId());
			itemTransactionSave.setItemAmt(updateItemStatus.get().getItemAmt());
			itemTransactionSave.setItemCode(updateItemStatus.get().getItemCode());
			itemTransactionSave.setItemDesc(updateItemStatus.get().getItemDesc());
			itemTransactionSave.setItemQuantity(updateItemStatus.get().getItemDynamicQuantity());
			itemTransactionSave.setItemUom(updateItemStatus.get().getItemUom());
			itemTransactionSave.setPoNo(updateItemStatus.get().getPoNo());
			itemTransactionSave.setRegDate(today.toString());
			itemTransactionSave.setRegTime(time);
			itemTransactionSave.setStatus(1);
			itemTranactionRepository.save(itemTransactionSave);
		}
		
		List<ItemTransactionModel> checkItemTranStatus=itemTranactionRepository.findByInvoiceId(invoiceId.getId());
		int k=0;
		for(k=0;k<checkItemTranStatus.size();k++) {
			pono=checkItemTranStatus.get(k).getPoNo();
			item_list.add("\n\t\t\t\t\t\t\t\t{\n\t\t\t\t\t\t\t\t\t\"MatCode\":\""+checkItemTranStatus.get(k).getItemCode()+",\n"
					+ "\t\t\t\t\t\t\t\t\t\"Qty\":"+checkItemTranStatus.get(k).getItemQuantity()+"\n\t\t\t\t\t\t\t\t\t}");
		}
		
		List<ItemMasterModel> updateItemStatus=itemMasterRepository.findByPoNo(itemMasterModel.getPoNo());
		int m=0;
		for(m=0;m<updateItemStatus.size();m++) {
			updateItemStatus.get(m).setItemDynamicQuantity(updateItemStatus.get(m).getItemRemaningQuantity());
			if(updateItemStatus.get(m).getItemRelaseQuantity() >= updateItemStatus.get(m).getItemQuantity()) {
				updateItemStatus.get(m).setStatus(1);
				updateItemStatus.get(m).setItemDynamicQuantity(updateItemStatus.get(m).getItemRemaningQuantity());
			}
		}
		
		List<ItemMasterModel> checkItemStatus=itemMasterRepository.findByPoNoAndStatus(itemMasterModel.getPoNo(),(int)0);
			if(checkItemStatus.size()==0) {
				Optional<PoMasterModel> updatePoStatus=poMasterRepository.findByPoNo(itemMasterModel.getPoNo());
				updatePoStatus.get().setStatus((int)4);
				poMasterRepository.save(updatePoStatus.get());
			}else {
				Optional<PoMasterModel> updatePoStatus=poMasterRepository.findByPoNo(itemMasterModel.getPoNo());
				updatePoStatus.get().setStatus((int)3);
				poMasterRepository.save(updatePoStatus.get());
			}
		
		String convertItemList1=item_list.toString();
		String convertItemList2=convertItemList1.replace("[", "");
		String convertItemList=convertItemList2.replace("]", "");
		SimpleDateFormat oldFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = oldFormat.parse(invoiceMasterModel.getInvoiceDate());
		SimpleDateFormat newFormat = new SimpleDateFormat("dd.MM.yyyy");
		String output = newFormat.format(date);
		 String invoiceData = "{\n\t\t\"InvoiceNo\" : \""+invoiceMasterModel.getInvoiceNo()+"\",\n\t\t\"InvoiceDt\":\""+output+"\",\n\t\t\"ReleaseList\":[ "
		 		+ "\n\t\t\t\t{\n\t\t\t\t\t\t\"ReleaseNo\":\""+itemMasterModel.getPoNo()+"\","
		 		+ "\n\t\t\t\t\t\t\"ItemList\":["+convertItemList+"\n\t\t\t\t\t\t\t\t]\n\t\t\t\t\t}\n\t\t]\n}";

	        int imageSize = 400;
	        BitMatrix matrix = new MultiFormatWriter().encode(invoiceData, BarcodeFormat.QR_CODE, imageSize, imageSize);
	        ByteArrayOutputStream bos = new ByteArrayOutputStream();
	        MatrixToImageWriter.writeToStream(matrix, "png", bos);
	        String image = Base64.getEncoder().encodeToString(bos.toByteArray()); // base64 encode

	        byte[] data = DatatypeConverter.parseBase64Binary(image);
	        String target2=Constants.IFileConstants.UPLOAD_PATH+"/QRCODE/"+invoiceMasterModel.getInvoiceNo();
	        File file = new File(target2);
	        file.setReadable(true, false);
			file.setExecutable(true, false);
			file.setWritable(true, false);
			if(!file.exists()) {
			file.mkdirs();
			}
			file.setReadable(true, false);
			file.setExecutable(true, false);
			file.setWritable(true, false);
			String target3=target2+"/"+invoiceMasterModel.getInvoiceNo()+"_qrCode.png";
			File file1 = new File(target3);
	        try (OutputStream outputStream = new BufferedOutputStream(new FileOutputStream(file1))) {
	            outputStream.write(data);
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	        
	        Optional<InvoiceMasterModel> updateQrAttach=invoiceMasterRepository.findById(invoiceId.getId());
	        if(updateQrAttach.isPresent()) {
	        	updateQrAttach.get().setQrCodeAttachment(target3);
	        	invoiceMasterRepository.save(updateQrAttach.get());
	        }
	        
	        Optional<PoMasterModel> updatePo=poMasterRepository.findByPoNo(itemMasterModel.getPoNo());
	        
	        List<LoginAccess> adminList = loginAccessRepository.findAll();
			String[] emailIds= new String[adminList.size()];
			int j=0;
			for(LoginAccess emailList:adminList){  
				emailIds[j++]= emailList.getUser().getEmployeeEmail();  
			   }  
	        
				String to = updatePo.get().getSupplierDtl().getSupplierEmail();
				String cc = null;
				String msgSubject = "Invoice No. "+invoiceMasterModel.getInvoiceNo()+" is Submitted by "+updatePo.get().getSupplierDtl().getSupplierName();
				String msgBody = "Dear Sir/Ma'am, <br>"
		 				 +"Invoice No. "+invoiceMasterModel.getInvoiceNo()+" is Submitted by "+updatePo.get().getSupplierDtl().getSupplierName()+"\n"
		                 +"<table style='border:none;'><tr style='text-align:left;'><th colspan='2'><u>INVOICE DETAILS: </u></th><tr>"
		                 +"<tr><td><b>PO NO.</b></td><td>"+updatePo.get().getPoNo()+"</td></tr>"
		                 +"<tr><td><b>PO DATE </b></td><td>"+updatePo.get().getPoDate()+"</td></tr>"
		                 +"<tr><td><b>INVOICE NO.</b></td><td>"+invoiceMasterModel.getInvoiceNo()+"</td></tr>"
		                 +"<tr><td><b>INVOICE DATE</b></td><td>"+invoiceMasterModel.getInvoiceDate()+"</td></tr>"
		                 +"<tr><td><b>INVOICE QUANTITY</b></td><td>"+itemMasterModel.getItemQuantity()+"</td></tr>"
		                 +"<tr><td><b>SUBMITTED BY </b></td><td>"+updatePo.get().getSupplierDtl().getSupplierName()+"</td></tr>"
		                 +"</td></tr></table><br><br><b>Best Regards,</b><br>"
		                 +" "+updatePo.get().getSupplierDtl().getSupplierName()+"<br>";
		    	iMailService.sendMail(to, cc, emailIds, msgBody, msgSubject, Constants.IMailConstants.FROM);
		
	}

	@Override
	public List<InvoiceMasterModel> getSupplierInvoiceMasterList(String suppliercode)
			throws ApplicationException, Exception {
//		return poMasterDao.getSupplierInvoiceMasterList(status,suppliercode);
		return invoiceMasterRepository.findBySupplierCode(suppliercode);
	}

	@Override
	public Object getSupplierInvoiceCount(int status, String suppliercode) throws ApplicationException, Exception {
		Object invoiceCount=null;
		invoiceCount= poMasterRepository.getSupplierInvoiceCount(suppliercode);
		return invoiceCount;
	}

	@Override
	public List<RTVMasterModel> getSupplierRtvMasterList(String suppliercode) throws ApplicationException, Exception {
		return rtvMasterRepository.findByVendorCode(suppliercode);
	}

	@Override
	public Object getSupplierRtvCount(String suppliercode) throws ApplicationException, Exception {
		Object totalRtvCount=null;
		totalRtvCount= rtvMasterRepository.getSupplierRtvCount(suppliercode);
		return totalRtvCount;
	}

	@Override
	public List<RTVMasterModel> getSupplierRtvItemWiseList(String invoiceNo) throws ApplicationException, Exception {
		List<InvoiceMasterModel> invoId=invoiceMasterRepository.findByInvoiceNo(invoiceNo);
		List<ItemTransactionModel> itemList = itemTranactionRepository.findByInvoiceId(invoId.get(0).getId());
		List<String> multiItemList = new ArrayList<String>();
		for(ItemTransactionModel itemPoList:itemList){
			multiItemList.add(itemPoList.getItemCode());
		}
		return poMasterDao.getRtvListItemWise(multiItemList);
	}

	@Override
	public List<InvoiceMasterModel> getSupplierInvoicePreviousList(String poNumber)
			throws ApplicationException, Exception {
//		return poMasterDao.getSupplierInvoicePreviousList(status,poNumber);
		return invoiceMasterRepository.findByPoNo(poNumber);
	}

	@Override
	public void updateItemQuantity(long itemId, double itemQty) throws ApplicationException, Exception {
		Optional<ItemMasterModel> updateItemQty=itemMasterRepository.findById(itemId);
		updateItemQty.get().setItemDynamicQuantity(itemQty);
	}

	@Override
	public List<ItemTransactionModel> getItemTranDetails(long tranId) throws ApplicationException, Exception {
		return itemTranactionRepository.findByInvoiceId(tranId);
	}
	
	


}
