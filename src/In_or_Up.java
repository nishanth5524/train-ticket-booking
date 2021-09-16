
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class In_or_Up {

	In_or_Up(Connection con, int ccount, String tname, String DT, String status) throws SQLException {

		Scanner sc = new Scanner(System.in);

		while (true) {
			System.out.println("Signin or Signup to book your ticket\n\nEnter your choice\n[1] Sign_up\n[2] Sign_in");

			int schoice = sc.nextInt();

			if (schoice == 1) {
				Sign_up obj1 = new Sign_up(con, ccount, tname, DT, status);
				break;

			}

			else if (schoice == 2) {
				Sign_in obj1 = new Sign_in(con, ccount, tname, DT, status);
				break;
			}

			else {

				System.out.println("Invalid Selection");

			}
		}
	}
}
