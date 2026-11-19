
interface VisitorPass {
    boolean isValid();
}

class OneDayPass implements VisitorPass {
    public boolean isValid() {
        return true;
    }
}

class Vehicle {
    String regNo;

    Vehicle(String regNo) {
        this.regNo = regNo;
    }

    double calculateFee(int hours, int entryHour) {
        return hours * 30;
    }
}

class Car extends Vehicle {
    Car(String regNo) {
        super(regNo);
    }
}

class Ebike extends Vehicle {
    Ebike(String regNo) {
        super(regNo);
    }

    double calculateFee(int hours, int entryHour) {
        return 20; // Flat fee
    }
}

class DeliveryVan extends Vehicle {
    DeliveryVan(String regNo) {
        super(regNo);
    }

    double calculateFee(int hours, int entryHour) {
        if (entryHour >= 9 && entryHour < 17) {
            return hours * 60; // Double car rate
        }
        return hours * 30;
    }
}

public class Main {

    static String[][] floors = {
        new String[2],
        new String[3],
        new String[2],
        new String[4],
        new String[3]
    };

    static void park(Vehicle v) {
        for (int i = 0; i < floors.length; i++) {
            for (int j = 0; j < floors[i].length; j++) {
                if (floors[i][j] == null) {
                    floors[i][j] = v.regNo;
                    System.out.println(v.regNo +
                        " parked at Floor " + (i + 1) +
                        ", Slot " + (j + 1));
                    return;
                }
            }
        }
        System.out.println("Parking Full");
    }

    public static void main(String[] args) {

        Vehicle car = new Car("TN01A1");
        Vehicle bike = new Ebike("TN02B2");
        Vehicle van = new DeliveryVan("TN03C3");

        park(car);
        park(bike);
        park(van);

        System.out.println("Car Fee: Rs " +
            car.calculateFee(2, 10));

        System.out.println("E-bike Fee: Rs " +
            bike.calculateFee(2, 10));

        System.out.println("Delivery Van Fee: Rs " +
            van.calculateFee(2, 10));

        OneDayPass pass = new OneDayPass();

        if (pass.isValid()) {
            System.out.println("Visitor One-Day Pass: Valid");
            System.out.println("Visitor Parking Fee: Rs 0.0");
        }
    }
}
