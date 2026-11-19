
class ParkingTicket {
    static int ticketCounter = 1000;

    int ticketNo;
    String vehicleNo;

    ParkingTicket(String vehicleNo) {
        ticketCounter++;
        this.ticketNo = ticketCounter;
        this.vehicleNo = vehicleNo;
    }

    void display() {
        System.out.println("Ticket Number: " + ticketNo);
        System.out.println("Vehicle Number: " + vehicleNo);
    }

    static void showTotalTickets() {
        System.out.println("Total Tickets: " +
                           (ticketCounter - 1000));
    }
}

public class Main {
    public static void main(String[] args) {

        ParkingTicket t1 =
            new ParkingTicket("TN01AB1234");

        ParkingTicket t2 =
            new ParkingTicket("TN02CD5678");

        ParkingTicket t3 =
            new ParkingTicket("TN03EF9012");

        t1.display();
        t2.display();
        t3.display();

        ParkingTicket.showTotalTickets();
    }
}
