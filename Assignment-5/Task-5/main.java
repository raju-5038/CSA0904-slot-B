
import java.sql.*;

public class Main {

    public static void main(String[] args) {

        String url = "jdbc:mysql://localhost:3306/college";
        String user = "root";
        String password = "root";

        try (Connection con =
                DriverManager.getConnection(url, user, password)) {

            con.setAutoCommit(false);

            try {
               
                PreparedStatement ps1 = con.prepareStatement(
                    "UPDATE wallet SET balance = balance - 500 WHERE id = 1"
                );
                ps1.executeUpdate();


                PreparedStatement ps2 = con.prepareStatement(
                    "UPDATE wallet SET balance = balance + 500 WHERE id = 2"
                );
                ps2.executeUpdate();

                con.commit();

                System.out.println("Transfer successful!");

            } catch (SQLException e) {

                con.rollback();

                System.out.println("Transfer failed!");
                System.out.println("Transaction rolled back.");
            }

        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
        }
    }
}
