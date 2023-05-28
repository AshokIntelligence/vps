package com.app.vps.login.service;

import java.util.Collection;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.app.vps.exception.ApplicationException;
import com.app.vps.login.model.LoginSupplier;
import com.app.vps.login.model.SupplierModel;
import com.app.vps.login.repository.SupplierRepository;

@Service
public class SupplierServiceImpl implements ISupplierService {

	@Autowired
	SupplierRepository supplierRepository;
	
	@Autowired
	LoginSupplier loginSupplier;
	
	@Override
	public LoginSupplier loadUserByUsername(String suppliercode, String supplier_pass) throws ApplicationException, Exception {
		Optional<SupplierModel> supplier = supplierRepository.findBySupplierCodeAndSupplierPassword(suppliercode,supplier_pass);
		if (supplier.isPresent()) {
			Collection<GrantedAuthority> authorities = new java.util.HashSet<>();
			LoginSupplier loginSupplier = new LoginSupplier();
			loginSupplier.setUsername(suppliercode);
			loginSupplier.setSupplierCode(supplier.get().getSupplierCode());
			loginSupplier.setSupplierName(supplier.get().getSupplierName());
			loginSupplier.setPassword(supplier.get().getSupplierPassword());
			loginSupplier.setSupplierEmail(supplier.get().getSupplierEmail());
			loginSupplier.setSupplier_id(supplier.get().getId());
			loginSupplier.setSupplierStatus(supplier.get().getStatus());
			authorities.add(new org.springframework.security.core.authority.SimpleGrantedAuthority("USER"));
			loginSupplier.setAuthority(authorities);
			return loginSupplier;
			
		}else {
			throw new UsernameNotFoundException(String.format("User not found"));
		}
	}

}
