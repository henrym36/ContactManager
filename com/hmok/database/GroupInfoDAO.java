package com.hmok.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import com.hmok.ContactManager.GroupBean;

public class GroupInfoDAO {
	Connection con;
	PreparedStatement ptmt;
	ResultSet rs;

	public long add( GroupBean groupBean )
	{
		try
		{
			String querystring="INSERT INTO GROUP_INFO (joined_on, last_visited, last_attended, total_rsvp, rsvp_yes, " + 
					"rsvp_maybe, rsvp_no, attended, no_show, intro) VALUES (?,?,?,?,?,?,?,?,?,?)";
			con = getConnection();
			ptmt = con.prepareStatement(querystring);
			ptmt.setDate(1, toSqlDate(groupBean.getJoinedOn()));
			ptmt.setDate(2, toSqlDate(groupBean.getLastVisited()));
			ptmt.setDate(3, toSqlDate(groupBean.getLastAttended()));
			ptmt.setLong(4, groupBean.getTotalRSVP());
			ptmt.setLong(5, groupBean.getRSVPYes());
			ptmt.setLong(6, groupBean.getRSVPMaybe());
			ptmt.setLong(7, groupBean.getRSVPNo());
			ptmt.setLong(8, groupBean.getAttended());
			ptmt.setLong(9, groupBean.getNoShow());
			ptmt.setBoolean(10, groupBean.isIntro());
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
				if (rs != null)
					rs.close();
				if (ptmt != null)
					ptmt.close();
				if (con != null)
					con.close();
			}
			catch(SQLException e)
			{
				e.printStackTrace();
			}
		}
		return 0;
	}
	
	private java.sql.Date toSqlDate( Date date ) {
		if (date == null)
			return null;
		return new java.sql.Date(date.getTime());
	}

	
	private Connection getConnection() throws SQLException
	{
		Connection conn;
		conn = ConnectionFactory.getInstance().getConnection();
		return conn;
	}
}
