
import java.util.Scanner;

class FeeService {
    public String payFee(String regNo, double amount) {
        if (regNo.isEmpty())
            return "Enter Register Number";

        if (amount <= 0)
            return "Invalid Amount";

        return "Payment Successful!\nRegister No: "
                + regNo + "\nAmount: Rs." + amount;
    }
}

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        FeeService service = new FeeService();

        System.out.print("Enter Register Number: ");
        String regNo = sc.nextLine();

        System.out.print("Enter Amount: ");
        double amount = sc.nextDouble();

        System.out.println(service.payFee(regNo, amount));

        sc.close();
    }
}
