package com.hmok.display;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Used to display contact information
 * 
 *  @author Henry Mok
 */
public class ContactListItem {
	private String name;
	private String userId;
	private String title;
	private long memberId;
	private String company;
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
	private boolean photo;
	private boolean assistant;
	private String mailingList;
	private String url;
	
	private ContactListItem() {
	}
	
    /**
     * Sql to run to get all the contacts
     */
	public static String getAllContactsSQL () {
		return "SELECT ct.name, ct.user_id, ct.title, ct.member_id, cm.company_name, gi.joined_on, " +
				"gi.last_visited, gi.last_attended, gi.total_rsvp, gi.rsvp_yes, " +
				"gi.rsvp_maybe, gi.rsvp_no, gi.attended, gi.no_show, gi.intro, " +
				"ct.photo, ct.assistant_org, ct.mailing_list, ct.url " +
				"FROM contact ct, company cm, group_info gi " +
				"WHERE ct.company_id = cm.company_id AND ct.group_info_id = gi.group_info_id ";
	}
	
    /**
     * Creates a list of ContactListItem from a result set
     * 
     * @param rs - result set from getAllContactsSQL
     */
	public static List <ContactListItem> processResults ( ResultSet rs ) {
		List <ContactListItem> contacts = new ArrayList <ContactListItem> ();
		try {
			while(rs.next())
			{
				ContactListItem contactListItem = new ContactListItem();
				contactListItem.setName(rs.getString("name"));
				contactListItem.setUserId(rs.getString("user_id"));
				contactListItem.setTitle(rs.getString("title"));
				contactListItem.setMemberId(rs.getLong("member_id"));
				contactListItem.setCompany(rs.getString("company_name"));
				contactListItem.setJoinedOn(rs.getDate("joined_on"));
				contactListItem.setLastVisited(rs.getDate("last_visited"));
				contactListItem.setLastAttended(rs.getDate("last_attended"));
				contactListItem.setTotalRSVP(rs.getLong("total_rsvp"));
				contactListItem.setRSVPYes(rs.getLong("rsvp_yes"));
				contactListItem.setRSVPMaybe(rs.getLong("rsvp_maybe"));
				contactListItem.setRSVPNo(rs.getLong("rsvp_no"));
				contactListItem.setAttended(rs.getLong("attended"));
				contactListItem.setNoShow(rs.getLong("no_show"));
				contactListItem.setIntro(rs.getBoolean("intro"));
				contactListItem.setPhoto(rs.getBoolean("photo"));
				contactListItem.setAssistant(rs.getBoolean("assistant_org"));
				contactListItem.setMailingList(rs.getString("mailing_list"));
				contactListItem.setUrl(rs.getString("url"));
				contacts.add(contactListItem);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return contacts;
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
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
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
	public void setLastVisited(Date lastVisited) {
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
