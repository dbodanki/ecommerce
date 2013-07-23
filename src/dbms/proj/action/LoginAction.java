package dbms.proj.action;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.opensymphony.xwork2.Action;
import dbms.proj.dbconnection.connector;

public class LoginAction implements Action{
	
	private String userid;
	private String password;
	private Connection conn;

	public String execute()
	{
		connector con1=new connector();
		String LoginQuery="SELECT password,is_admin FROM user WHERE username=?";
		ResultSet rs;
		try {
			setConnection(con1.dbconnection());
			PreparedStatement pStmt = getConnection().prepareStatement(LoginQuery);
			pStmt.setString(1, getUserid());
			rs=pStmt.executeQuery();
			while(rs.next())
			{
				if(getPassword().equalsIgnoreCase(rs.getString(1))&&rs.getBoolean(2))
					return "admin";
				if(getPassword().equalsIgnoreCase(rs.getString(1))&&!(rs.getBoolean(2)))
					return "customer";
			}
		} 
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return LOGIN;
	}

	/**
	 * @return the userid
	 */
	public String getUserid() {
		return userid;
	}

	/**
	 * @param userid the userid to set
	 */
	public void setUserid(String userid) {
		this.userid = userid;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the connection
	 */
	public Connection getConnection() {
		return conn;
	}

	/**
	 * @param connection the connection to set
	 */
	public void setConnection(Connection connection) {
		
		this.conn = connection;
	}
}
