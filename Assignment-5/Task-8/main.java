
import java.sql.*;
import java.util.*;

class PassLoader {

    public static int loadAll(Connection con, List<String[]> passes)
            throws SQLException {

        String sql = "INSERT INTO bus_pass " +
                "(pass_id, reg_no, name, route_no, amount) " +
                "VALUES (?, ?, ?, ?, ?)";

        boolean oldAutoCommit = con.getAutoCommit();
        con.setAutoCommit(false);

        try (PreparedStatement ps = con.prepareStatement(sql)) {

            int n = 0;

            for (String[] p : passes) {

                
                ps.setString(1, p[0]);
                ps.setString(2, p[1]);
                ps.setString(3, p[2]);
                ps.setInt(4, Integer.parseInt(p[3]));
                ps.setDouble(5, Double.parseDouble(p[4]));

                ps.addBatch();
                n++;

                if (n % 1000 == 0) {
                    ps.executeBatch();
                    ps.clearBatch();
                }
            }

            ps.executeBatch();


            con.commit();
            return n;

        } catch (SQLException | RuntimeException e) {

            
            con.rollback();
            throw e;

        } finally {
            con.setAutoCommit(oldAutoCommit);
        }
    }
}

public class Main {

    public static void main(String[] args) {

        String url = "jdbc:mysql://localhost:3306/college";
        String user = "root";
        String password = "root";

        try (Connection con =
                DriverManager.getConnection(url, user, password)) {

            int[] sizes = {1000, 10000, 100000};

            for (int size : sizes) {

                
                try (Statement st = con.createStatement()) {
                    st.executeUpdate("DELETE FROM bus_pass");
                }

                List<String[]> passes = new ArrayList<>();

                
                for (int i = 1; i <= size; i++) {
                    passes.add(new String[]{
                        "P" + i,
                        "REG" + i,
                        "Student" + i,
                        "7",
                        "3500"
                    });
                }

                long start = System.nanoTime();

                int count = PassLoader.loadAll(con, passes);

                long end = System.nanoTime();

                System.out.println("Records: " + size);
                System.out.println("Inserted: " + count);
                System.out.println("Time: " +
                        ((end - start) / 1_000_000.0) + " ms");
                System.out.println("----------------------");
            }

            
            String query = "SELECT * FROM bus_pass";

            try (Statement st = con.createStatement();
                 ResultSet rs = st.executeQuery(query)) {

                ResultSetMetaData md = rs.getMetaData();

                System.out.println("Column Information:");

                for (int i = 1; i <= md.getColumnCount(); i++) {
                    System.out.println(
                        md.getColumnName(i) + " - " +
                        md.getColumnTypeName(i)
                    );
                }

                System.out.println("Sample Records:");

                int count = 0;
                while (rs.next() && count < 5) {
                    System.out.println(
                        rs.getString("pass_id") + " " +
                        rs.getString("reg_no") + " " +
                        rs.getString("name") + " " +
                        rs.getInt("route_no") + " " +
                        rs.getDouble("amount")
                    );
                    count++;
                }
            }

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
