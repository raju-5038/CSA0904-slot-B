
import java.util.*;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        Map<String, List<String>> doctors = new HashMap<>();

        doctors.put("Cardiology",
            Arrays.asList("Dr. Ravi", "Dr. Kumar"));

        doctors.put("Neurology",
            Arrays.asList("Dr. Arun", "Dr. Priya"));

        doctors.put("Orthopedics",
            Arrays.asList("Dr. Suresh", "Dr. Anitha"));

        System.out.println("HOSPITAL APPOINTMENT DESK");

        System.out.print("Enter Patient ID: ");
        String patientId = sc.nextLine();

        if (patientId.trim().isEmpty()) {
            System.out.println("Error: Patient ID is required");
            sc.close();
            return;
        }

        System.out.println("\nSelect Department:");
        System.out.println("1. Cardiology");
        System.out.println("2. Neurology");
        System.out.println("3. Orthopedics");

        System.out.print("Enter choice: ");
        int choice = sc.nextInt();
        sc.nextLine();

        String department;

        switch (choice) {
            case 1:
                department = "Cardiology";
                break;
            case 2:
                department = "Neurology";
                break;
            case 3:
                department = "Orthopedics";
                break;
            default:
                System.out.println("Invalid Department");
                sc.close();
                return;
        }

        System.out.println("\nAvailable Doctors:");

        List<String> doctorList = doctors.get(department);

        for (int i = 0; i < doctorList.size(); i++) {
            System.out.println((i + 1) + ". " + doctorList.get(i));
        }

        System.out.print("Select Doctor: ");
        int doctorChoice = sc.nextInt();
        sc.nextLine();

        if (doctorChoice < 1 ||
            doctorChoice > doctorList.size()) {
            System.out.println("Invalid Doctor Selection");
            sc.close();
            return;
        }

        String doctor = doctorList.get(doctorChoice - 1);

        System.out.println("\nAvailable Time Slots:");
        System.out.println("1. 10:00 AM");
        System.out.println("2. 11:00 AM");
        System.out.println("3. 02:00 PM");

        System.out.print("Select Time Slot: ");
        int slotChoice = sc.nextInt();
        sc.nextLine();

        String slot;

        switch (slotChoice) {
            case 1:
                slot = "10:00 AM";
                break;
            case 2:
                slot = "11:00 AM";
                break;
            case 3:
                slot = "02:00 PM";
                break;
            default:
                System.out.println("Invalid Time Slot");
                sc.close();
                return;
        }

        System.out.println("\nConfirm Appointment? (yes/no)");
        String confirm = sc.nextLine();

        if (confirm.equalsIgnoreCase("yes")) {
            System.out.println("\nAPPOINTMENT BOOKED SUCCESSFULLY");
            System.out.println("Patient ID: " + patientId);
            System.out.println("Department: " + department);
            System.out.println("Doctor: " + doctor);
            System.out.println("Time Slot: " + slot);
        } else {
            System.out.println("Appointment Cancelled");
        }

        System.out.println("\nDo you want to close? (yes/no)");
        String close = sc.nextLine();

        if (close.equalsIgnoreCase("yes")) {
            System.out.println("Application Closed");
        } else {
            System.out.println("Application Continues");
        }

        sc.close();
    }
}
