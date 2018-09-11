package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public class databaseDao {
	
	private Connection connect=null;
	private Statement statement=null;
	private ResultSet resultset=null;
	public static String classname="com.mysql.jdbc.Driver";
	public static String url="jdbc:mysql://localhost:3306/mynews";
	public static String user="root";
	public static String passwd="";
	
	public databaseDao() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException
	{
		Class.forName(classname).newInstance();
		connect=DriverManager.getConnection(url,user,passwd);
		statement=connect.createStatement();
	}
	public void query(String sql) throws SQLException
	{
		resultset=statement.executeQuery(sql);
	}
	public boolean next() throws SQLException
	{
		return resultset.next();
	}
	public String getString(String field) throws SQLException
	{
		return resultset.getString(field);
	}
	public Double getDouble(String field) throws SQLException
	{
		return resultset.getDouble(field);
	}
	public Integer getInteger(String field) throws SQLException
	{
		return resultset.getInt(field);
	}
	public Timestamp getTimestamp(String field) throws SQLException
	{
		return resultset.getTimestamp(field);
	}
	public LocalDateTime getLocalDateTime(String field) throws SQLException{
		return resultset.getTimestamp(field).toLocalDateTime();
	}
	public void close() throws SQLException
	{
		if(connect!=null)connect.close();
	}
	public void update(String sql) throws SQLException
	{
		statement.execute(sql);
	}
	public void commit() throws SQLException{
		connect.commit();
	}	
	public void setAutoCommit(boolean f) throws SQLException{
		connect.setAutoCommit(f);
	}
}
