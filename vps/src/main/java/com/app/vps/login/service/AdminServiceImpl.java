package com.app.vps.login.service;

import java.util.Collection;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.app.vps.constant.Constants;
import com.app.vps.exception.ApplicationException;
import com.app.vps.login.model.LoginUser;
import com.app.vps.login.model.Role;
import com.app.vps.login.model.User;
import com.app.vps.login.repository.AdminRepository;

@Service
public class AdminServiceImpl implements IAdminService {

	@Autowired
	private AdminRepository adminRepository;

	@Autowired
	LoginUser loginUser;

	@Override
	public LoginUser loadUserByUsername(String emp_code) throws UsernameNotFoundException {
		Optional<User> user = adminRepository.findByEmployeeCodeAndEmpStatus(Long.parseLong(emp_code),Constants.STATUS_ACTIVE);
		if (user.isPresent()) {
			Collection<GrantedAuthority> authorities = new java.util.HashSet<>();
			LoginUser loginUser = new LoginUser();
			if (user.get().getRoles() == null || user.get().getRoles().isEmpty()) {
				loginUser.setUsername(String.valueOf(user.get().getEmployeeCode()));
				loginUser.setPassword(user.get().getEmployeePassword());
				authorities.add(new org.springframework.security.core.authority.SimpleGrantedAuthority("UNAUTHORISED"));
				loginUser.setAuthority(authorities);
				return loginUser;
			}else {
				loginUser.setUsername(String.valueOf(user.get().getEmployeeCode()));
				loginUser.setPassword(user.get().getEmployeePassword());
				loginUser.setUserFname(user.get().getEmployeeFname());
				loginUser.setUserLname(user.get().getEmployeeLname());
				loginUser.setDepartment(user.get().getDept());
				loginUser.setDesignation(user.get().getDesignation());
				loginUser.setPlant(user.get().getPlant());
				for (Role role : user.get().getRoles()) {
					authorities.add(new org.springframework.security.core.authority.SimpleGrantedAuthority(role.getRoleName()));
					loginUser.setAuthority(authorities);
				}
				return loginUser;
			}
		} else {
			throw new UsernameNotFoundException(String.format("User not found"));
		}
	}

	@Override
	public Optional<User> findByUsername(String username) {
		return null;
	}

	@Override
	public Optional<User> findByEmail(String email) {
		return null;
	}

	@Override
	public String checkLogout(String emp_code) throws ApplicationException {
		loginUser.setUsername("");
		loginUser.setPassword("");
		return "logged out successfully";
	}

	@Override
	public Object getEmployeeName(long emp_code) throws ApplicationException, Exception {
		Object empName = null;
		return empName;
	}

	@Override
	public User findById(long emp_code) throws ApplicationException, Exception {
		Optional<User> user = adminRepository.findByEmployeeCodeAndEmpStatus(emp_code, Constants.STATUS_ACTIVE);
		return user.get();
	}

}
