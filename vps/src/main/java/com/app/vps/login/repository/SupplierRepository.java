package com.app.vps.login.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.app.vps.login.model.SupplierModel;

@Repository
public interface SupplierRepository extends JpaRepository<SupplierModel, Long> {

	Optional<SupplierModel> findBySupplierCodeAndStatus(@Param("supplierCode") String supplierCode, @Param("status")int status);

	Optional<SupplierModel> findBySupplierCodeAndSupplierPassword(@Param("supplierCode") String supplierCode,@Param("supplierPass") String supplierPass);

	Optional<SupplierModel> findBySupplierCode(@Param("supplierCode") String supplierCode);

	@Query(value="select count(vm.id) from SupplierModel vm")
	Object getTotalSupplierCount();


}
