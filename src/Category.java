
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class Category {

	int bill;
	String tname;
	Connection con;

	private Category() {

	}

	Category(int bill,String tname,Connection con) throws SQLException {

		this.bill = bill;
		this.tname = tname;
		this.con = con;

		Scanner sc = new Scanner(System.in);

		while (true) {
			
		System.out.println("\n\nDifferently abled\nEnter yes or no" );

			String category = sc.nextLine();

			if (category.equals("y")  || category.equals("Y")){
				bill = bill / 2;
				System.out.println("Total Amount: " + bill + "₹");
				//In_or_Up obj = new In_or_Up(con);
				break;

			}

			else if(category.equals("n")  || category.equals("N")){
				System.out.println("Train Name: "+ tname);
				System.out.println("Total Amount: " + bill + "₹");
				//In_or_Up obj = new In_or_Up(con);
				break;
			}
			
			else{

				System.out.println("Invalid Selection");

			}


		}

	}
}