
import java.io.*;
import java.util.*;

class Resident implements Serializable {
    private static final long serialVersionUID = 1L;

    String name;
    String registerNumber;

    transient String password;
    transient Object connection;

    Resident(String name, String registerNumber,
             String password) {
        this.name = name;
        this.registerNumber = registerNumber;
        this.password = password;
    }
}

class Room implements Serializable {
    private static final long serialVersionUID = 1L;

    int roomNumber;
    ArrayList<Resident> residents = new ArrayList<>();

    Room(int roomNumber) {
        this.roomNumber = roomNumber;
    }
}

class Hostel implements Serializable {
    private static final long serialVersionUID = 1L;

    String hostelName;
    ArrayList<Room> rooms = new ArrayList<>();

    Hostel(String hostelName) {
        this.hostelName = hostelName;
    }
}

public class Main {
    public static void main(String[] args) {

        try {
            Hostel h = new Hostel("SIMATS Hostel");

            Room r = new Room(101);

            Resident s = new Resident(
                "Bharath", "192311001", "12345"
            );

            r.residents.add(s);
            h.rooms.add(r);

            
            ObjectOutputStream out =
                new ObjectOutputStream(
                    new FileOutputStream("hostel.dat"));

            out.writeObject(h);
            out.close();

            System.out.println("Hostel data saved successfully!");

            
            ObjectInputStream in =
                new ObjectInputStream(
                    new FileInputStream("hostel.dat"));

            Hostel saved = (Hostel) in.readObject();
            in.close();

            Resident student =
                saved.rooms.get(0).residents.get(0);

            System.out.println("Hostel: " + saved.hostelName);
            System.out.println("Room: " +
                saved.rooms.get(0).roomNumber);
            System.out.println("Resident: " + student.name);
            System.out.println("Register Number: " +
                student.registerNumber);
            System.out.println("Password: " + student.password);

        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
