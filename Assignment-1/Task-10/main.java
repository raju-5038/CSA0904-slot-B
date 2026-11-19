class Vehicle {
    private String regNo;
    private int duration;

    public Vehicle(String regNo, int duration) {
        setRegNo(regNo);
        setDuration(duration);
    }

    public void setRegNo(String regNo) {
        if (regNo != null && !regNo.isEmpty()) {
            this.regNo = regNo;
        } else {
            System.out.println("Invalid vehicle number");
        }
    }

    public String getRegNo() {
        return regNo;
    }

    public void setDuration(int duration) {
        if (duration >= 0) {
            this.duration = duration;
        } else {
            System.out.println("Invalid duration");
        }
    }

    public int getDuration() {
        return duration;
    }
}

public class Main {
    public static void main(String[] args) {
        Vehicle v = new Vehicle("TN01AB1234", 3);

        System.out.println("Vehicle Number: " + v.getRegNo());
        System.out.println("Duration: " + v.getDuration());

        v.setDuration(-2);
    }
}
