
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Admin {

	public static void main(String[] args) throws Exception {

		Scanner sc = new Scanner(System.in);

		String tname = null;

		int flagtname = 1;

		while (flagtname == 1) {
			System.out.println("Enter Train name");

			tname = sc.nextLine();

			Pattern ptrn = Pattern.compile("^T");

			Matcher m = ptrn.matcher(tname);

			if (m.find()) {
				flagtname = 0;
			}

			else {
				System.out.println("Invalid input\n");
			}

		}

		String from = null;
		int flagfrom = 1;

		while (flagfrom == 1) {
			System.out.println("Enter From");

			from = sc.nextLine();

			Pattern ptrn = Pattern.compile("^[a-zA-Z]*$");

			Matcher m = ptrn.matcher(from);

			if (m.find()) {
				flagfrom = 0;
			}

			else {
				System.out.println("Invalid input\n");
			}

		}

		String to = null;
		int flagto = 1;

		while (flagto == 1) {
			System.out.println("Enter To");

			to = sc.nextLine();

			Pattern ptrn = Pattern.compile("^[a-zA-Z]*$");

			Matcher m = ptrn.matcher(to);

			if (m.find()) {
				flagto = 0;
			}

			else {
				System.out.println("Invalid input\n");
			}

		}

		String date = null;
		int flagdate = 1;

		while (flagdate == 1) {
			System.out.println("Enter Date");

			date = sc.nextLine();

			String regex = "^\\d{4}\\-(0?[1-9]|1[012])\\-(0?[1-9]|[12][0-9]|3[01])$";
			Pattern ptrn = Pattern.compile(regex);

			Matcher m = ptrn.matcher(date);

			if (m.find()) {
				flagdate = 0;
			}

			else {
				System.out.println("Invalid input\n");
			}

		}

		System.out.println("Enter total seat");

		int tseat = sc.nextInt();

		sc.nextLine();

		String deptime = null;
		int flagdeptime = 1;

		while (flagdeptime == 1) {
			System.out.println("Enter Departure time");

			deptime = sc.nextLine();

			Pattern ptrn = Pattern.compile("^(2[0-3]|[01]?[0-9]):([0-5]?[0-9])$");

			Matcher m = ptrn.matcher(deptime);

			if (m.find()) {
				flagdeptime = 0;
			}

			else {
				System.out.println("Invalid input\n");
			}

		}

		String arrtime = null;
		int flagarrtime = 1;

		while (flagarrtime == 1) {
			System.out.println("Enter Arrival time");

			arrtime = sc.nextLine();

			Pattern ptrn = Pattern.compile("^(2[0-3]|[01]?[0-9]):([0-5]?[0-9])$");

			Matcher m = ptrn.matcher(arrtime);

			if (m.find()) {
				flagarrtime = 0;
			}

			else {
				System.out.println("Invalid input\n");
			}

		}

		System.out.println("Enter Waiting list seat count");
		int rseat = sc.nextInt();

		String[] str = date.split("-");

		String cidTable = tname;

		for (int i1 = 0; i1 < str.length; i1++) {
			cidTable = cidTable + str[i1];
		}

		DBConnection obj = new DBConnection();
		Connection con = obj.DB();

		Statement stmt = con.createStatement();

		String sql1 = "insert into Train_details values('" + tname + "','" + from + "','" + to + "','" + date + "','"
				+ tseat + "','" + deptime + "','" + arrtime + "','" + rseat + "','" + cidTable + "','1')";

		stmt.executeUpdate(sql1);

		String sql2 = "create table " + cidTable
				+ "(name varchar(50) NOT NULL,age varchar(50) NOT NULL,phone_num varchar(50) NOT NULL,gender varchar(50) NOT NULL,CID varchar(50) NOT NULL,status varchar(50) NOT NULL);";

		stmt.executeUpdate(sql2);
	}

}
