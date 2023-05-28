package com.app.vps.login.model;

import java.util.Collection;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
public final class LoginSupplier implements UserDetails {
	
	private static final long serialVersionUID = 1L;
	private long supplier_id;
	private String username;
	private String supplierCode;
	private String supplierName;
	private int supplierStatus;
	private String password;
	private String supplierEmail;
	private boolean isReportingAuthority;
	private int authorityStatus;

	private Collection<? extends GrantedAuthority> authority;

	public LoginSupplier() {
		super();
	}

	public boolean isReportingAuthority() {
		return isReportingAuthority;
	}

	public void setReportingAuthority(boolean isReportingAuthority) {
		this.isReportingAuthority = isReportingAuthority;
	}

	public Collection<? extends GrantedAuthority> getAuthority() {
		return authority;
	}

	public void setAuthority(Collection<? extends GrantedAuthority> authority) {
		this.authority = authority;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authority;
	}

	@Override
	public boolean isAccountNonExpired() {
		return false;
	}

	@Override
	public boolean isAccountNonLocked() {
		return false;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return false;
	}

	@Override
	public boolean isEnabled() {
		return false;
	}

	@Override
	public String getUsername() {
		return null;
	}

	@Override
	public String getPassword() {
		return null;
	}

	public long getSupplier_id() {
		return supplier_id;
	}

	public void setSupplier_id(long supplier_id) {
		this.supplier_id = supplier_id;
	}

	public String getSupplierCode() {
		return supplierCode;
	}

	public void setSupplierCode(String supplierCode) {
		this.supplierCode = supplierCode;
	}

	public String getSupplierName() {
		return supplierName;
	}

	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}

	public int getSupplierStatus() {
		return supplierStatus;
	}

	public void setSupplierStatus(int supplierStatus) {
		this.supplierStatus = supplierStatus;
	}

	public String getSupplierEmail() {
		return supplierEmail;
	}

	public void setSupplierEmail(String supplierEmail) {
		this.supplierEmail = supplierEmail;
	}

	public int getAuthorityStatus() {
		return authorityStatus;
	}

	public void setAuthorityStatus(int authorityStatus) {
		this.authorityStatus = authorityStatus;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
}
