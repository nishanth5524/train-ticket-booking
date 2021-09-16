
import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {
	
	public Connection DB() throws Exception {
		Class.forName("com.mysql.cj.jdbc.Driver");
		String url = "jdbc:mysql://localhost:3306/train_ticket_booking_system";
		String username = "nishanth";
		String password = "Nishanth@5524";

		Connection con = DriverManager.getConnection(url, username, password);
//		if (con != null) {
//			System.out.println("Database Connected successfully");
//		} else {
//			System.out.println("Database Connection failed");
//		}
		
		return con;
	}
}
