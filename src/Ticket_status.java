
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Ticket_status {

	Ticket_status() throws Exception {

		Scanner sc = new Scanner(System.in);

		int flag = 1;
		String pnr = null;
		while(flag == 1)
		{
		System.out.println("Enter Passenger Name Record(PNR) Number: ");

		pnr = sc.nextLine();

		Pattern p = Pattern.compile("^T");
		
		Matcher m = p.matcher(pnr);

		if (m.find()) {
			flag = 0;
		}

		else {
			System.out.println("Invalid from input\n");
		}
		
		}

		DBConnection obj = new DBConnection();
		Connection con = obj.DB();

		String[] str = pnr.split("TR");

		try {
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery("select * from " + str[0] + " where CID = '" + pnr + "'");
		while (rs.next()) {
			System.out.println("Name: " + rs.getString(1) + "\nAge: " + rs.getString(2) + "\nPhone Number: "
					+ rs.getString(3) + "\nGender: " + rs.getString(4) + "\nPNR: " + rs.getString(5) + "\nStatus: "
					+ rs.getString(6));
		}
		}
		catch(Exception e)
		{
			System.out.println("Invalid PNR");
			System.exit(0);
		}
		while (true) {

			System.out.println("\n1. Cancel ticket");
			System.out.println("2. Exit");

			int n = sc.nextInt();

			if (n == 1) {

				Cancel_ticket obj1 = new Cancel_ticket(str[0], pnr, con);
			}

			else if (n == 2) {
				break;
			}

			else {
				System.out.println("Invalid Number");
			}
		}

	}

}
