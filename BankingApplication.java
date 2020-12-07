import java.util.InputMismatchException;
import java.util.Scanner;

public class BankingApplication {

	public static void main(String[] args) {
		BankAccount ba = new BankAccount("Zomer", "0123");
		ba.showMenu();
	}

}

class BankAccount {
	int balance;
	int previousTransac;
	String customerName;
	String customerID;

	BankAccount (String customerName, String customerID) {
		this.customerName = customerName;
		this.customerID = customerID;
	}

	void deposit(int amount) {
		if (!(amount <= 0)) {
			balance = balance + amount;
			previousTransac = amount;
		}
	}

	void withdraw(int amount) {
		if(!(amount <= 0)); {
			balance = balance - amount;
			previousTransac = -amount;
		}
	}

	void getPreviousTransac() {
		if (previousTransac > 0) {
			System.out.println("\nDeposited: " + previousTransac);
		}
		else if (previousTransac < 0) {
			System.out.println("\nWithdrawn: " + Math.abs(previousTransac));
		}
		else {
			System.out.println("\nNo history of transaction.");
		}
	}

	void showMenu() {
		int depositAmount = 0, withdrawAmount = 0;
		String option = "";
		boolean choose = true;
		
		Scanner input = new Scanner(System.in);

		System.out.println("Welcome " + customerName);
		System.out.println("Your ID " + customerID);
		System.out.println("\n");
		System.out.println("A. Check Balance");
		System.out.println("B. Deposit");
		System.out.println("C. Withdraw");
		System.out.println("D. Previous Transaction");
		System.out.println("E. Exit");
		
		while (choose) {
			System.out.print("\nEnter an option: ");
			option = input.nextLine();

			switch(option) {
			case "a":
			case "A":
				System.out.println("\nBalance: " + balance);
				break;

			case "b":
			case "B":
				while (true) {
					try {
						System.out.print("\nEnter an amount of deposit: ");
						depositAmount = input.nextInt();
						input.nextLine();
					}
					catch(InputMismatchException e) {
						System.out.println("Invalid input. Please try again.");
						input.next();
						continue;
					}
					break;
				}
				deposit(depositAmount);
				break;


			case "c":
			case "C":
				while (true) {
					try {
						System.out.print("\nEnter an amount to withdraw: ");
						withdrawAmount = input.nextInt();
						input.nextLine();
					}
					catch(InputMismatchException e) {
						System.out.println("\nInvalid input. Please try again.");
						input.next();
						continue;
					}

					if (withdrawAmount > balance) {
						System.out.println("\nInsufficient balance. Please try again");
						continue;
					}
					else if (balance >= withdrawAmount) {
						break;
					}
				}
				withdraw(withdrawAmount);
				break;

			case "d":
			case "D":
				getPreviousTransac();
				break;
				
			case "e":
			case "E":
				choose = false;
				break;

			default:
				System.out.println("\nInvalid input. Please try again.");
				break;
			}
		}
		
		System.out.println("\nThank you for using our services.");
		input.close();
	}
}
