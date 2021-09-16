
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Sign_up {

	Sign_up(Connection con, int ccount,String tname,String DT,String status) throws SQLException {
		String gender;
		String password;

		int flagphone = 1;
		int flagname = 1;
		int flagage = 1;
		String Phone_number = null;
		String name = null;
		String age = null;

		Scanner sc = new Scanner(System.in);

		while (flagphone == 1) {
			System.out.println("Enter you Phone number");

			Phone_number = sc.nextLine();

			Pattern ptrn = Pattern.compile("(0|91)?[6-9][0-9]{9}");

			Matcher m = ptrn.matcher(Phone_number);

			if (m.find()) {
				flagphone = 0;
			}

			else {
				System.out.println("Invalid from input\n");
			}

		}
		
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery(
				"select * from sign_up where phone_num = '" + Phone_number + "'");
		int count = 0;
		
		while (rs.next()) {
			count = count + 1;
		}
		
		if(count>0)
		{
			System.out.println("user already exists");
			System.out.println("Sign in");
			Sign_in obj1 = new Sign_in(con,ccount,tname, DT, status);
			System.exit(0);
			
		}

		while (flagname == 1) {
			System.out.println("Enter your name");

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
			System.out.println("Enter your age");
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

		while (true) {

			System.out.println("Choose your gender\n\n[1] Male\n[2] Female\n[3] Prefer not to say");

			int sgender = sc.nextInt();

			if (sgender == 1) {
				gender = "male";
				break;
			}

			else if (sgender == 2) {
				gender = "female";
				break;
			}

			else if (sgender == 3) {
				gender = "Prefer not to say";
				break;
			} else {
				System.out.println("Invaled Selection");
			}
		}

		sc.nextLine();

		while (true) {

			System.out.println("Enter your password");

			password = sc.nextLine();

			System.out.println("Re-Enter your password");

			String repassword = sc.nextLine();

			if (password.equals(repassword)) {
				System.out.println("Your account is created successfully!");
				Customer_details obj = new Customer_details(ccount, Phone_number, tname, DT, status,con);
				break;
			} else {
				System.out.println("Invalid");
			}

		}

		Statement stmt1 = con.createStatement();
		stmt1.executeUpdate("insert into sign_up values('" + Phone_number + "','" + name + "','" + age + "','" + gender
				+ "','" + password + "')");

	}

}
