
import java.io.*;
import java.util.*;

public class Main {

    static String fileName = "passes.txt";

    
    static void addPass(String id, String regNo, String name,
                        int route, String stop,
                        String validity, double amount) throws IOException {

        try (BufferedWriter bw = new BufferedWriter(
                new FileWriter(fileName, true))) {

            bw.write(id + "," + regNo + "," + name + "," +
                     route + "," + stop + "," + validity + "," + amount);

            bw.newLine();
        }

        System.out.println("Pass added successfully!");
    }

    
    static void findPass(String id) throws IOException {

        try (BufferedReader br = new BufferedReader(
                new FileReader(fileName))) {

            String line;

            while ((line = br.readLine()) != null) {
                String[] data = line.split(",", -1);

                if (data.length == 7 && data[0].equals(id)) {
                    System.out.println("Pass Found:");
                    System.out.println(Arrays.toString(data));
                    return;
                }
            }
        }

        System.out.println("Pass not found!");
    }


    static void listByRoute(int route) throws IOException {

        try (BufferedReader br = new BufferedReader(
                new FileReader(fileName))) {

            String line;
            boolean found = false;

            System.out.println("Passes for Route " + route + ":");

            while ((line = br.readLine()) != null) {
                String[] data = line.split(",", -1);

                if (data.length == 7 &&
                    data[3].equals(String.valueOf(route))) {

                    System.out.println(Arrays.toString(data));
                    found = true;
                }
            }

            if (!found) {
                System.out.println("No passes found!");
            }
        }
    }

    public static void main(String[] args) throws IOException {


        addPass("P101", "192311001", "Arun", 12,
                "Central", "2027-03-31", 4500);

        addPass("P102", "192311002", "Ravi", 7,
                "Market", "2027-03-31", 3800);

        addPass("P103", "192311003", "Divya", 12,
                "College", "2027-03-31", 4500);

        
        findPass("P102");


        listByRoute(12);
    }
}
