package util;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseUtil {
	public static Connection getConnection() {
		try {
			String dbUrl="jdbc:mysql://localhost:3306/LectureEvaluation";
			String dbID="root";
			String dbPassword="AppDB@2017";
			Class.forName("com.mysql.jdbc.Driver");
			return DriverManager.getConnection(dbUrl,dbID,dbPassword);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
