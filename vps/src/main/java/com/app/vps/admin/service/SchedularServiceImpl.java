package com.app.vps.admin.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Base64;
import java.util.Date;
import java.util.Optional;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.app.vps.exception.ApplicationException;
import com.app.vps.supplier.model.ItemMasterModel;
import com.app.vps.supplier.model.PoMasterModel;
import com.app.vps.supplier.model.RTVMasterModel;
import com.app.vps.supplier.repository.ItemMasterRepository;
import com.app.vps.supplier.repository.PoMasterRepository;
import com.app.vps.supplier.repository.RTVMasterRepository;

@Service
@Transactional(rollbackFor = {ApplicationException.class, Exception.class })
public class SchedularServiceImpl implements ISchedularService {
	
	@Autowired
	RTVMasterRepository rTVMasterRepository;
	
	@Autowired
	PoMasterRepository poMasterRepository;
	
	@Autowired
	ItemMasterRepository itemMasterRepository;

	@Override
	public String rtvSchedular() throws ApplicationException, Exception {
		LocalDate today = LocalDate.now();
		String pattern = "hh:mm a";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		String time = simpleDateFormat.format(new Date());
		try {
			String userCredentials = "layamgar:Repl@2023";
			String basicAuth = "Basic " + new String(Base64.getEncoder().encode(userCredentials.getBytes()));
			URL url = new URL("http://sp01.erpruchagroup.com:8001/rtvdetails/rtvdetails?sap-client=900");
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestProperty("Authorization", basicAuth);
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Content-Type", "application/json");
			conn.setRequestProperty("Accept", "application/json");
			conn.setUseCaches(false);
			conn.setDoInput(true);
			conn.setDoOutput(true);
			BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
			String output2 = null;
			output2 = br.readLine();

			JSONArray array = new JSONArray(output2); 
			for(int i=0; i < array.length(); i++)   
			{  
				JSONObject object = array.getJSONObject(i);  
				Optional<RTVMasterModel> updateRtvDetails = rTVMasterRepository.findByItemCode(object.getString("MATNR"));
				if (updateRtvDetails.isEmpty()) {
					RTVMasterModel saveRtvDetails = new RTVMasterModel();
					saveRtvDetails.setItemCode(object.getString("MATNR"));
					saveRtvDetails.setItemNumber(object.getInt("EBELP"));
					saveRtvDetails.setItemQuantity(object.getDouble("MENGE"));
					saveRtvDetails.setPoNo(object.getString("EBELN"));
					saveRtvDetails.setRtvDate(object.getString("BUDAT"));
					saveRtvDetails.setRtvNo(object.getString("BKTXT"));
					saveRtvDetails.setVendorCode(object.getString("LIFNR"));
					saveRtvDetails.setVendorName(object.getString("NAME1"));
					saveRtvDetails.setRegDate(today.toString());
					saveRtvDetails.setRegTime(time.toString());
					saveRtvDetails.setInvoiceNo(object.getString("LFBNR"));
					saveRtvDetails.setItemDescription(object.getString("MAKTX"));
					rTVMasterRepository.save(saveRtvDetails);
				}else {
					updateRtvDetails.get().setItemCode(object.getString("MATNR"));
					updateRtvDetails.get().setItemNumber(object.getInt("EBELP"));
					updateRtvDetails.get().setItemQuantity(object.getDouble("MENGE"));
					updateRtvDetails.get().setPoNo(object.getString("EBELN"));
					updateRtvDetails.get().setRtvDate(object.getString("BUDAT"));
					updateRtvDetails.get().setRtvNo(object.getString("BKTXT"));
					updateRtvDetails.get().setVendorCode(object.getString("LIFNR"));
					updateRtvDetails.get().setVendorName(object.getString("NAME1"));
					updateRtvDetails.get().setRegDate(today.toString());
					updateRtvDetails.get().setRegTime(time.toString());
					updateRtvDetails.get().setInvoiceNo(object.getString("LFBNR"));
					updateRtvDetails.get().setItemDescription(object.getString("MAKTX"));
					rTVMasterRepository.save(updateRtvDetails.get());
				}
			}
			conn.disconnect();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public String poSchedular() throws ApplicationException, Exception {
		LocalDate today = LocalDate.now();
		LocalTime time = LocalTime.now();
		try {
			String userCredentials = "layamgar:Repl@2023";
			String basicAuth = "Basic " + new String(Base64.getEncoder().encode(userCredentials.getBytes()));
			URL url = new URL("http://sp01.erpruchagroup.com:8001/rtvdetails/rtvdetails?sap-client=900");
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestProperty("Authorization", basicAuth);
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Content-Type", "application/json");
			conn.setRequestProperty("Accept", "application/json");
			conn.setUseCaches(false);
			conn.setDoInput(true);
			conn.setDoOutput(true);
			BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
			String output2 = null;
			output2 = br.readLine();
			JSONArray array = new JSONArray(output2); 
			for(int i=0; i < array.length(); i++)   
			{  
				JSONObject object = array.getJSONObject(i);  
				Optional<PoMasterModel> updatePoDetails = poMasterRepository.findByPoNo(object.getString("EBELN"));
				if (updatePoDetails.isEmpty()) {
					PoMasterModel savePoDetails = new PoMasterModel();
					savePoDetails.setPoDate(object.getString("BEDAT"));
					savePoDetails.setPoNo(object.getString("EBELN"));
					savePoDetails.setPoStatus(object.getString("FRGKE"));
					savePoDetails.setRegDate(today);
					savePoDetails.setRegTime(time);
					savePoDetails.setStatus(0);
					poMasterRepository.save(savePoDetails);
				}else {
					updatePoDetails.get().setPoDate(object.getString("BEDAT"));
					updatePoDetails.get().setPoNo(object.getString("EBELN"));
					updatePoDetails.get().setPoStatus(object.getString("FRGKE"));
					updatePoDetails.get().setRegDate(today);
					updatePoDetails.get().setRegTime(time);
					poMasterRepository.save(updatePoDetails.get());
				}
				Optional<ItemMasterModel> updateItemDetails = itemMasterRepository.findByItemCode(object.getString("MATNR"));
				if (updateItemDetails.isEmpty()) {
					ItemMasterModel saveItemDetails = new ItemMasterModel();
					saveItemDetails.setItemAmt(object.getDouble("NETPR"));
					saveItemDetails.setItemCode(object.getString("MATNR"));
					saveItemDetails.setItemDesc(object.getString("MAKTX"));
					saveItemDetails.setItemQuantity(object.getDouble("MENGE"));
					saveItemDetails.setItemUom(object.getString("MEINS"));
					saveItemDetails.setPoNo(object.getString("EBELN"));
					saveItemDetails.setItemDynamicQuantity(object.getDouble("MENGE"));
					saveItemDetails.setItemRelaseQuantity(0);
					saveItemDetails.setItemRemaningQuantity(object.getDouble("MENGE"));
					saveItemDetails.setStatus(0);
					itemMasterRepository.save(saveItemDetails);
				}else {
					updateItemDetails.get().setItemAmt(object.getDouble("NETPR"));
					updateItemDetails.get().setItemCode(object.getString("MATNR"));
					updateItemDetails.get().setItemDesc(object.getString("MAKTX"));
					updateItemDetails.get().setItemQuantity(object.getDouble("MENGE"));
					updateItemDetails.get().setItemUom(object.getString("MEINS"));
					updateItemDetails.get().setPoNo(object.getString("EBELN"));
					itemMasterRepository.save(updateItemDetails.get());
				}
			}
			conn.disconnect();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	
}
