
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Total_count {

	private Total_count() {

	}

	public Total_count(int bill, String tname, Connection con, String sDate) throws SQLException {

		int count = 0;
		int sqlcount = 0;
		int sqlRcount = 0;
		int flag = 1;

		String status = null;

		Scanner sc = new Scanner(System.in);

		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery(
				"select T_seat from Train_details where T_Name = '" + tname + "' and DT ='" + sDate + "'");

		while (rs.next()) {
			System.out.println("Total seats available: " + rs.getInt(1));
			sqlcount = rs.getInt(1);
		}

		while (flag == 1) {
			
			if(sqlcount>0)
			{
			System.out.println("Enter Total number of seats");
			count = sc.nextInt();
			
			if (sqlcount >= count) {
				status = "confirm";
				break;
			}
			}
			else {

				if (sqlcount == 0) {
					flag = 0;

					System.out.println("\nInavailability of seats");

					while (true) {

						System.out.println("\nEnter [1] to view availability of Waiting list seat count");

						int choice = sc.nextInt();

						if (choice == 1) {

							ResultSet rs1 = stmt.executeQuery("select R_seat from Train_details where T_Name = '"
									+ tname + "' and DT ='" + sDate + "'");

							while (rs1.next()) {
								System.out.println("Total Waiting list seats available: " + rs1.getInt(1));
								sqlRcount = rs1.getInt(1);
							}

							System.out.println("Enter Total number of Waiting list seat count");
							count = sc.nextInt();

							if (sqlRcount >= count) {
								status = "waiting";
								break;
							}

							else {
								System.out.println("\nInavailability of seats");
							}
						}

						else {
							System.out.println("Invalid Input:");
						}

					}

				}

				ResultSet rs1 = stmt.executeQuery("select R_seat from Train_details where T_Name = '"
						+ tname + "' and DT ='" + sDate + "'");

				while (rs1.next()) {
					System.out.println("Total Waiting list seats available: " + rs1.getInt(1));
					sqlRcount = rs1.getInt(1);
				}

				System.out.println("Enter Total number of Waiting list seat count");
				count = sc.nextInt();

				if (sqlRcount >= count) {
					status = "waiting";
					break;
				}

				else {
					System.out.println("\nInavailability of seats");
				}
			
			}
		}

		int tbill = bill * count;
		System.out.println("Train Name: " + tname);
		System.out.println("Total Amount: " + tbill + "₹");

		In_or_Up obj = new In_or_Up(con, count, tname, sDate, status);
	}

}
