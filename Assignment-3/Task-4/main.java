
class ScannerStation extends Thread {

    private int stationNumber;
    private int scripts;

    ScannerStation(int stationNumber, int scripts) {
        this.stationNumber = stationNumber;
        this.scripts = scripts;
    }

    public void run() {
        try {
            System.out.println("Scanner " + stationNumber +
                               " started.");

            for (int i = 1; i <= scripts; i++) {
                System.out.println("Scanner " + stationNumber +
                                   " scanning script " + i);

                Thread.sleep(200);
            }

            System.out.println("Scanner " + stationNumber +
                               " completed " + scripts + " scripts.");

        } catch (InterruptedException e) {
            System.out.println("Scanner interrupted.");
            Thread.currentThread().interrupt();
        }
    }
}


class RunnableScanner implements Runnable {

    public void run() {
        System.out.println("Runnable scanner started.");

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        System.out.println("Runnable scanner completed.");
    }
}

public class Main {

    public static void main(String[] args) {

        ScannerStation s1 = new ScannerStation(1, 3);
        ScannerStation s2 = new ScannerStation(2, 3);
        ScannerStation s3 = new ScannerStation(3, 3);
        ScannerStation s4 = new ScannerStation(4, 3);


        s1.start();
        s2.start();
        s3.start();
        s4.start();

        System.out.println("Scanner 1 alive: " + s1.isAlive());

        try {
           
            s1.join();
            s2.join();
            s3.join();
            s4.join();

            System.out.println("All scanners completed.");

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        
        RunnableScanner task = new RunnableScanner();
        Thread t5 = new Thread(task);

        t5.start();

        try {
            t5.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        System.out.println("Main thread completed.");
    }
}
