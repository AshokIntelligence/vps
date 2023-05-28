package com.app.vps.login.service;

import java.util.Optional;
import org.springframework.security.core.userdetails.UserDetailsService;
import com.app.vps.exception.ApplicationException;
import com.app.vps.login.model.User;

public interface IAdminService extends UserDetailsService {

	Optional<User> findByUsername(String username);

	Optional<User> findByEmail(String email);

	String checkLogout(String username) throws ApplicationException;

	Object getEmployeeName(long emp_code) throws ApplicationException, Exception;

	User findById(long id) throws ApplicationException, Exception;

}
