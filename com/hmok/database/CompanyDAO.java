package com.hmok.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.hmok.ContactManager.CompanyBean;
import com.hmok.display.CompanyListItem;

public class CompanyDAO {
	Connection con;
	PreparedStatement ptmt;
	ResultSet rs;

	private Connection getConnection() throws SQLException
	{
		Connection conn;
		conn = ConnectionFactory.getInstance().getConnection();
		return conn;
	}
	
    /**
     * Insert CompanyBean into database 
     */
	public long add( CompanyBean companyBean )
	{
		CompanyBean cb = findByCompanyName (companyBean.getName());
		if (cb != null) {
			return cb.getCompanyId();
		}
		try
		{
			String querystring = "INSERT INTO COMPANY (company_name) VALUES(?)";
			con = getConnection();
			ptmt = con.prepareStatement(querystring);
			ptmt.setString(1, companyBean.getName());
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
	
    /**
     * creates a CompanyBean by company name
     */
	public CompanyBean findByCompanyName (String name)
	{
		CompanyBean companyBean = null;
		try
		{
			String querystring = "SELECT company_id, company_name, company_addr, company_phone FROM COMPANY WHERE company_name=?";
			con = getConnection();
			ptmt = con.prepareStatement(querystring);
			ptmt.setString(1, name);
			rs = ptmt.executeQuery();
			if (rs.next())
			{
				companyBean = CompanyBean.createCompanyFromFieldVals(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4));

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
		return companyBean;
	}
	
    /**
     * 
     * Gets a list of companies. Calls CompanyListItem to get the query.
     * After execution, the result set is passed back to CompanyListItem
     * to create the company list.
     * 
     */
	public List <CompanyListItem> retrieveAllContacts()
	{
		List <CompanyListItem> companies = new ArrayList <CompanyListItem> ();
		try
		{
			String querystring = CompanyListItem.getAllCompanySQL();
			con = getConnection();
			ptmt = con.prepareStatement(querystring);
			rs = ptmt.executeQuery();
			companies = CompanyListItem.processResults ( rs );
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
		return companies;
	}
}
