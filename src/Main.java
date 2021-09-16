
import java.text.ParseException;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws Exception {

		System.out.println("-------------------------------");
		System.out.println("- Train ticket booking system -");
		System.out.println("-------------------------------\n");

		Scanner sc = new Scanner(System.in);

		while (true) {

			System.out.println("\n1. Book Ticket");
			System.out.println("2. Ticket Status");
			System.out.println("3. Exit\n");

			int n = sc.nextInt();

			if (n == 1) {
				// Book Ticket

				Book_ticket bt = new Book_ticket();

			}

			else if (n == 2) {
				// Ticket Status

				Ticket_status ts = new Ticket_status();
			}

			else if (n == 3) {
				System.out.println("Thank you :-)");
				System.exit(0);

			}

			else {
				System.out.println("Invalid Number");
			}

		}
	}

}
