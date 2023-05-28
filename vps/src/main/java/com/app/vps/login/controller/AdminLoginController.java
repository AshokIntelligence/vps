package com.app.vps.login.controller;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.app.vps.basecontroller.BaseController;
import com.app.vps.bo.BaseResponseBO;
import com.app.vps.constant.Constants;
import com.app.vps.exception.ApplicationException;
import com.app.vps.login.model.LoginUser;
import com.app.vps.login.service.IAdminService;
import com.app.vps.message.IResponseCode;
import com.app.vps.message.IResponseMessage;

@CrossOrigin(origins="*", allowedHeaders="*") 
@RestController
public class AdminLoginController extends BaseController {

	@Autowired
	private IAdminService iAdminService;

	@Autowired
	LoginUser loginUser;

	org.slf4j.Logger logger = LoggerFactory.getLogger(AdminLoginController.class);

	@GetMapping(value = Constants.IMappingConstants.ADMIN+Constants.IMappingConstants.LOGIN)
	public ResponseEntity<BaseResponseBO> login(@RequestParam("emp_code") long emp_code,@RequestParam("emp_pass") String emp_pass) throws ApplicationException {
		LoginUser loginUser = new LoginUser();
		try {
			loginUser = (LoginUser) iAdminService.loadUserByUsername(String.valueOf(emp_code));
			return getResponseModel(loginUser, IResponseCode.SUCCESS, IResponseMessage.SUCCESS, null);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.toString());
			return getResponseModel(null, IResponseCode.UNAUTHORIZED, IResponseMessage.UNAUTHORIZED_USER, null);
		}
	}

	@GetMapping(value = Constants.IMappingConstants.ADMIN+Constants.IMappingConstants.LOGOUT)
	public ResponseEntity<?> logout(@RequestParam("global_emp_code") long emp_code) {
		try {
			if (loginUser.getUsername() != null && !loginUser.getUsername().isEmpty()) {
				iAdminService.checkLogout(loginUser.getUsername());
				return getResponseModel(null, IResponseCode.SUCCESS, IResponseMessage.LOGGEDOUT_SUCCESSFULLY, null);
			} else {
				return getResponseModel(null, IResponseCode.SUCCESS, IResponseMessage.ALREADY_LOGGEDOUT, null);
			}
		} catch (ApplicationException e) {
			e.printStackTrace();
			return getResponseModel(null, e.getCode(), e.getMessage(), null);
		}
	}
}
