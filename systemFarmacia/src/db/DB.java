package db;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;


public class DB {
	
	private static Connection comn = null;
		
	public static Connection getConnection() {
	    if (comn == null) {
	    	try {
				Properties props = loadProperties();  
				String url = props.getProperty("dburl");
				comn = DriverManager.getConnection(url,props);
	    	}
	    	catch(SQLException e) {
	    		throw new DBExeception(e.getMessage());
	    	}
		}
		return comn;
	}
	
	public static void closeConnection() {
		if (comn != null) {
			try {
				comn.close();
			}
			catch(SQLException e) {
				throw new DBExeception(e.getMessage());
			}
		}
	}
	
	
	private static Properties loadProperties() {
		try (FileInputStream fs = new FileInputStream("db.properties")){
			Properties props = new Properties();
			props.load(fs);
			return props;
		}
		catch(IOException e){
			throw new DBExeception(e.getMessage());
		}
	}
}
