
import java.sql.*;
import java.io.*;

public class Main {

    static String url = "jdbc:mysql://localhost:3306/college";
    static String user = "root";
    static String password = "root";


    static void unsafeLogin(Connection con) throws Exception {

        String id = "' OR '1'='1' -- ";
        String pwd = "wrong";

        String sql = "SELECT * FROM users WHERE id='" +
                     id + "' AND pwd='" + pwd + "'";

        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(sql);

        if (rs.next())
            System.out.println("Unsafe Login: Accepted");
        else
            System.out.println("Unsafe Login: Rejected");
    }


    static void safeLogin(Connection con) throws Exception {

        String id = "' OR '1'='1' -- ";
        String pwd = "wrong";

        String sql =
            "SELECT * FROM users WHERE id=? AND pwd=?";

        PreparedStatement ps = con.prepareStatement(sql);

        ps.setString(1, id);
        ps.setString(2, pwd);

        ResultSet rs = ps.executeQuery();

        if (rs.next())
            System.out.println("Safe Login: Accepted");
        else
            System.out.println("Safe Login: Rejected");
    }


    static void testFile() throws Exception {

        FileWriter fw = new FileWriter("passes.txt");

        fw.write("P1001,192311001,Arun,12,4500\n");
        fw.write("P1002,Damaged\n");
        fw.write("P1003,192311003,Divya,7,ABC\n");
        fw.write("\n");

        fw.close();

        BufferedReader br =
            new BufferedReader(new FileReader("passes.txt"));

        String line;

        System.out.println("\nDamaged File Test:");

        while ((line = br.readLine()) != null) {

            String[] data = line.split(",", -1);

            try {
                if (data.length != 5)
                    throw new Exception("Wrong number of fields");

                Double.parseDouble(data[4]);

                System.out.println("Valid record: " + data[0]);

            } catch (Exception e) {
                System.out.println("Invalid record: " + line);
            }
        }

        br.close();
    }

    public static void main(String[] args) {

        try (Connection con =
             DriverManager.getConnection(url, user, password)) {

            unsafeLogin(con);
            safeLogin(con);

            testFile();

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
