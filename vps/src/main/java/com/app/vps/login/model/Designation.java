package com.app.vps.login.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="desig_mst")
public class Designation {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="desig_id")
	private long id;
	
	@Column(name="desig_name")
	private String desigName;
	
	@Column(name="status")
    private long desigStatus;

	public Designation() {
		super();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getDesigName() {
		return desigName;
	}

	public void setDesigName(String desigName) {
		this.desigName = desigName;
	}

	public long getDesigStatus() {
		return desigStatus;
	}

	public void setDesigStatus(long desigStatus) {
		this.desigStatus = desigStatus;
	}

}