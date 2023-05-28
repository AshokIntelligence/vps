package com.app.vps.constant;

public interface Constants {
	public int STATUS_ACTIVE = 1;
	public int STATUS_INACTIVE = 0;
	public int VALUE_ONE = 1;
	public int VALUE_ZERO=0;
	public int DEFAULT = -1;
    public String STATUS="status";
	
    public int PENDING = 0;
	public int APPROVED = 1;  
	public int REJECTED = 2;
	public int DRAFT = 3;
	
	public interface IMappingConstants
	{  
		public String LOGIN = "/login";
		public String LOGOUT = "/logout";
		public String SAVE = "/save";
		public String LIST = "/list";
		public String PLANT = "/plant";
		public String EMPLOYEE = "/employee";
		public String UPDATE = "/update";
		public String DELETE = "/delete";
		public String DEPT = "/dept";
		public String USER = "/user";
		public String STATUS = "/status";
		public String MASTER = "/master";
		public String BYID = "/byId";
		public String DETAILS = "/details";
		public String ADMIN = "/admin";
		public String GETBYSUPPLIERCODE = "/getBySupplierCode";
		public String PROFILE = "/profile";
		public String PASSWORD = "/password";
		public String ALL = "/all";
		public String COMMENT = "/comment";
		public String PENDING = "/pending";
		public String PO = "/po";
		public String COUNT = "/count";
		public String TOTAL = "/total";
		public String SUPPLIER = "/supplier";
		public String ACTION = "/action";
		public String INVOICE = "/invoice";
		public String SAP = "/sap";
		public String ITEM = "/item";
		public String BYINVOICENO = "/byInvoiceNo";
		public String SUPPLIER_WISE = "/supplier_wise";
		public String EMAIL = "/email";
		public String FORGATE = "/forgate";
		public String RTV = "/rtv";
		public String ITEM_WISE = "/item_wise";
		public String PRIVIOUS = "/previous";
		public String QUANTITY = "/quantity";
		public String TRANACTION = "/transaction";
	}   

	public interface ISecurityConstants{
		String PASSWORD = "Test@1234";
	}
	
	public interface IMailConstants{
		public String FROM = "no-reply@rucha.co.in";
		public String LINK= "https://replportal.co.in:8443/portal/dashboard.jsp";
	}
	
	
	public interface IFileConstants{
		String UPLOAD_PATH = "/upload/vps";
	}
}
