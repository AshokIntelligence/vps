package com.app.vps.admin.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.app.vps.exception.ApplicationException;
import com.app.vps.supplier.dao.PoMasterDao;
import com.app.vps.supplier.model.InvoiceMasterModel;
import com.app.vps.supplier.model.ItemMasterModel;
import com.app.vps.supplier.model.PoMasterModel;
import com.app.vps.supplier.model.RTVMasterModel;
import com.app.vps.supplier.repository.InvoiceMasterRepository;
import com.app.vps.supplier.repository.ItemMasterRepository;
import com.app.vps.supplier.repository.PoMasterRepository;
import com.app.vps.supplier.repository.RTVMasterRepository;

@EnableAsync
@Service
@Transactional(rollbackFor = {ApplicationException.class, Exception.class })
public class AdminPoServiceImpl implements IAdminPoService {

	@Autowired
	PoMasterRepository poMasterRepository;
	
	@Autowired
	ItemMasterRepository itemMasterRepository;
	
	@Autowired
	PoMasterDao poMasterDao;
	
	@Autowired
	RTVMasterRepository rTVMasterRepository;
	
	@Autowired
	InvoiceMasterRepository invoiceMasterRepository;
	
	@Override
	public List<PoMasterModel> getAdminPoList(int poStatus,int poStatus2) throws ApplicationException, Exception {
		return poMasterRepository.findByStatusOrStatus(poStatus,poStatus2);
	}

	@Override
	public Object getAdminPoCount(int poStatus) throws ApplicationException, Exception {
		Object adminPoCount=null;
		adminPoCount= poMasterRepository.getAdminPoCount(poStatus);
		return adminPoCount;
	}

	@Override
	public PoMasterModel getAdminPoDetail(long po_id) throws ApplicationException, Exception {
		Optional<PoMasterModel> poDetails = poMasterRepository.findById(po_id);
		return poDetails.get();
	}

	@Override
	public List<ItemMasterModel> getAdminPoItemList(String poNo) throws ApplicationException, Exception {
		return itemMasterRepository.findByPoNo(poNo);
	}

	@Override
	public List<ItemMasterModel> getInvoiceItemList(String poNo, int status) throws ApplicationException, Exception {
		return itemMasterRepository.findByPoNoAndStatus(poNo, status);
	}

	@Override
	public List<InvoiceMasterModel> getInvoiceMasterList(int status) throws ApplicationException, Exception {
//		return poMasterDao.getInvoiceMasterList(status);
		return invoiceMasterRepository.findAll();
	}

	@Override
	public Object getAdminInvoiceCount(int status) throws ApplicationException, Exception {
		Object invoiceCount=null;
		invoiceCount= poMasterRepository.getAdminInvoiceCount();
		return invoiceCount;
	}

	@Override
	public Optional<InvoiceMasterModel> getAdminInvoiceDetail(long invoiceId) throws ApplicationException, Exception {
		return invoiceMasterRepository.findById(invoiceId);
	}

	@Override
	public List<PoMasterModel> getAdminSupplierWisePoList(String supplierCode) throws ApplicationException, Exception {
		return poMasterRepository.findBySupplierCode(supplierCode);
	}

	@Override
	public List<RTVMasterModel> getAllRtvList() throws ApplicationException, Exception {
		return rTVMasterRepository.findAll();
	}

	@Override
	public Object getAdminRtvCount() throws ApplicationException, Exception {
		Object adminRtvCount=null;
		adminRtvCount= rTVMasterRepository.getAdminRtvCount();
		return adminRtvCount;
	}


}
