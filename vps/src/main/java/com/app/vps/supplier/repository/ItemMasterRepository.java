package com.app.vps.supplier.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.app.vps.supplier.model.ItemMasterModel;

@Repository
public interface ItemMasterRepository extends JpaRepository<ItemMasterModel, Long> {

	Optional<ItemMasterModel> findByItemCode(@Param("itemCode") String itemCode);

	List<ItemMasterModel> findByPoNo(@Param("poNo") String poNo);

	List<ItemMasterModel> findByPoNoAndStatus(@Param("poNo") String poNo, @Param("status") int status);

}
