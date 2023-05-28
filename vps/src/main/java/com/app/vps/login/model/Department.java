package com.app.vps.login.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="dept_mst")
public class Department{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="dept_id")
	private long id;
		
	 @Column(name="dept_name")
	 private String deptName;
	 
     @Column(name="status")
     private long deptStatus;

	public Department() {
		super();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	
	public long getDeptStatus() {
		return deptStatus;
	}

	public void setDeptStatus(long deptStatus) {
		this.deptStatus = deptStatus;
	}
	
}
