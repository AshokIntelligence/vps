package com.app.vps.supplier.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.app.vps.supplier.model.InvoiceMasterModel;
import com.app.vps.supplier.model.ItemMasterModel;

@Repository
public interface InvoiceMasterRepository extends JpaRepository<InvoiceMasterModel, Long> {

	List<InvoiceMasterModel> findBySupplierCode(@Param("supplierCode") String supplierCode);

	List<InvoiceMasterModel> findByPoNo( @Param("poNumber")String poNumber);

	List<InvoiceMasterModel> findByInvoiceNo(@Param("invoiceNo") String invoiceNo);

}
