package com.app.vps.login.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="plant_mst")
public class Plant{	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="plant_id")
	private long id;
	
	@Column(name="plant_name")
	String plantName;
	
	@Column(name="plant_code")
	String plantCode;
	
	@Column(name="status")
	int plantStatus;
	
	public Plant() {
		super();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getPlantName() {
		return plantName;
	}

	public void setPlantName(String plantName) {
		this.plantName = plantName;
	}

	public int getPlantStatus() {
		return plantStatus;
	}

	public void setPlantStatus(int plantStatus) {
		this.plantStatus = plantStatus;
	}

	public String getPlantCode() {
		return plantCode;
	}

	public void setPlantCode(String plantCode) {
		this.plantCode = plantCode;
	}

}
