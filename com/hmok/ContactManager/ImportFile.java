package com.hmok.ContactManager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.servlet.http.Part;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;

import com.hmok.database.CompanyDAO;
import com.hmok.database.ContactDAO;
import com.hmok.database.GroupInfoDAO;

/**
 * Takes a CSV file, verifies the file and saves the
 * file information to the database
 * 
 *  @author Henry Mok
 */
@ManagedBean
@SessionScoped
public class ImportFile {
	private Part file;
	private String fileContent;
	FacesMessage message = new FacesMessage();
	List <String> allErrorMsgs = new ArrayList <String> ();

	public String loadFile() {
		allErrorMsgs.clear();
		try {
			fileContent = new Scanner(file.getInputStream()).useDelimiter("\\A").next();
			processFile( fileContent );
			if (allErrorMsgs.isEmpty()) {
				allErrorMsgs.add("File imported successfully");
			}
		} catch (IOException e) {
			allErrorMsgs.add("Problem reading file");
		}
		return "result?faces-redirect=true";
	}
	
    /**
     * Takes the input file, parses it creates contact,
     * company and group objects. Saves objects to the database
     * 
     * @param fileContent - contents of the input file
     */
	private void processFile( String fileContent ) {
		String line;
		String cvsSplitBy = ",";
		String[] rowValues;
		int lineCounter = 0;
		List <String> lineErrorMsgs;
		long contactId;
		
		if (fileContent == null){
			return;
		}
		
		BufferedReader reader = new BufferedReader(new StringReader(fileContent));
		try {
		    while ((line = reader.readLine()) != null) {
		    	lineCounter++;
		    	rowValues = line.split(cvsSplitBy);
		    	//skip title row
		    	if (lineCounter == 1) {
		    		continue;
		    	}
		    	LineChecker lineChecker = new LineChecker();
		    	lineErrorMsgs = lineChecker.checkLine( rowValues, lineCounter );
		    	
		    	//if there is an error from lineChecker, save 
		    	//errors and do not process this line
		    	if (lineErrorMsgs.size() > 0) {
		    		allErrorMsgs.addAll(lineErrorMsgs);
		    		continue;
		    	}
		    	
				ContactBean contact = ContactBean.createContactFromStrArray( rowValues );
				ContactDAO contactDAO = new ContactDAO();
				contactId = contactDAO.add(contact);
				//Problem adding contact
				if (contactId == 0) {
					allErrorMsgs.add("Row " + lineCounter + " problem adding this contact" );
					continue;
				}
				contact.setContactId(contactId);
				
				CompanyBean company = CompanyBean.createCompanyFromStrArray( rowValues );
				CompanyDAO companyDAO = new CompanyDAO();
				contact.setCompanyId(companyDAO.add(company));
				
				GroupBean group = GroupBean.createGroupFromStrArray( rowValues );
				GroupInfoDAO groupInfoDAO = new GroupInfoDAO();
				contact.setGroupInfoId(groupInfoDAO.add(group));
				
				contactDAO.updateIds(contact);

		    }
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
    /**
     * Check file to see if it's empty or doesn't have a .csv extension
     * 
     */
	public void validateFile(FacesContext ctx, UIComponent comp, Object value) {
		FacesContext context = FacesContext.getCurrentInstance();
		Part file = (Part)value;
		
		if (file.getSize() == 0) {
			message.setSummary("Please select a file");
		} else if (!"application/vnd.ms-excel".equals(file.getContentType())) {
			message.setSummary("File of incorrect type");
		}
		
		if (!(message.getSummary() == null)) {
			context.addMessage(null, message);
			throw new ValidatorException(message);
		}
	}
	
	public List<String> getAllErrorMsgs() {
		return allErrorMsgs;
	}

	public void setAllErrorMsgs(List<String> allErrorMsgs) {
		this.allErrorMsgs = allErrorMsgs;
	}

	public Part getFile() {
		return file;
	}

	public void setFile(Part file) {
		this.file = file;
	}
}
