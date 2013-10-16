package com.hmok.ContactManager;

import java.util.Date;

import com.hmok.mapping.CSVFieldMap;

/**
 * Represents group_info table in the database
 * 
 *  @author Henry Mok
 */
public class GroupBean {
	private long groupId;
	private Date joinedOn;
	private Date lastVisited;
	private Date lastAttended;
	private long totalRSVP;
	private long RSVPYes;
	private long RSVPMaybe;
	private long RSVPNo;
	private long attended;
	private long noShow;
	private boolean intro;
	
	private GroupBean ( 
			long groupId,
			Date joinedOn,
			Date lastVisited,
			Date lastAttended,
			long totalRSVP,
			long RSVPYes,
			long RSVPMaybe,
			long RSVPNo,
			long attended,
			long noShow,
			boolean intro) {

		this.groupId = groupId;
		this.groupId = groupId;
		this.joinedOn = joinedOn;
		this.lastVisited = lastVisited;
		this.lastAttended = lastAttended;
		this.totalRSVP = totalRSVP;
		this.RSVPMaybe = RSVPMaybe;
		this.RSVPNo = RSVPNo;
		this.attended = attended;
		this.noShow = noShow;
		this.intro = intro;
	}
	
    /**
     * Replaces constructor. Creates class from a CSV row using 
     * CVSFieldMap to get the correct column
     * 
     * @param input - array of strings from CSV
     */
	public static GroupBean createGroupFromStrArray( String [] input ) {
		
		return new GroupBean(0,
				Util.stringToDate(input[CSVFieldMap.JOINED_ON.ordinal()]),
				Util.stringToDate(input[CSVFieldMap.LAST_VISITED.ordinal()]),
				Util.stringToDate(input[CSVFieldMap.LAST_ATTENDED.ordinal()]),
				Long.parseLong(input[CSVFieldMap.TOTAL_RSVP.ordinal()]),
				Long.parseLong(input[CSVFieldMap.RSVP_YES.ordinal()]),
				Long.parseLong(input[CSVFieldMap.RSVP_MAYBE.ordinal()]),
				Long.parseLong(input[CSVFieldMap.RSVP_NO.ordinal()]),
				Long.parseLong(input[CSVFieldMap.MEETUPS_ATTENDED.ordinal()]),
				Long.parseLong(input[CSVFieldMap.NO_SHOWS.ordinal()]),
				Util.stringToBool(input[CSVFieldMap.INTRO.ordinal()])
				);
	}

	public long getGroupId() {
		return groupId;
	}

	public void setGroupId(long groupId) {
		this.groupId = groupId;
	}

	public Date getJoinedOn() {
		return joinedOn;
	}

	public void setJoinedOn(Date joinedOn) {
		this.joinedOn = joinedOn;
	}

	public Date getLastVisited() {
		return lastVisited;
	}

	public void setLastVisisted(Date lastVisited) {
		this.lastVisited = lastVisited;
	}

	public Date getLastAttended() {
		return lastAttended;
	}

	public void setLastAttended(Date lastAttended) {
		this.lastAttended = lastAttended;
	}

	public long getTotalRSVP() {
		return totalRSVP;
	}

	public void setTotalRSVP(long totalRSVP) {
		this.totalRSVP = totalRSVP;
	}

	public long getRSVPYes() {
		return RSVPYes;
	}

	public void setRSVPYes(long rSVPYes) {
		RSVPYes = rSVPYes;
	}

	public long getRSVPMaybe() {
		return RSVPMaybe;
	}

	public void setRSVPMaybe(long rSVPMaybe) {
		RSVPMaybe = rSVPMaybe;
	}

	public long getRSVPNo() {
		return RSVPNo;
	}

	public void setRSVPNo(long rSVPNo) {
		RSVPNo = rSVPNo;
	}

	public long getAttended() {
		return attended;
	}

	public void setAttended(long attended) {
		this.attended = attended;
	}

	public long getNoShow() {
		return noShow;
	}

	public void setNoShow(long noShow) {
		this.noShow = noShow;
	}

	public boolean isIntro() {
		return intro;
	}

	public void setIntro(boolean intro) {
		this.intro = intro;
	}
	
	
}
