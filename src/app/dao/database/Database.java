package app.dao.database;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {

	private static Database database;
	private Connection connection;
	private String databaseName;
	private String databaseUser;
	private String databasePassword;
	private String protocol;
	private String driver;
	
	private Database(String databaseName, String databaseUser, String databasePassword,
			String protocol, String driver) {
		this.databaseName = databaseName;
		this.databaseUser = databaseUser;
		this.databasePassword = databasePassword;
		this.protocol = protocol;
		this.driver = driver;
	}


	public static Database newInstance(String databaseName, String databaseUser, String databasePassword, String protocol, String driver) {
		database = (database == null) ? new Database(databaseName, databaseUser, databasePassword, protocol, driver) : database; 
		return database;
	}
	
	
	public boolean doConnection() {
		if(connection == null)
		try {
			Class.forName(driver);
			connection = DriverManager.getConnection(protocol + databaseName, databaseUser, databasePassword); 
			return true;
		} catch(SQLException | ClassNotFoundException e) {		
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	
	public void cerrar() {
		if(connection != null)
			try {
				connection.close();
			} catch(SQLException e) {
				e.printStackTrace();
			}
	}
	
	
	public static Database getDatabase() {
		return database;
	}
	public Connection getConnection() {
		return connection;
	}
	public String getDatabaseName() {
		return databaseName;
	}
	public String getDatabaseUser() {
		return databaseUser;
	}
	public String getDatabasePassword() {
		return databasePassword;
	}
	public String getProtocol() {
		return protocol;
	}
	public String getDriver() {
		return driver;
	}	
	
}
