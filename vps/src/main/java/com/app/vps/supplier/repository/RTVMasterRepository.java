package com.app.vps.supplier.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.app.vps.supplier.model.RTVMasterModel;

@Repository
public interface RTVMasterRepository extends JpaRepository<RTVMasterModel, Long> {

	List<RTVMasterModel> findByVendorCode(@Param("suppliercode") String suppliercode);

	Optional<RTVMasterModel> findByItemCode(@Param("itemCode") String itemCode);

	@Query(value="select count(r.id) from RTVMasterModel r")
	Object getAdminRtvCount();

	@Query(value="select count(r.id) from RTVMasterModel r where r.vendorCode =:vendorCode")
	Object getSupplierRtvCount(@Param("vendorCode") String vendorCode);

}
