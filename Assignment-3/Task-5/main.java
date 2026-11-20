
public class Main {

    private final boolean[] booked;
    private int bookingsMade = 0;

    Main(int seats) {
        booked = new boolean[seats];
    }

    
    public synchronized void book(int seat) {

        if (!booked[seat]) {
            booked[seat] = true;
            bookingsMade++;

            System.out.println(
                "Seat " + (seat + 1) + " booked successfully."
            );

        } else {
            System.out.println(
                "Seat " + (seat + 1) + " is already booked."
            );
        }
    }

    public static void main(String[] args)
            throws InterruptedException {

        Main screen = new Main(10);

        Thread[] users = new Thread[100];


        for (int i = 0; i < users.length; i++) {

            final int seat = i % 10;

            users[i] = new Thread(() -> screen.book(seat));

            users[i].start();
        }

        
        for (Thread t : users) {
            t.join();
        }

        System.out.println("Seats sold: " + screen.bookingsMade);
    }
}
