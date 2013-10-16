package com.hmok.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ConnectionFactory
{
	private static ConnectionFactory connectionFactory=null;

	private ConnectionFactory()
	{
		ResourceBundle properties = ResourceBundle.getBundle("local");
		try
		{
			Class.forName(properties.getString("driverClassName"));
		}
		catch(ClassNotFoundException e)
		{
			e.printStackTrace();
		}
	}
	 
	public Connection getConnection() throws SQLException
	{
		ResourceBundle properties = ResourceBundle.getBundle("local");
		Connection conn = null;
		conn = DriverManager.getConnection( 
				properties.getString("connectionUrl"),properties.getString("dbUser"),properties.getString("dbPwd"));
		return conn;
	}
	 
	public static ConnectionFactory getInstance()
	{
		if (connectionFactory == null)
		{
			connectionFactory = new ConnectionFactory();
		}
		return connectionFactory;
	}
}
