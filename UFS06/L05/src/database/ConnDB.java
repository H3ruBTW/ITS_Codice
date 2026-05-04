package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public abstract class ConnDB {
	public static Connection openConnection() {
		
		String dbName = "java";
		String ipServer = "127.0.0.1";
		
		Connection con = null;
		try {
			String driver = "com.mysql.jdbc.Driver";
			Class.forName(driver);
			String url = "jdbc:mysql://"+ipServer+"/"+dbName+"?useSSL=false";
			con = DriverManager.getConnection(url, "root", "1234");
		}
		catch (ClassNotFoundException e){
			e.printStackTrace();
		}
		catch (SQLException e){
			e.printStackTrace();
		}

		return con;
	}

	public static void closeConnection(Connection con) {
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
