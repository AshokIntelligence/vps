package com.app.vps.baseservice;

import java.util.List;
import com.app.vps.bo.ResponseBO;

public class BaseService {
	public ResponseBO generatePaginationResponse(List<?> list, long listSize, int current_page, int limit) {
		ResponseBO responseBO = new ResponseBO();
		responseBO.setList(list);
		return responseBO;
	}
}
