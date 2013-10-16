package com.hmok.ContactManager;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.hmok.database.CompanyDAO;
import com.hmok.database.ContactDAO;
import com.hmok.display.CompanyListItem;
import com.hmok.display.ContactListItem;

/**
 * Create lists to display on webpage
 * 
 *  @author Henry Mok
 */
@ManagedBean
@SessionScoped
public class CreateList {
	private List <ContactListItem> contactList = null;
	private List <CompanyListItem> companyList = null;
	
	public String createContactList() {
		ContactDAO contactDAO = new ContactDAO();
		contactList = contactDAO.retrieveAllContacts();
		sortTableByName();

		return "contactList?faces-redirect=true";
	}
	
	public String sortTableByName() {
		if (contactList == null) {
			createContactList();
		}
		Comparator <ContactListItem> comparator = new Comparator <ContactListItem>() {
		    public int compare(ContactListItem c1, ContactListItem c2) {
		    	return c1.getName().compareToIgnoreCase(c2.getName()) ;
		    }
		};
		Collections.sort(contactList, comparator);
		
		return "";
	}
	
	public String sortTableByCompany() {
		if (contactList == null) {
			createContactList();
		}
		Comparator <ContactListItem> comparator = new Comparator <ContactListItem>() {
		    public int compare(ContactListItem c1, ContactListItem c2) {
		    	return c1.getCompany().compareToIgnoreCase(c2.getCompany()) ;
		    }
		};
		
		Collections.sort(contactList, comparator);
		return "";
	}
	
	public String createCompanyList() {
		CompanyDAO companyDAO = new CompanyDAO();
		companyList = companyDAO.retrieveAllContacts();
		
		return "companyList?faces-redirect=true";
	}

	public List<CompanyListItem> getCompanyList() {
		return companyList;
	}

	public void setCompanyList(List<CompanyListItem> companyList) {
		this.companyList = companyList;
	}

	public List<ContactListItem> getContactList() {
		return contactList;
	}

	public void setContactList(List<ContactListItem> contactList) {
		this.contactList = contactList;
	}

}
