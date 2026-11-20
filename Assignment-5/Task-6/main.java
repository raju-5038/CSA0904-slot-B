
import java.io.*;
import java.nio.file.*;
import java.util.*;

class PassStore {
    private final Path file;

    PassStore(Path file) {
        this.file = file;
    }

    
    public void add(String passId, String regNo,
                    String name, int route, double amount)
                    throws IOException {

        try (BufferedWriter w = Files.newBufferedWriter(
                file, StandardOpenOption.CREATE,
                StandardOpenOption.APPEND)) {

            w.write(String.join(",", passId, regNo, name,
                    String.valueOf(route),
                    String.valueOf(amount)));
            w.newLine();
        }
    }

    
    public String[] find(String passId) throws IOException {

        try (BufferedReader br = Files.newBufferedReader(file)) {

            String line;

            while ((line = br.readLine()) != null) {

                String[] data = line.split(",", -1);

                if (data.length != 5) {
                    continue;
                }

                if (data[0].equals(passId)) {
                    return data;
                }
            }
        }

        return null;
    }
}

public class Main {

    public static void main(String[] args) throws IOException {

        Path f = Files.createTempFile("passes", ".csv");

        PassStore store = new PassStore(f);

        store.add("P1001", "192311001", "Arun", 12, 4500);

        Files.writeString(f, "P1002,DAMAGED LINE\n",
                StandardOpenOption.APPEND);

        store.add("P1003", "192311003", "Divya", 7, 3800);

        System.out.println(Arrays.toString(store.find("P1003")));

        System.out.println(Arrays.toString(store.find("P1002")));

        Files.delete(f);
    }
}
