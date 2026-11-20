
public class Main {

    static long usingString(int tickets, int fields) {
        long total = 0;

        for (int i = 0; i < tickets; i++) {
            String ticket = "";

            for (int j = 1; j <= fields; j++) {
                ticket = ticket + "Field" + j + " ";
            }

            total += ticket.length();
        }
        return total;
    }

    static long usingBuilder(int tickets, int fields) {
        long total = 0;

        for (int i = 0; i < tickets; i++) {
            StringBuilder ticket = new StringBuilder();

            for (int j = 1; j <= fields; j++) {
                ticket.append("Field").append(j).append(" ");
            }

            total += ticket.length();
        }
        return total;
    }

    static long measure(boolean useBuilder, int tickets, int fields) {
        long start = System.nanoTime();

        if (useBuilder) {
            usingBuilder(tickets, fields);
        } else {
            usingString(tickets, fields);
        }

        return System.nanoTime() - start;
    }

    public static void main(String[] args) {

        int[] sizes = {1000, 5000, 10000};
        int fields = 50;
        int repetitions = 5;

        
        usingString(100, fields);
        usingBuilder(100, fields);

        for (int tickets : sizes) {

            long stringTotal = 0;
            long builderTotal = 0;

            for (int r = 0; r < repetitions; r++) {
                stringTotal += measure(false, tickets, fields);
                builderTotal += measure(true, tickets, fields);
            }

            System.out.println("\nTickets: " + tickets);
            System.out.println("Fields: " + fields);

            System.out.println("String average: "
                + (stringTotal / repetitions) + " ns");

            System.out.println("StringBuilder average: "
                + (builderTotal / repetitions) + " ns");
        }
    }
}
