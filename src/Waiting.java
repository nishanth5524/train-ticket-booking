
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class Waiting {

	public Waiting(Connection con,String str,String pnr) throws SQLException {
		
		Statement stmt1 = con.createStatement();
		stmt1.executeUpdate("Delete from " + str + " where CID = '" + pnr + "'");
		String sql1 = "update Train_details set R_seat = R_seat + 1 where Ctable ='" + str + "'";
		stmt1.executeUpdate(sql1);
		System.out.println("ticket cancelled if loop");
		con.close();
	}
}
