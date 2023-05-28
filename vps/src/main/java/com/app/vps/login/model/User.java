package com.app.vps.login.model;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="emp_details_mst")
public class User implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="emp_id")
	private long id;
	
	@Column(name="emp_status")
	private int empStatus;

	@Column(name="desig_id")
	private int designaionId;
	@ManyToOne()
	@JoinColumn(name = "desig_id", referencedColumnName = "desig_id" ,insertable = false, updatable = false )
	private Designation designation;

	@Column(name="plant_id")
	private int plantId;	
	@ManyToOne()
	@JoinColumn(name = "plant_id", referencedColumnName = "plant_id" ,insertable = false, updatable = false )
	private Plant plant;
		
	@Column(name = "dept_id")
	private int deptId;
	@ManyToOne()
	@JoinColumn(name = "dept_id", referencedColumnName = "dept_id" ,insertable = false, updatable = false )
	private Department dept;
	
	@Column(name="emp_code")
	private long employeeCode;
	
	@Column(name="emp_fname")
	private String employeeFname;
	
	@Column(name="emp_mname")
	private String employeeMname;
	
	@Column(name="emp_lname")
	private String employeeLname;
	
	@Column(name="emp_email")
	private String employeeEmail;
	
	@Column(name="emp_pass")
	private String employeePassword;
	
	@Column(name="emp_mobile")
	private String employeeMobile;
	
	@ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "vps_login_access", joinColumns = @JoinColumn(name = "emp_code", referencedColumnName = "emp_code"), inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
    private Collection<Role> roles;

	public User() {
		super();
	}
	
	public User(long id, int plantId) {
		this.id = id;
		this.plantId = plantId;
	}
	
	public User(long id,long employeeCode, String employeeFname, String employeeLname) {
		this.id = id;
		this.employeeCode = employeeCode;
		this.employeeFname = employeeFname;
		this.employeeLname = employeeLname;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getEmpStatus() {
		return empStatus;
	}

	public void setEmpStatus(int empStatus) {
		this.empStatus = empStatus;
	}

	public int getDesignaionId() {
		return designaionId;
	}

	public void setDesignaionId(int designaionId) {
		this.designaionId = designaionId;
	}

	public Designation getDesignation() {
		return designation;
	}

	public void setDesignation(Designation designation) {
		this.designation = designation;
	}

	public int getPlantId() {
		return plantId;
	}

	public void setPlantId(int plantId) {
		this.plantId = plantId;
	}

	public Plant getPlant() {
		return plant;
	}

	public void setPlant(Plant plant) {
		this.plant = plant;
	}

	public int getDeptId() {
		return deptId;
	}

	public void setDeptId(int deptId) {
		this.deptId = deptId;
	}

	public Department getDept() {
		return dept;
	}

	public void setDept(Department dept) {
		this.dept = dept;
	}

	public long getEmployeeCode() {
		return employeeCode;
	}

	public void setEmployeeCode(long employeeCode) {
		this.employeeCode = employeeCode;
	}

	public String getEmployeeFname() {
		return employeeFname;
	}

	public void setEmployeeFname(String employeeFname) {
		this.employeeFname = employeeFname;
	}

	public String getEmployeeMname() {
		return employeeMname;
	}

	public void setEmployeeMname(String employeeMname) {
		this.employeeMname = employeeMname;
	}

	public String getEmployeeLname() {
		return employeeLname;
	}

	public void setEmployeeLname(String employeeLname) {
		this.employeeLname = employeeLname;
	}

	public String getEmployeeEmail() {
		return employeeEmail;
	}

	public void setEmployeeEmail(String employeeEmail) {
		this.employeeEmail = employeeEmail;
	}

	public String getEmployeePassword() {
		return employeePassword;
	}

	public void setEmployeePassword(String employeePassword) {
		this.employeePassword = employeePassword;
	}

	public String getEmployeeMobile() {
		return employeeMobile;
	}

	public void setEmployeeMobile(String employeeMobile) {
		this.employeeMobile = employeeMobile;
	}

	public Collection<Role> getRoles() {
		return roles;
	}

	public void setRoles(Collection<Role> roles) {
		this.roles = roles;
	}
	
	
	
}
