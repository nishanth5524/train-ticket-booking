
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Cancel_ticket {

	Cancel_ticket(String str, String pnr, Connection con) throws SQLException {

		String result = null;
		int tr_seat = 0;

		Statement stmt = con.createStatement();
		ResultSet resultSet = stmt.executeQuery("select status from " + str + " where CID = '" + pnr + "'");

		if (!resultSet.next()) {
			System.out.println("Ticket Not Found");
			System.exit(0);
		} else {

			System.out.println("In do while");

			do {
				result = resultSet.getString(1);
				System.out.println(result);
			} while (resultSet.next());
		}

		if (result == "waiting") {

			Waiting obj1 = new Waiting(con, str, pnr);

		}

		else {

			Confirm obj = new Confirm(con, str, pnr);

		}
	}
}
