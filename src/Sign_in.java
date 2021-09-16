
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Sign_in {

	Sign_in(Connection con,int ccount,String tname,String DT,String status) throws SQLException {

     	Scanner sc = new Scanner(System.in);

		int flagphone = 1;
		String Phone_number = null;

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

		System.out.println("Enter your password");

		String password = sc.nextLine();

		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery(
				"select * from sign_up where phone_num = '" + Phone_number + "' and password = '" + password + "'");
		int count = 0;
		while (rs.next()) {
			count = count + 1;
		}
		if (count == 1) {
			System.out.println("User, Found Access Granted!");
			Customer_details obj = new Customer_details(ccount, Phone_number, tname, DT, status,con);
		} else if (count > 1) {
			System.out.println("Duplicate User, Access Denied!");
		} else {
			System.out.println("user doesn't exsist. ");
		}

	}

}
