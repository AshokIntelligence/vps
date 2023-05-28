package com.app.vps.basedao;

import com.app.vps.exception.ApplicationException;

public interface IBaseDAO {

	Object validateEntityById(Class<?> classT, Object entity_id, String error_msg)throws ApplicationException, Exception;
	Object getEntityByPropertyName(Class<?> classT, String property_name, Object property_value);
	Object getEntityById(Class<?> classT, Object entity_id) throws ApplicationException, ApplicationException;
	void mergeEntity(Object entity);
}
