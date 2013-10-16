package com.hmok.display;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CompanyListItem {
	private String name;
	private String address;
	private String phoneNum;
	
	private CompanyListItem() {
	}
	
    /**
     * Sql to run to get all the companies
     */
	public static String getAllCompanySQL () {
		return "SELECT company_name, company_addr, company_phone FROM company";
	}
	
    /**
     * Creates a list of CompanyListItem from a result set
     * 
     * @param rs - result set from getAllCompanySQL
     */
	public static List <CompanyListItem> processResults ( ResultSet rs ) {
		List <CompanyListItem> companies = new ArrayList <CompanyListItem> ();
		try {
			while(rs.next())
			{
				CompanyListItem companyListItem = new CompanyListItem();
				companyListItem.setName(rs.getString("company_name"));
				companyListItem.setAddress(rs.getString("company_addr"));
				companyListItem.setPhoneNum(rs.getString("company_phone"));
				companies.add(companyListItem);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return companies;
	}

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhoneNum() {
		return phoneNum;
	}

	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}
	
}
