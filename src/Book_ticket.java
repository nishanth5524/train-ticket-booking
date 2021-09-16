
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class Book_ticket {

	Book_ticket() throws Exception {

		int flagfrom = 1;
		int flagto = 1;
		int flagdate = 1;

		String from = null;
		String to = null;
		String sDate = null;
		String tname = null;

		DBConnection ob = new DBConnection();
		Connection con = ob.DB();

		Scanner sc = new Scanner(System.in);

		Pattern p = Pattern.compile("^[a-zA-Z]*$");

		while (flagfrom == 1) {
			System.out.println("From: ");

			from = sc.nextLine();

			Matcher m = p.matcher(from);

			if (m.find()) {
				flagfrom = 0;
			}

			else {
				System.out.println("Invalid from input\n");
			}

		}

		while (flagto == 1) {
			System.out.println("\nTo: ");

			to = sc.nextLine();

			Matcher m = p.matcher(to);

			if (m.find()) {
				flagto = 0;
			}

			else {
				System.out.println("Invalid to input\n");
			}

		}

		while (flagdate == 1) {

			System.out.println("\nEnter date (yyyy-mm-dd) ");

			sDate = sc.nextLine();

			String regex = "^\\d{4}\\-(0?[1-9]|1[012])\\-(0?[1-9]|[12][0-9]|3[01])$";

			Pattern p1 = Pattern.compile(regex);

			Matcher m = p1.matcher(sDate);

			if (m.find()) {
				flagdate = 0;
			}

			else {
				System.out.println("Invalid date input\n");
			}

		}

		try {
			int count = 0;
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("select T_Name from Train_details where From_l = '" + from
					+ "' and To_l ='" + to + "' and DT = '" + sDate + "'");

			if (rs.next() == false) {
				System.out.println("Train is not available");
			}

			else {

				do {

					if (count == 0) {
						System.out.println("Train availability list");
					}

					count++;
					System.out.println("Train name: " + rs.getString(1));
					tname = rs.getString(1);
				} while (rs.next());

				All_Classes ac = new All_Classes(tname, con, sDate);
			}
		} catch (Exception ex) {
			System.out.println("Train is not available" + ex);
		}

	}

}
