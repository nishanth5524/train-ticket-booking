
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Confirm {
public Confirm(Connection con,String str,String pnr) throws SQLException {
	
	int temp = 0;
	int tr_seat = 0;

	Statement stmt = con.createStatement();
	ResultSet rs4 = stmt.executeQuery("select R_seat from Train_details where Ctable ='" + str + "'");

	while (rs4.next()) {
		tr_seat = rs4.getInt(1);
	}

	if (tr_seat >= 5) {
		String sql12 = "update Train_details set T_seat = T_seat + 1 where Ctable ='" + str + "'";
		stmt.executeUpdate(sql12);
	}

	Statement stmt2 = con.createStatement();
	stmt2.executeUpdate("Delete from " + str + " where CID = '" + pnr + "'");
	String result1 = null;

	ResultSet rs1 = stmt2
			.executeQuery("select CID from " + str + " where status = 'waiting' ORDER BY CID LIMIT 1");
	while (rs1.next()) {
		result1 = rs1.getString(1);
		temp++;
	}

	if (temp > 0) {
		stmt2.executeUpdate("update " + str + " set status = 'confirm' where CID ='" + result1 + "'");

		String sql12 = "update Train_details set R_seat = R_seat + 1 where Ctable ='" + str + "'";
		stmt2.executeUpdate(sql12);
	}
	System.out.println("ticket cancelled else loop");
	con.close();
	
}
}
