package com.app.vps.admin.service;

import com.app.vps.exception.ApplicationException;

public interface ISchedularService {

	String rtvSchedular()throws ApplicationException, Exception;

	String poSchedular()throws ApplicationException, Exception;


}
