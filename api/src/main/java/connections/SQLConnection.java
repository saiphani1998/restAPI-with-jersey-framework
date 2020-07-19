package connections;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLConnection
{
	
	private final String URL = "jdbc:mysql://localhost:3306/";
	private final String USER = "root";
	private final String PASSWORD = "root";
	
	public Connection getConnection(String db)
	{
		db = "test_db";
		Connection connection = null;
		
		try
		{
            Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(URL + db, USER, PASSWORD);
		}
		catch(SQLException e)
		{
			System.out.println(e.getMessage());
		} 
		catch (ClassNotFoundException e)
		{
			e.printStackTrace();
		}
		
		return connection;
	}
}
