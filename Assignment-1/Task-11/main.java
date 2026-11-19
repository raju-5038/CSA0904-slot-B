class ParkingTicket {
    String regNo;
    String type;
    int slotNo;

    ParkingTicket(String regNo, String type, int slotNo) {
        this.regNo = regNo;
        this.type = type;
        this.slotNo = slotNo;
    }
}

public class Main {
    static String[] slots = new String[3];
    static ParkingTicket[] tickets = new ParkingTicket[3];
    static double totalCollection = 0;

    static int findTicket(String regNo) {
        for (int i = 0; i < tickets.length; i++) {
            if (tickets[i] != null &&
                tickets[i].regNo.equals(regNo)) {
                return i;
            }
        }
        return -1;
    }

    static void enterVehicle(String regNo, String type) {
        if (!type.equals("Bike") &&
            !type.equals("Car") &&
            !type.equals("EV")) {
            System.out.println("Invalid vehicle type: " + type);
            return;
        }

        if (findTicket(regNo) != -1) {
            System.out.println("Duplicate registration: " + regNo);
            return;
        }

        for (int i = 0; i < slots.length; i++) {
            if (slots[i] == null) {
                slots[i] = regNo;
                tickets[i] = new ParkingTicket(regNo, type, i + 1);
                System.out.println(regNo + " entered. Slot: " + (i + 1));
                return;
            }
        }

        System.out.println("Parking full. No free slot.");
    }

    static void exitVehicle(String regNo, int minutes) {
        int index = findTicket(regNo);

        if (index == -1) {
            System.out.println("Ticket not found: " + regNo);
            return;
        }

        if (minutes < 0) {
            System.out.println("Invalid parking duration");
            return;
        }

        ParkingTicket t = tickets[index];

        int hours = (minutes + 59) / 60;
        int rate = 0;

        if (t.type.equals("Bike")) {
            rate = 10;
        } else if (t.type.equals("Car")) {
            rate = 30;
        } else if (t.type.equals("EV")) {
            rate = 30;
        }

        double fee = hours * rate;
        totalCollection += fee;

        System.out.println(t.regNo + " exited. Fee: Rs " + fee);

        slots[index] = null;
        tickets[index] = null;
    }

    static boolean isValidSlot(int slotNo) {
        return slotNo >= 1 && slotNo <= slots.length;
    }

    public static void main(String[] args) {
        enterVehicle("TN01A1", "Bike");
        enterVehicle("TN02B2", "Car");

        
        enterVehicle("TN03C3", "EV");

        
        enterVehicle("TN04D4", "Car");       
        enterVehicle("TN01A1", "Bike");      
        enterVehicle("TN05E5", "Bus");       

        System.out.println("Slot 5 valid: " + isValidSlot(5));

        
        exitVehicle("TN01A1", 60);
        exitVehicle("TN02B2", 120);
        exitVehicle("TN03C3", 60);

        
        enterVehicle("TN04D4", "Bike");
        exitVehicle("TN04D4", 0);

        System.out.println("Total collection: Rs " + totalCollection);
    }
}
