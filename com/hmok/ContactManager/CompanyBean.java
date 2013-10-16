package com.hmok.ContactManager;

import com.hmok.mapping.CSVFieldMap;

/**
 * Represents company table in database
 *
 * @author Henry Mok
 */
public class CompanyBean {
	private long companyId;
	private String name;
	private String address;
	private String phone;
	
	private CompanyBean(long contactId, String name, String address, String phone )
	{
		this.companyId = contactId;
		this.name = name;
		this.address = address;
		this.phone = phone;
	}
	
    /**
     * Replaces constructor. Creates class from a CSV row using 
     * CVSFieldMap to get the correct column. No values for
     * address and phone number because the CSV doesn't have it
     * 
     *  @param input - array of strings from CSV
     */
	public static CompanyBean createCompanyFromStrArray( String [] input) {
		return new CompanyBean(0, input[CSVFieldMap.COMPANY.ordinal()], "", "");
	}
	
    /**
     * Replaces constructor. Creates class using field values
     * 
     *  @param input - array of strings from CSV
     */
	public static CompanyBean createCompanyFromFieldVals( 
			String id, String name, String address, String phone ) {
		return new CompanyBean(Long.parseLong(id), name, address, phone);
	}

	public long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(long companyId) {
		this.companyId = companyId;
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

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	
}
