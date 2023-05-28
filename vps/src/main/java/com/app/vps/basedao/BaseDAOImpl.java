package com.app.vps.basedao;

import java.lang.reflect.Field;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.app.vps.constant.Constants;
import com.app.vps.exception.ApplicationException;
import com.app.vps.message.IResponseCode;
import com.app.vps.message.IResponseMessage;

@Repository
public class BaseDAOImpl implements IBaseDAO {
	
	@Autowired
	private EntityManager entityManager;

	@Override
	public Object validateEntityById(Class<?> classT, Object entity_id, String error_msg)throws ApplicationException, Exception {
		if (entity_id != null && ((entity_id instanceof Integer && (int) entity_id > Constants.VALUE_ZERO) || (entity_id instanceof Long && (long) entity_id > Constants.VALUE_ZERO))) {
			Object entity = entityManager.find(classT, entity_id);
			if (entity == null)
				throw new ApplicationException(IResponseCode.INVALID_ENTITY, error_msg);
			else {
				Field declaredField = classT.getDeclaredField(Constants.STATUS);
				int status = (int) declaredField.get(entity);
				if (status == Constants.STATUS_INACTIVE)
					throw new ApplicationException(IResponseCode.INVALID_ENTITY, error_msg);
			}
			return entity;
		}
		return null;
	}

	@Override
	public void mergeEntity(Object entity) {
		entityManager.merge(entity);
	}

	@Override
	public Object getEntityById(Class<?> classT, Object entity_id) throws ApplicationException {
		Object master_id = entityManager.find(classT, entity_id);
		if (master_id == null) {
			throw new ApplicationException(IResponseCode.INVALID_ENTITY, IResponseMessage.INVALID_MASTER_ID);}
		return master_id;
	}

	@Override
	public Object getEntityByPropertyName(Class<?> classT, String property_name, Object property_value) {
		try {
			if (property_name != null && !property_name.isEmpty() && property_value != null) {
			Object result = entityManager.createQuery("from " + classT.getName() + " where " + property_name + " = '"+ property_value.toString() + "'", classT).setMaxResults(Constants.VALUE_ONE).getSingleResult();
				return result;
			}
		} catch (NoResultException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
