package dbms.proj.dbconnection;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class connector {
	
	public Connection dbconnection() throws SQLException, ClassNotFoundException{
		Properties prop=new Properties();
		try{
			InputStream in=getClass().getResourceAsStream("connection.properties");
			prop.load(in);

		}
		catch (IOException E){
			throw new RuntimeException(E);
		}
		String URL = prop.getProperty("url");
		Class.forName(prop.getProperty("driver"));
		String userid=prop.getProperty("username");
		String password=prop.getProperty("password");
		Connection conn = DriverManager.getConnection(URL, userid, "");
		return conn;
	}
}
