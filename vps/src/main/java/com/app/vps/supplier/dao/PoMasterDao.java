package com.app.vps.supplier.dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.springframework.stereotype.Repository;

import com.app.vps.supplier.model.ItemMasterModel;
import com.app.vps.supplier.model.PoMasterModel;
import com.app.vps.supplier.model.RTVMasterModel;

@Repository
public class PoMasterDao {

	@PersistenceContext
	EntityManager entityManager;

	public List<PoMasterModel> getTotalSupplierPoCount(String suppliercode) {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<PoMasterModel> criteriaQuery = criteriaBuilder.createQuery(PoMasterModel.class);
 		Root<PoMasterModel> root = criteriaQuery.from(PoMasterModel.class);
 		CriteriaQuery<PoMasterModel> select = criteriaQuery.multiselect(root.get("id"),root.get("status"));	
 		Predicate predicateEmp = root.get("supplierCode").in(suppliercode);
 		Predicate And = criteriaBuilder.and(predicateEmp);
 		criteriaQuery.where(And);
 		TypedQuery<PoMasterModel> typedQuery = entityManager.createQuery(select);
		return typedQuery.getResultList();
	}

//	public List<ItemMasterModel> getInvoiceMasterList(int status) {
//		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
//		CriteriaQuery<ItemMasterModel> criteriaQuery = criteriaBuilder.createQuery(ItemMasterModel.class);
// 		Root<ItemMasterModel> root = criteriaQuery.from(ItemMasterModel.class);
// 		CriteriaQuery<ItemMasterModel> select = criteriaQuery.multiselect(root.get("id"), root.get("poMasterDtl"), root.get("invoiceNo"), root.get("invoiceDate"), root.get("invoiceQuantity"));
// 		Expression<String> groupByExp = root.get("invoiceNo").as(String.class);
// 		
// 		criteriaQuery.orderBy(criteriaBuilder.asc(root.get("id")));
// 		criteriaQuery.distinct(true);
// 		criteriaQuery.groupBy(groupByExp);
// 		Predicate predicateItemStatus = root.get("status").in(status);
// 		Predicate And = criteriaBuilder.and(predicateItemStatus);
// 		criteriaQuery.where(And);
// 		TypedQuery<ItemMasterModel> typedQuery = entityManager.createQuery(select);
//		return typedQuery.getResultList();
//	}


//	public List<ItemMasterModel> getSupplierInvoiceMasterList(int status, String suppliercode) {
//		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
//		CriteriaQuery<ItemMasterModel> criteriaQuery = criteriaBuilder.createQuery(ItemMasterModel.class);
// 		Root<ItemMasterModel> root = criteriaQuery.from(ItemMasterModel.class);
// 		CriteriaQuery<ItemMasterModel> select = criteriaQuery.multiselect(root.get("id"), root.get("poMasterDtl"), root.get("invoiceNo"), root.get("invoiceDate"), root.get("invoiceQuantity"));
// 		Expression<String> groupByExp = root.get("invoiceNo").as(String.class);
// 		Join<ItemMasterModel, PoMasterModel> joinAction = root.join("poMasterDtl", JoinType.LEFT);
// 		criteriaQuery.orderBy(criteriaBuilder.asc(root.get("id")));
// 		criteriaQuery.distinct(true);
// 		criteriaQuery.groupBy(groupByExp);
// 		Predicate predicateItemStatus = root.get("status").in(status);
// 		Predicate predicateSupplierCode = joinAction.get("supplierCode").in(suppliercode);
// 		Predicate And = criteriaBuilder.and(predicateItemStatus,predicateSupplierCode);
// 		criteriaQuery.where(And);
// 		TypedQuery<ItemMasterModel> typedQuery = entityManager.createQuery(select);
//		return typedQuery.getResultList();
//	}
//
	public List<RTVMasterModel> getRtvListItemWise(List<String> multiItemList) {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<RTVMasterModel> criteriaQuery = criteriaBuilder.createQuery(RTVMasterModel.class);
 		Root<RTVMasterModel> root = criteriaQuery.from(RTVMasterModel.class);
 		CriteriaQuery<RTVMasterModel> select = criteriaQuery.multiselect(root.get("id"), root.get("poNo"), root.get("itemCode"), root.get("itemDescription"), root.get("invoiceNo"),root.get("itemNumber"), root.get("vendorCode"), root.get("vendorName"), root.get("rtvNo"), root.get("rtvDate"), root.get("itemQuantity"));
 		criteriaQuery.orderBy(criteriaBuilder.asc(root.get("id")));
 		Predicate predicateItemCode = root.get("itemCode").in(multiItemList);
 		Predicate And = criteriaBuilder.and(predicateItemCode);
 		criteriaQuery.where(And);
 		TypedQuery<RTVMasterModel> typedQuery = entityManager.createQuery(select);
		return typedQuery.getResultList();
	}
//
//	public List<ItemMasterModel> getSupplierInvoicePreviousList(int status, String poNumber) {
//		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
//		CriteriaQuery<ItemMasterModel> criteriaQuery = criteriaBuilder.createQuery(ItemMasterModel.class);
// 		Root<ItemMasterModel> root = criteriaQuery.from(ItemMasterModel.class);
// 		CriteriaQuery<ItemMasterModel> select = criteriaQuery.multiselect(root.get("id"), root.get("invoiceNo"), root.get("invoiceDate"), root.get("invoiceQuantity"),root.get("qrCodeAttachment"));
// 		Expression<String> groupByExp = root.get("invoiceNo").as(String.class);
// 		criteriaQuery.orderBy(criteriaBuilder.asc(root.get("id")));
// 		criteriaQuery.distinct(true);
// 		criteriaQuery.groupBy(groupByExp);
// 		Predicate predicateItemStatus = root.get("status").in(status);
// 		Predicate predicatePoNumber = root.get("poNo").in(poNumber);
// 		Predicate And = criteriaBuilder.and(predicateItemStatus,predicatePoNumber);
// 		criteriaQuery.where(And);
// 		TypedQuery<ItemMasterModel> typedQuery = entityManager.createQuery(select);
//		return typedQuery.getResultList();
//	}
}
