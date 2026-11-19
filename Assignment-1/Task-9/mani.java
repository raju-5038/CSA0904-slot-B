
interface Chargeable {
    double chargingFee(int kWh);
}

abstract class Vehicle {
    String regNo;

    Vehicle(String regNo) {
        this.regNo = regNo;
    }

    abstract double calculateFee(int hours);

    void display() {
        System.out.println("Vehicle Number: " + regNo);
    }
}

class TwoWheeler extends Vehicle {

    TwoWheeler(String regNo) {
        super(regNo);
    }

    @Override
    double calculateFee(int hours) {
        return hours * 10;
    }
}

class Car extends Vehicle {

    Car(String regNo) {
        super(regNo);
    }

    @Override
    double calculateFee(int hours) {
        return hours * 30;
    }
}

class ElectricCar extends Vehicle implements Chargeable {

    ElectricCar(String regNo) {
        super(regNo);
    }

    @Override
    double calculateFee(int hours) {
        return hours * 30;
    }

    @Override
    public double chargingFee(int kWh) {
        return kWh * 12;
    }
}

public class Main {
    public static void main(String[] args) {

        TwoWheeler bike = new TwoWheeler("TN01AB1234");
        Car car = new Car("TN02CD5678");
        ElectricCar ev = new ElectricCar("TN03EF9012");

        bike.display();
        System.out.println("Parking Fee: Rs " +
                           bike.calculateFee(2));

        car.display();
        System.out.println("Parking Fee: Rs " +
                           car.calculateFee(2));

        ev.display();
        System.out.println("Parking Fee: Rs " +
                           ev.calculateFee(2));
        System.out.println("Charging Fee: Rs " +
                           ev.chargingFee(10));
    }
}
