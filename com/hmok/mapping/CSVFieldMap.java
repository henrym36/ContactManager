package com.hmok.mapping;

/**
 * Stores the fields and the data types in the CSV
 * 
 *  @author Henry Mok
 */
public enum CSVFieldMap {
	NAME 		(DataType.STRING,"Name"),
	USERID 		(DataType.STRING,"User ID"),
	TITLE 		(DataType.STRING,"Title"),
	MEMBERID 	(DataType.LONG,"Member ID"),
	PHONE 		(DataType.PHONE,"Phone Number"),
	COMPANY 	(DataType.STRING,"Company"),
	JOINED_ON 	(DataType.DATE,"Joined Group On"),
	LAST_VISITED (DataType.DATE,"Last Visited Group On"),
	LAST_ATTENDED (DataType.DATE,"Last Attended"),
	TOTAL_RSVP 	(DataType.LONG,"Total RSVPs"),
	RSVP_YES 	(DataType.LONG,"RSVPed Yes"),
	RSVP_MAYBE 	(DataType.LONG,"RSVPed Maybe"),
	RSVP_NO 	(DataType.LONG,"RSVPed No"),
	MEETUPS_ATTENDED (DataType.LONG,"Meetups Attended"),
	NO_SHOWS 	(DataType.LONG,"No Shows"),
	INTRO 		(DataType.BOOLEAN,"Intro"),
	PHOTO 		(DataType.BOOLEAN,"Photo"),
	ASSISTANT 	(DataType.BOOLEAN,"Assistant Organizer"),
	MAILING_LIST (DataType.STRING,"Mailing List"),
	URL 		(DataType.STRING,"URL of Member Profile");
	
	private final DataType dataType;
	private final String columnName;
	
	private CSVFieldMap ( DataType dataType, String columnName ) {
		this.dataType = dataType;
		this.columnName = columnName;
	}
	
	public DataType getDataType() {
		return dataType;
	}

	public String getColumnName() {
		return columnName;
	}
}
