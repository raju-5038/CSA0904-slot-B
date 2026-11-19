
class ParkingTicket {
    int ticketNo;
    String vehicleNo;
    int floor;
    int slot;
    String entryTime;
    ParkingTicket() {
        this(1001, "Not Assigned", 0, 0, "Not Available");
    }

    ParkingTicket(int ticketNo, String vehicleNo,
                  int floor, int slot, String entryTime) {
        this.ticketNo = ticketNo;
        this.vehicleNo = vehicleNo;
        this.floor = floor;
        this.slot = slot;
        this.entryTime = entryTime;
    }
    ParkingTicket(int ticketNo, String vehicleNo) {
        this(ticketNo, vehicleNo, 1, 1, "10:00 AM");
    }

    ParkingTicket(ParkingTicket oldTicket, int newTicketNo) {
        this(newTicketNo, oldTicket.vehicleNo,
             oldTicket.floor, oldTicket.slot,
             oldTicket.entryTime);
    }
    void displayTicket() {
        System.out.println("Ticket Number: " + ticketNo);
        System.out.println("Vehicle Number: " + vehicleNo);
        System.out.println("Floor: " + floor);
        System.out.println("Slot: " + slot);
        System.out.println("Entry Time: " + entryTime);
        System.out.println("--------------------");
    }
}
public class Main {
    public static void main(String[] args) {
        ParkingTicket t1 = new ParkingTicket();
        ParkingTicket t2 = new ParkingTicket(
            1002, "TN01AB1234", 1, 2, "10:30 AM");
        ParkingTicket t3 = new ParkingTicket(
            1003, "TN02CD5678");
        ParkingTicket t4 = new ParkingTicket(t2, 1004);
        t1.displayTicket();
        t2.displayTicket();
        t3.displayTicket();
        t4.displayTicket();
    }
}
