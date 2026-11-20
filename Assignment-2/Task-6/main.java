
import java.util.*;

class Patient {
    final String name;
    final int severity;
    final long arrival;

    Patient(String name, int severity, long arrival) {
        this.name = name;
        this.severity = severity;
        this.arrival = arrival;
    }

    public String toString() {
        return name + "(S" + severity + ")";
    }
}

public class Main {
    private long counter = 0;

    private PriorityQueue<Patient> queue =
        new PriorityQueue<>(
            Comparator.comparingInt((Patient p) -> p.severity)
                      .thenComparingLong(p -> p.arrival)
        );

    void admit(String name, int severity) {
        queue.add(new Patient(name, severity, counter++));
    }

    Patient next() {
        return queue.poll();
    }

    public static void main(String[] args) {
        Main er = new Main();

        er.admit("Ravi", 3);
        er.admit("Meena", 1);
        er.admit("John", 3);
        er.admit("Asha", 5);
        er.admit("Kumar", 1);
        er.admit("Lata", 2);

        System.out.println("Patient treatment order:");

        Patient p;
        while ((p = er.next()) != null) {
            System.out.print(p + " ");
        }
    }
}
