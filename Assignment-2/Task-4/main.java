
import java.util.*;

public class Main {

    static void testHashMap(int size) {

        HashMap<String, Integer> map = new HashMap<>();

        long start = System.nanoTime();

        for (int i = 1; i <= size; i++) {
            map.put("MED" + i, i * 10);
        }

        long insertTime = System.nanoTime() - start;

        start = System.nanoTime();

        for (int i = 1; i <= 100000; i++) {
            map.get("MED" + ((i % size) + 1));
        }

        long lookupTime = System.nanoTime() - start;

        start = System.nanoTime();

        for (String code : map.keySet()) {
            System.out.print("");
        }

        long reportTime = System.nanoTime() - start;

        System.out.println("\nHashMap Results");
        System.out.println("Insertion: " + insertTime + " ns");
        System.out.println("100000 Lookups: " + lookupTime + " ns");
        System.out.println("Report: " + reportTime + " ns");
        System.out.println("First entries: " + map.entrySet().stream()
                .limit(5).toList());
    }

    static void testTreeMap(int size) {

        TreeMap<String, Integer> map = new TreeMap<>();

        long start = System.nanoTime();

        for (int i = 1; i <= size; i++) {
            map.put("MED" + i, i * 10);
        }

        long insertTime = System.nanoTime() - start;

        start = System.nanoTime();

        for (int i = 1; i <= 100000; i++) {
            map.get("MED" + ((i % size) + 1));
        }

        long lookupTime = System.nanoTime() - start;

        start = System.nanoTime();

        for (String code : map.keySet()) {
            System.out.print("");
        }

        long reportTime = System.nanoTime() - start;

        System.out.println("\nTreeMap Results");
        System.out.println("Insertion: " + insertTime + " ns");
        System.out.println("100000 Lookups: " + lookupTime + " ns");
        System.out.println("Sorted Report: " + reportTime + " ns");
        System.out.println("First entries: " + map.entrySet().stream()
                .limit(5).toList());
    }

    static void testLinkedHashMap(int size) {

        LinkedHashMap<String, Integer> map = new LinkedHashMap<>();

        long start = System.nanoTime();

        for (int i = 1; i <= size; i++) {
            map.put("MED" + i, i * 10);
        }

        long insertTime = System.nanoTime() - start;

        start = System.nanoTime();

        for (int i = 1; i <= 100000; i++) {
            map.get("MED" + ((i % size) + 1));
        }

        long lookupTime = System.nanoTime() - start;

        start = System.nanoTime();

        for (String code : map.keySet()) {
            System.out.print("");
        }

        long reportTime = System.nanoTime() - start;

        System.out.println("\nLinkedHashMap Results");
        System.out.println("Insertion: " + insertTime + " ns");
        System.out.println("100000 Lookups: " + lookupTime + " ns");
        System.out.println("Insertion Order Report: " + reportTime + " ns");
        System.out.println("First entries: " + map.entrySet().stream()
                .limit(5).toList());
    }

    public static void main(String[] args) {

        int[] sizes = {100, 1000, 5000};

        for (int size : sizes) {

            System.out.println("\n==========================");
            System.out.println("MEDICINE COUNT: " + size);

            testHashMap(size);
            testTreeMap(size);
            testLinkedHashMap(size);
        }
    }
}
