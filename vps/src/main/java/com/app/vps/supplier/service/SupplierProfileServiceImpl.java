package com.app.vps.supplier.service;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.app.vps.constant.Constants;
import com.app.vps.exception.ApplicationException;
import com.app.vps.login.model.SupplierModel;
import com.app.vps.login.repository.SupplierRepository;
import com.app.vps.mail.IMailService;

@EnableAsync
@Service
@Transactional(rollbackFor = {ApplicationException.class, Exception.class })
public class SupplierProfileServiceImpl implements ISupplierProfileService {

	@Autowired
	SupplierRepository supplierRepository;
	
	@Autowired
	IMailService iMailService;
	
	@Override
	public SupplierModel getSupplierDetailsBySupplier(String supplier_code) throws ApplicationException, Exception {
		Optional<SupplierModel> supplierDetails= supplierRepository.findBySupplierCode(supplier_code);
		return supplierDetails.get();
	}

	@Override
	public void updateSupplierProfile(SupplierModel supplierModel) throws ApplicationException, Exception {
		Optional<SupplierModel> supplierDetails= supplierRepository.findById(supplierModel.getId());
		supplierDetails.get().setSupplierEmail(supplierModel.getSupplierEmail());
		supplierRepository.save(supplierDetails.get());
	}

	@Override
	public void updateSupplierPassword(SupplierModel supplierModel) throws ApplicationException, Exception {
		Optional<SupplierModel> supplierDetails= supplierRepository.findBySupplierCode(supplierModel.getSupplierCode());
		supplierDetails.get().setSupplierPassword(supplierModel.getSupplierPassword());
		supplierRepository.save(supplierDetails.get());
	}

	@Override
	public List<SupplierModel> getSupplierAllList() throws ApplicationException, Exception {
		return supplierRepository.findAll();
	}

	@Override
	public void updateSupplierComment(SupplierModel supplierModel) throws ApplicationException, Exception {
		Optional<SupplierModel> supplierDetails= supplierRepository.findById(supplierModel.getId());
		supplierDetails.get().setSupplierDeactivateComment(supplierModel.getSupplierDeactivateComment());
		supplierDetails.get().setStatus(supplierModel.getStatus());
		supplierRepository.save(supplierDetails.get());
		
	}

	@Override
	public void sendForgatePassUrl(String supplier_code,String supplier_code2, String base_url) throws ApplicationException, Exception {
		Optional<SupplierModel> supplierDetails= supplierRepository.findBySupplierCode(supplier_code2);
		String to = supplierDetails.get().getSupplierEmail();
		String cc = null;
		String emailIds [] = {};
		String msgSubject = "CHANGE PASSWORD LINK";
		String msgBody = "Dear Sir/Ma'am, <br>"
 				 +"CHANGE PASSWORD LINK IS <br> "+base_url+"supplier/supplierChangePassword.jsp?supplier_code="+supplier_code;
    	iMailService.sendMail(to, cc, emailIds, msgBody, msgSubject, Constants.IMailConstants.FROM);
	}
	
	
}
