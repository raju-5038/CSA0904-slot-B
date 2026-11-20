
import java.io.*;

public class Main {

    public static void main(String[] args) {

        String[] files = {"store1.txt", "store2.txt", "store3.txt"};

        int totalSales = 0;

        try (BufferedWriter writer =
                 new BufferedWriter(new FileWriter("summary.txt"))) {

            writer.write("DAILY SALES REPORT");
            writer.newLine();

            for (String fileName : files) {

                try (BufferedReader reader =
                         new BufferedReader(new FileReader(fileName))) {

                    String line;

                    while ((line = reader.readLine()) != null) {
                        int sales = Integer.parseInt(line);
                        totalSales += sales;
                    }

                    System.out.println(fileName + " processed successfully.");

                } catch (FileNotFoundException e) {
                    System.out.println("File missing: " + fileName);

                } catch (NumberFormatException e) {
                    System.out.println("Invalid sales data in: " + fileName);

                } catch (IOException e) {
                    System.out.println("Error reading: " + fileName);
                }
            }

            writer.write("Total Sales: " + totalSales);
            writer.newLine();

            System.out.println("Total Sales: " + totalSales);
            System.out.println("Report generated successfully.");

        } catch (IOException e) {
            System.out.println("Report writing error: " + e.getMessage());
        }
    }
}
