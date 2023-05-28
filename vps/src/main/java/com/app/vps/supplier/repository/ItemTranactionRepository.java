package com.app.vps.supplier.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.app.vps.supplier.model.ItemTransactionModel;

@Repository
public interface ItemTranactionRepository extends JpaRepository<ItemTransactionModel, Long> {

	List<ItemTransactionModel> findByInvoiceId(@Param("invoiceId") Long invoiceId);

}
