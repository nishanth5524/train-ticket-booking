
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Customer_details {

	public Customer_details(int ccount, String phone_num, String tname, String date, String status, Connection con)
			throws SQLException {

		int flagname = 1;
		int flagage = 1;
		int flaggender = 1;
		int count = 1;
		String name = null;
		String age = null;
		String gender = null;

		String sql1 = null;
		String sql2 = null;

		Scanner sc = new Scanner(System.in);

		for (int i = 1; i <= ccount; i++) {

			flagname = 1;
			flagage = 1;
			flaggender = 1;

			while (flagname == 1) {
				System.out.println("Enter passenger " + count + " Name");

				name = sc.nextLine();

				Pattern ptrn = Pattern.compile("^[a-zA-Z]*$");

				Matcher m = ptrn.matcher(name);

				if (m.find()) {
					flagname = 0;
				}

				else {
					System.out.println("Invalid from input\n");
				}

			}
			while (flagage == 1) {
				System.out.println("Enter passenger " + count + " Age");
				age = sc.nextLine();

				Pattern ptrn = Pattern.compile("^[0-9]*$");

				Matcher m = ptrn.matcher(age);

				if (m.find()) {
					flagage = 0;
				}

				else {
					System.out.println("Invalid from input\n");
				}

			}

			while (flaggender == 1) {

				System.out.println(
						"Choose passenger " + count + " gender\n\n[1] Male\n[2] Female\n[3] Prefer not to say");

				int sgender = sc.nextInt();

				if (sgender == 1) {
					gender = "male";
					flaggender = 0;

				}

				else if (sgender == 2) {
					gender = "female";
					flaggender = 0;

				}

				else if (sgender == 3) {
					gender = "Prefer not to say";
					flaggender = 0;

				} else {
					System.out.println("Invaled Selection");
				}
			}
			String[] str = date.split("-");

			String cidTable = tname;

			for (int i1 = 0; i1 < str.length; i1++) {
				cidTable = cidTable + str[i1];
			}

			String cid = cidTable;

			String TR = "TR";

			cid = cid + TR;

			Statement stmt = con.createStatement();

			int t_seat = 0;

			Statement stmt2 = con.createStatement();
			ResultSet rs = stmt2.executeQuery(
					"select count from Train_details where T_Name = '" + tname + "' and DT ='" + date + "'");

			while (rs.next()) {
				t_seat = rs.getInt(1);
			}

			if (t_seat < 10) {

				cid = cid + 0;
				cid = cid + t_seat;
				
			} else {
				cid = cid + t_seat;
			}
			System.out.println("PNR: " + cid);

			String sql = "insert into " + cidTable + " values('" + name + "','" + age + "','" + phone_num + "','"
					+ gender + "','" + cid + "','" + status + "');";

			int rstt = stmt.executeUpdate(sql);

			if (rstt == 1) {
				String sql5 = "update Train_details set count = count + 1 where Ctable ='" + cidTable + "'";

				if (status == "confirm") {
					sql1 = "update Train_details set T_seat = T_seat - 1 where Ctable ='" + cidTable + "'";
					stmt.executeUpdate(sql1);
				}

				else {
					sql2 = "update Train_details set R_seat = R_seat - 1 where Ctable ='" + cidTable + "'";
					stmt.executeUpdate(sql2);
				}

				stmt.executeUpdate(sql5);

			}

			count++;

			sc.nextLine();

		}

		con.close();

	}

}
