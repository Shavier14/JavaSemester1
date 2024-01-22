import java.sql.*;
import javax.swing.*;
public class StoreData {
	
	public static Connection ConnectDB()
	{
		try 
		{
			Class.forName("org.sqlite.JDBC");
			Connection conn = DriverManager.getConnection
					("jdbc:sqlite:C:\\Users\\ASUS\\eclipse-workspace\\StoreData\\Storedata.db");
			JOptionPane.showMessageDialog(null, "Connection Made");
			return conn;
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null, "Connection Error");
			return null;
		}
	}

}
	
