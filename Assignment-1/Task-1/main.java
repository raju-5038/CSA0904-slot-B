import java.time.LocalDateTime;

public class Main {

    public static void main(String[] args) {

        Vehicle v = new Vehicle(
            "TN01AB1234",
            "CAR",
            LocalDateTime.now()
        );

        ParkingTicket t = new ParkingTicket(1001, v);

        System.out.println("Vehicle: " + t.vehicle.registration);
        System.out.println("Vehicle Type: " + t.vehicle.type);
        System.out.println("Ticket Number: " + t.ticketNo);
        System.out.println("Entry Time: " + t.vehicle.entryTime);
    }
}

class Vehicle {
    String registration;
    String type;
    LocalDateTime entryTime;

    Vehicle(String registration, String type, LocalDateTime entryTime) {
        this.registration = registration;
        this.type = type;
        this.entryTime = entryTime;
    }
}

class ParkingTicket {
    long ticketNo;
    Vehicle vehicle;

    ParkingTicket(long ticketNo, Vehicle vehicle) {
        this.ticketNo = ticketNo;
        this.vehicle = vehicle;
    }
}
