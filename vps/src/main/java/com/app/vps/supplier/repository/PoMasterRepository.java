package com.app.vps.supplier.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.app.vps.supplier.model.PoMasterModel;

@Repository
public interface PoMasterRepository extends JpaRepository<PoMasterModel, Long> {

	List<PoMasterModel> findByStatus(@Param("poStatus") int poStatus);

	@Query(value="select count(p.id) from PoMasterModel p where p.status =:poStatus")
	Object getAdminPoCount(@Param("poStatus") int poStatus);

	List<PoMasterModel> findByStatusAndSupplierCode(@Param("poStatus") int poStatus, @Param("supplierCode") String supplierCode);

	Optional<PoMasterModel> findByPoNo(@Param("poNo") String poNo);

	@Query(value="select count(imm.id) from InvoiceMasterModel imm")
	Object getAdminInvoiceCount();

	@Query(value="select count(imm.id) from InvoiceMasterModel imm where imm.supplierCode =:suppliercode")
	Object getSupplierInvoiceCount( @Param("suppliercode") String suppliercode);

	List<PoMasterModel> findBySupplierCode(@Param("suppliercode") String supplierCode);

	List<PoMasterModel> findByStatusOrStatus(@Param("poStatus") int poStatus, @Param("poStatus2") int poStatus2);

}
