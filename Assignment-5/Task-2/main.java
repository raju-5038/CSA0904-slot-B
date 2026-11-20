
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {

        String fileName = "attendance.txt";

        
        try (BufferedWriter bw = new BufferedWriter(
                new FileWriter(fileName))) {

            for (int i = 1; i <= 1000; i++) {
                bw.write("Student " + i);
                bw.newLine();
            }
        }

        int lines = 0;
        int data;

        
        try (BufferedInputStream bis =
                new BufferedInputStream(
                    new FileInputStream(fileName))) {

            while ((data = bis.read()) != -1) {
                if (data == '\n') {
                    lines++;
                }
            }
        }

        System.out.println("File created successfully!");
        System.out.println("Total lines: " + lines);
    }
}
