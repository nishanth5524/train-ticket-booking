
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class All_Classes {

	All_Classes(String tname, Connection con, String sDate) throws SQLException {

		Scanner sc = new Scanner(System.in);

		int bill = 0, flag = 0;

		while (true) {
			System.out.println("---------------Classes----------------");

//			System.out.println("Enter you choice\n\n" + "[1] AC First Class (1A)\n" + "[2] AC 2 Tier (2A)\n"
//					+ "[3] AC 3 Tier (3A)\n" + "[4] Sleeper (SL)\n" + "[5] Second Sitting (2S)\n");

			
			System.out.println("Enter you choice\n\n" + "[1] Sleeper");
			
			int classes = sc.nextInt();

			if (classes == 1) {
				bill = bill + 500;
				flag = 1;

			}

			else {

				System.out.println("Invalid Selection");

			}

			if (flag == 1) {

				Total_count obj = new Total_count(bill, tname, con, sDate);
				break;
			}
		}

	}
}