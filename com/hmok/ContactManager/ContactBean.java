package com.hmok.ContactManager;

import com.hmok.mapping.CSVFieldMap;

/**
 * Represents contact table in the database
 * 
 *  @author Henry Mok
 */
public class ContactBean {
	private long contactId;
	private String name;
	private String userId;
	private String title;
	private long memberId;
	private String phone;
	private long companyId;
	private long groupInfoId;
	private boolean photo;
	private boolean assistant;
	private String mailingList;
	private String url;
	
	/**
	 * Constructor private. use createContactFromStrArray
	 */
	private ContactBean(
			long contactId,
			String name,
			String userId,
			String title,
			long memberId,
			String phone,
			long companyId,
			long groupInfoId,
			boolean photo,
			boolean assistant,
			String mailingList,
			String url)
	{
		this.contactId = contactId;
		this.name = name;
		this.userId = userId;
		this.title = title;
		this.memberId = memberId;
		this.phone = phone;
		this.companyId = companyId;
		this.groupInfoId = groupInfoId;
		this.photo = photo;
		this.assistant = assistant;
		this.mailingList = mailingList;
		this.url = url;
	}
	
    /**
     * Replaces constructor. Creates class from a CSV row using 
     * CVSFieldMap to get the correct column
     * 
     *  @param input - array of strings from CSV
     */
	public static ContactBean createContactFromStrArray (String [] input) {
		try {
			return new ContactBean(
					0, //for contactid
					input[CSVFieldMap.NAME.ordinal()],
					input[CSVFieldMap.USERID.ordinal()],
					input[CSVFieldMap.TITLE.ordinal()],
					Long.parseLong(input[CSVFieldMap.MEMBERID.ordinal()]),
					input[CSVFieldMap.PHONE.ordinal()],
					//for company and group info id
					0, 0,
					Util.stringToBool(input[CSVFieldMap.PHOTO.ordinal()]),
					Util.stringToBool(input[CSVFieldMap.ASSISTANT.ordinal()]),
					input[CSVFieldMap.MAILING_LIST.ordinal()],
					input[CSVFieldMap.URL.ordinal()]);
		} catch ( NumberFormatException e ) {
			e.printStackTrace();
		}
		return null;
	}

	public long getContactId() {
		return contactId;
	}
	public void setContactId(long contactId) {
		this.contactId = contactId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public long getMemberId() {
		return memberId;
	}
	public void setMemberId(long memberId) {
		this.memberId = memberId;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public long getCompanyId() {
		return companyId;
	}
	public void setCompanyId(long companyId) {
		this.companyId = companyId;
	}
	public long getGroupInfoId() {
		return groupInfoId;
	}
	public void setGroupInfoId(long groupInfoId) {
		this.groupInfoId = groupInfoId;
	}
	public boolean isPhoto() {
		return photo;
	}
	public void setPhoto(boolean photo) {
		this.photo = photo;
	}
	public boolean isAssistant() {
		return assistant;
	}
	public void setAssistant(boolean assistant) {
		this.assistant = assistant;
	}
	public String getMailingList() {
		return mailingList;
	}
	public void setMailingList(String mailingList) {
		this.mailingList = mailingList;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}

}
