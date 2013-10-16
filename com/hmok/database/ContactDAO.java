package com.hmok.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.hmok.ContactManager.ContactBean;
import com.hmok.display.ContactListItem;

public class ContactDAO
{

	Connection con;
	PreparedStatement ptmt;
	ResultSet rs;

	public ContactDAO()
	{

	}

	private Connection getConnection() throws SQLException
	{
		Connection conn;
		conn = ConnectionFactory.getInstance().getConnection();
		return conn;
	}
	
    /**
     * Insert ContactBean into database 
     */
	public long add (ContactBean contactBean)
	{
		try
		{
			String querystring="INSERT INTO CONTACT " + 
					"(name, user_id, title, member_id, phone, company_id, group_info_id, photo, assistant_org, mailing_list, url) " +
					"VALUES (?,?,?,?,?,?,?,?,?,?,?)";
			con = getConnection();
			ptmt = con.prepareStatement(querystring);
			ptmt.setString(1, contactBean.getName());
			ptmt.setString(2, contactBean.getUserId());
			ptmt.setString(3, contactBean.getTitle());
			ptmt.setLong(4, contactBean.getMemberId());
			ptmt.setString(5, contactBean.getPhone());
			ptmt.setLong(6, contactBean.getCompanyId());
			ptmt.setLong(7, contactBean.getGroupInfoId());
			ptmt.setBoolean(8, contactBean.isPhoto());
			ptmt.setBoolean(9, contactBean.isAssistant());
			ptmt.setString(10, contactBean.getMailingList());
			ptmt.setString(11, contactBean.getUrl());
			ptmt.executeUpdate();

			ResultSet rs = ptmt.getGeneratedKeys();
			if (rs.next()) {
				return (rs.getLong(1));
			} else {
				return 0;
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		finally
		{
			try
			{
				if(rs != null)
					rs.close();
				if(ptmt != null)
					ptmt.close();
				if(con != null)
					con.close();
			}
			catch(SQLException e)
			{
				e.printStackTrace();
			}
		}
		return 0;
	}
	
    /**
     * Updates a contact with company_id and group_info_id
     */
	public void updateIds (ContactBean contactBean)
	{
		try
		{
			String querystring="UPDATE CONTACT SET company_id = ?, group_info_id = ? WHERE contact_id = ?";
			con = getConnection();
			ptmt = con.prepareStatement(querystring);
			ptmt.setLong(1, contactBean.getCompanyId());
			ptmt.setLong(2, contactBean.getGroupInfoId());
			ptmt.setLong(3, contactBean.getContactId());
			ptmt.executeUpdate();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		finally
		{
			try
			{
				if(rs != null)
					rs.close();
				if(ptmt != null)
					ptmt.close();
				if(con != null)
					con.close();
			}
			catch(SQLException e)
			{
				e.printStackTrace();
			}
		}
	}
	
    /**
     * 
     * Gets a list of contacts. Calls ContactListItem to get the query.
     * After execution, the result set is passed back to ContactListItem
     * to create the contact list.
     * 
     */
	public List <ContactListItem> retrieveAllContacts()
	{
		List <ContactListItem> contacts = new ArrayList <ContactListItem> ();
		try
		{
			String querystring = ContactListItem.getAllContactsSQL();
			con = getConnection();
			ptmt = con.prepareStatement(querystring);
			rs = ptmt.executeQuery();
			contacts = ContactListItem.processResults ( rs );
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		finally
		{
			try
			{
				if(rs != null)
					rs.close();
				if(ptmt != null)
					ptmt.close();
				if(con != null)
					con.close();
			}
			catch(SQLException e)
			{
				e.printStackTrace();
			}

		}
		return contacts;
	}
	


}
