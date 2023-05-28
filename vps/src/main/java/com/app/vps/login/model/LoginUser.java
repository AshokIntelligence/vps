package com.app.vps.login.model;

import java.util.Collection;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
public final class LoginUser implements UserDetails {
	
	private static final long serialVersionUID = 1L;
	private long user_id;
	private String username;
	private String password;
	private String userFname;
	private String userMname;
	private String userLname;
	private Designation designation;
	private Department department;
	private Plant plant;
	private boolean isReportingAuthority;
	private int authorityStatus;

	private Collection<? extends GrantedAuthority> authority;

	public LoginUser() {
		super();
	}

	public int getAuthorityStatus() {
		return authorityStatus;
	}

	public void setAuthorityStatus(int authorityStatus) {
		this.authorityStatus = authorityStatus;
	}

	public boolean isReportingAuthority() {
		return isReportingAuthority;
	}

	public void setReportingAuthority(boolean isReportingAuthority) {
		this.isReportingAuthority = isReportingAuthority;
	}

	public long getUser_id() {
		return user_id;
	}

	public void setUser_id(long l) {
		this.user_id = l;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String string) {
		this.username = string;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getUserFname() {
		return userFname;
	}

	public void setUserFname(String userFname) {
		this.userFname = userFname;
	}

	public String getUserMname() {
		return userMname;
	}

	public void setUserMname(String userMname) {
		this.userMname = userMname;
	}

	public String getUserLname() {
		return userLname;
	}

	public void setUserLname(String userLname) {
		this.userLname = userLname;
	}

	public Designation getDesignation() {
		return designation;
	}

	public void setDesignation(Designation designation) {
		this.designation = designation;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public Collection<? extends GrantedAuthority> getAuthority() {
		return authority;
	}

	public void setAuthority(Collection<? extends GrantedAuthority> authority) {
		this.authority = authority;
	}
	
	public Plant getPlant() {
		return plant;
	}

	public void setPlant(Plant plant) {
		this.plant = plant;
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
}
