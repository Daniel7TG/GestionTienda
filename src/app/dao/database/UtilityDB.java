package app.dao.database;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import app.records.DBRecord;

public class UtilityDB {

	public static DBRecord getDataBaseParameters() {
		Properties properties = new Properties();
		try {
			properties.load(new FileInputStream(new File("resources/config/config.properties")));
			String database = (String) properties.get("DATA_BASE");
			String user = (String) properties.get("USER");
			String password = (String) properties.get("PASSWORD");
			String driver = (String) properties.get("DRIVER");
			String protocol = (String) properties.get("PROTOCOL");
			return new DBRecord(database, user, password, driver, protocol);
		} catch(IOException e) {
			e.printStackTrace();
		}
		return null;
	}

}
