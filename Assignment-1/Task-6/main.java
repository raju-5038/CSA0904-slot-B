
class Vehicle {
    String vehicleNo;
    String vehicleType;

    Vehicle(String vehicleNo, String vehicleType) {
        this.vehicleNo = vehicleNo;
        this.vehicleType = vehicleType;
        System.out.println("Vehicle Constructor");
    }

    void display() {
        System.out.println("Vehicle Number: " + vehicleNo);
        System.out.println("Vehicle Type: " + vehicleType);
    }
}

class TwoWheeler extends Vehicle {
    TwoWheeler(String vehicleNo) {
        super(vehicleNo, "Two Wheeler");
        System.out.println("TwoWheeler Constructor");
    }
}

class Car extends Vehicle {
    Car(String vehicleNo) {
        super(vehicleNo, "Car");
        System.out.println("Car Constructor");
    }
}

class ElectricCar extends Car {
    ElectricCar(String vehicleNo) {
        super(vehicleNo);
        System.out.println("ElectricCar Constructor");
    }
}

public class Main {
    public static void main(String[] args) {

        System.out.println("Creating Electric Car:");

        ElectricCar e = new ElectricCar("TN01AB1234");

        System.out.println("------------------");
        e.display();

        System.out.println("------------------");

        TwoWheeler t = new TwoWheeler("TN02CD5678");
        t.display();
    }
}
