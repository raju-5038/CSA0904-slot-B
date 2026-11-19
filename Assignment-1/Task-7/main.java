
class Vehicle {
    private final String regNo;

    Vehicle(String regNo) {
        this.regNo = regNo;
    }

    public String getRegNo() {
        return regNo;
    }

    public double calculateFee() {
        return 50;
    }

    @Override
    public String toString() {
        return "Vehicle Number: " + regNo;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Vehicle) {
            Vehicle v = (Vehicle) obj;
            return regNo.equals(v.regNo);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return regNo.hashCode();
    }
}

class TwoWheeler extends Vehicle {

    TwoWheeler(String regNo) {
        super(regNo);
    }

    @Override
    public double calculateFee() {
        return 20;
    }
}

class Car extends Vehicle {

    Car(String regNo) {
        super(regNo);
    }

    @Override
    public double calculateFee() {
        return 50;
    }
}

public class Main {
    public static void main(String[] args) {

        final int parkingHours = 2;

        TwoWheeler bike = new TwoWheeler("TN01AB1234");
        Car car = new Car("TN02CD5678");

        System.out.println(bike);
        System.out.println("Bike Fee: Rs " +
                           bike.calculateFee() * parkingHours);

        System.out.println(car);
        System.out.println("Car Fee: Rs " +
                           car.calculateFee() * parkingHours);

        Vehicle v1 = new Car("TN02CD5678");
        Vehicle v2 = new Car("TN02CD5678");

        System.out.println("Vehicles Equal: " +
                           v1.equals(v2));
    }
}
