package com.app.vps.admincontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.RestController;
import com.app.vps.admin.service.ISchedularService;
import com.app.vps.basecontroller.BaseController;
import com.app.vps.bo.BaseResponseBO;
import com.app.vps.exception.ApplicationException;
import com.app.vps.message.IResponseCode;
import com.app.vps.message.IResponseMessage;

@RestController
public class SchedularController extends BaseController {

	@Autowired
	ISchedularService iSchedularService;

	// RTV SCHEDULER
	@Scheduled(cron = "00 00 16 * * *", zone = "Asia/Calcutta")
	public ResponseEntity<BaseResponseBO> rtvSchedular() {
		try {
			String msg = iSchedularService.rtvSchedular();
			return getResponseModel(msg, IResponseCode.SUCCESS, IResponseMessage.SUCCESS, null);

		} catch (ApplicationException e) {
			e.printStackTrace();
			return getResponseModel(null, e.getCode(), e.getMessage(), null);
		} catch (Exception e) {
			e.printStackTrace();
			return getResponseModel(null, IResponseCode.SERVER_ERROR, IResponseMessage.SERVER_ERROR, null);
		}
	}

	// PO SCHEDULER
	@Scheduled(cron = "00 37 16 * * *", zone = "Asia/Calcutta")
	public ResponseEntity<BaseResponseBO> poSchedular() {
		try {
			String msg = iSchedularService.poSchedular();
			return getResponseModel(msg, IResponseCode.SUCCESS, IResponseMessage.SUCCESS, null);

		} catch (ApplicationException e) {
			e.printStackTrace();
			return getResponseModel(null, e.getCode(), e.getMessage(), null);
		} catch (Exception e) {
			e.printStackTrace();
			return getResponseModel(null, IResponseCode.SERVER_ERROR, IResponseMessage.SERVER_ERROR, null);
		}
	}
}
