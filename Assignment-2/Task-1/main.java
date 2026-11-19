
public class Main {

    static String buildUsingString(int tickets, int fields) {
        String result = "";

        for (int i = 0; i < tickets; i++) {
            String ticket = "";

            for (int j = 1; j <= fields; j++) {
                ticket = ticket + "Field" + j + " ";
            }

            result = ticket;
        }
        return result;
    }

    static String buildUsingStringBuilder(int tickets, int fields) {
        String result = "";

        for (int i = 0; i < tickets; i++) {
            StringBuilder ticket = new StringBuilder();

            for (int j = 1; j <= fields; j++) {
                ticket.append("Field").append(j).append(" ");
            }

            result = ticket.toString();
        }
        return result;
    }

    static String buildUsingStringBuffer(int tickets, int fields) {
        String result = "";

        for (int i = 0; i < tickets; i++) {
            StringBuffer ticket = new StringBuffer();

            for (int j = 1; j <= fields; j++) {
                ticket.append("Field").append(j).append(" ");
            }

            result = ticket.toString();
        }
        return result;
    }

    static void measure(String method, int tickets, int fields) {
        long totalTime = 0;
        String result = "";


        for (int i = 0; i < 2; i++) {
            buildUsingStringBuilder(100, 10);
        }

        
        for (int i = 0; i < 5; i++) {
            long start = System.nanoTime();

            if (method.equals("String")) {
                result = buildUsingString(tickets, fields);
            } else if (method.equals("StringBuilder")) {
                result = buildUsingStringBuilder(tickets, fields);
            } else {
                result = buildUsingStringBuffer(tickets, fields);
            }

            long end = System.nanoTime();
            totalTime += (end - start);
        }

        System.out.println("\nMethod: " + method);
        System.out.println("Tickets: " + tickets);
        System.out.println("Fields: " + fields);
        System.out.println("Average time: " + (totalTime / 5) + " ns");
        System.out.println("Last ticket: " + result);
    }

    public static void main(String[] args) {

        int[][] workloads = {
            {1, 5},
            {100, 20},
            {10000, 50}
        };

        for (int[] work : workloads) {
            System.out.println("\n========== NEW WORKLOAD ==========");

            measure("String", work[0], work[1]);
            measure("StringBuilder", work[0], work[1]);
            measure("StringBuffer", work[0], work[1]);
        }
    }
}
