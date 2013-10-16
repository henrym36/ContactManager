package com.hmok.ContactManager;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.hmok.mapping.CSVFieldMap;
import com.hmok.mapping.DataType;

/**
 * Verifies a row from the CSV file
 * 
 *  @author Henry Mok
 */
public class LineChecker {
	final int NUMBER_FIELDS_IN_ROW = 20;

    /**
     * Checks the strings in the input row to make sure that it's the
     * correct data type for that column. Data type defined in CSVFieldMap
     * 
     * @param titleRowValues - Title row from CSV. Used to generate error messages
     * @param rowValues - CSV row to validate
     * @param rowNumber - Row number. Used to generate error messages
     */
	public List<String> checkLine ( String [] rowValues, int rowNumber ) {
		List <String> errorMsgs = new ArrayList <String>();

		if ( rowValues.length != NUMBER_FIELDS_IN_ROW) {
			errorMsgs.add("Row " + rowNumber + " incorrect number of fields");
			return errorMsgs;
		}
		for ( CSVFieldMap fm : CSVFieldMap.values()) {
			if ( fm.getDataType() == DataType.DATE ) {
				if ( ! StringIsDate ( rowValues[fm.ordinal()] )) {
					errorMsgs.add("Row " + rowNumber + " \"" + fm.getColumnName() + 
							"\" must contain a date (" + rowValues[fm.ordinal()] + ")");
				}
			} else if ( fm.getDataType() == DataType.LONG ) {
				if ( ! StringIsLong ( rowValues[fm.ordinal()] )) {
					errorMsgs.add("Row " + rowNumber + " \"" + fm.getColumnName() + 
							"\" must contain a long (" + rowValues[fm.ordinal()] + ")");
				}
			} else if ( fm.getDataType() == DataType.BOOLEAN ) {
				if ( ! StringIsBoolean ( rowValues[fm.ordinal()] )) {
					errorMsgs.add("Row " + rowNumber + " \"" + fm.getColumnName() + 
							"\" must contain a Yes or No (" + rowValues[fm.ordinal()] + ")");
				}
			} else if ( fm.getDataType() == DataType.PHONE ) {
				if ( ! StringIsPhone ( rowValues[fm.ordinal()] )) {
					errorMsgs.add("Row " + rowNumber + " \"" + fm.getColumnName() + 
							"\" must contain a phone number (" + rowValues[fm.ordinal()] + ")");
				}
			}
		}
		return errorMsgs;
	}
	
	private boolean StringIsLong ( String input ) {
		if (input.length() == 0) {
			return true;
		}
		if (input.matches("\\d+")) {
			return true;
		}
		return false;
	}
	
	private boolean StringIsBoolean ( String input ) {
		if (input.length() == 0) {
			return true;
		}
		if (input.toLowerCase().equals("yes") || input.toLowerCase().equals("no")) {
			return true;
		}
		return false;
	}
	
	private boolean StringIsDate ( String input ) {
		if (input.length() == 0) {
			return true;
		}
		if (Util.stringToDate(input) == null) {
			return false;
		}
		return true;
	}
	
	private boolean StringIsPhone ( String input ) {
		if (input.length() == 0) {
			return true;
		}
		Pattern pattern = Pattern.compile("\\d{3}-\\d{3}-\\d{4}");
		Matcher matcher = pattern.matcher(input);
		if (matcher.matches()) {
			return true;
		}
		else
		{
			return false;
		}
		
	}
}
