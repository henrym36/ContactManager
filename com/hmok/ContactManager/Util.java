package com.hmok.ContactManager;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Utility methods
 * 
 *  @author Henry Mok
 */
public class Util {
	
    private Util() {
    }
	
	public static Boolean stringToBool( String input ) {
		return (input.trim().toLowerCase().equals("yes")) ? true : false;
	}
	
	public static Date stringToDate( String dateStr ) {
		if (dateStr.trim().equals("")) {
			return null;
		}
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy"); 
        try {
			return df.parse(dateStr);
		} catch (ParseException e) {
			return null;
		}
	}
}
