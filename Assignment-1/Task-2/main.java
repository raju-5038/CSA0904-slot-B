
public class ParkingData {
    public static void main(String[] args) {

        // Input values
        int floors = 2;
        int[] slotsPerFloor = {3, 2};

        String vehicleNo = "TN01AB1234";
        int entryHour = 10;
        int minutes = 90;

        // Create jagged array
        String[][] slots = new String[floors][];

        for (int i = 0; i < floors; i++) {
            slots[i] = new String[slotsPerFloor[i]];
        }

        // Calculate parking fee
        double rate = 20.0;
        double hours = Math.ceil(minutes / 60.0);
        double fee = hours * rate;

        // Allocate free slot
        boolean parked = false;

        for (int i = 0; i < floors; i++) {
            for (int j = 0; j < slots[i].length; j++) {

                if (slots[i][j] == null) {
                    slots[i][j] = vehicleNo;

                    System.out.println("Vehicle parked successfully");
                    System.out.println("Floor: " + (i + 1));
                    System.out.println("Slot: " + (j + 1));

                    parked = true;
                    break;
                }
            }

            if (parked) {
                break;
            }
        }

        if (!parked) {
            System.out.println("Parking is full");
        }

        System.out.println("Vehicle Number: " + vehicleNo);
        System.out.println("Entry Hour: " + entryHour);
        System.out.println("Duration: " + minutes + " minutes");
        System.out.println("Parking Fee: Rs. " + fee);
    }
}
