
import java.util.Scanner;
import java.util.InputMismatchException;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        double balance = 5000;

        try {
            System.out.println("Card reader connected successfully.");

            System.out.print("Enter account number: ");
            int account = sc.nextInt();

            System.out.print("Enter withdrawal amount: ");
            double amount = sc.nextDouble();

            if (amount < 0) {
                throw new IllegalArgumentException(
                    "Withdrawal amount cannot be negative."
                );
            }

            if (amount > balance) {
                throw new ArithmeticException(
                    "Insufficient account balance."
                );
            }

            balance -= amount;

            System.out.println("Withdrawal successful!");
            System.out.println("Account number: " + account);
            System.out.println("Withdrawn amount: " + amount);
            System.out.println("Remaining balance: " + balance);

        } catch (InputMismatchException | IllegalArgumentException e) {
            System.out.println("Invalid input: " + e.getMessage());

        } catch (ArithmeticException e) {
            System.out.println("Transaction failed: " + e.getMessage());

        } finally {
            System.out.println("ATM transaction process completed.");
            sc.close();
        }
    }
}
