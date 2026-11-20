
import java.util.*;

public class Main {

    static void testArrayList(int size) {
        ArrayList<Integer> list = new ArrayList<>();

        long start = System.nanoTime();

        for (int i = 0; i < size; i++) {
            list.add(i);
        }

        long addTime = System.nanoTime() - start;

        start = System.nanoTime();
        list.add(0, 999);
        long insertTime = System.nanoTime() - start;

        start = System.nanoTime();
        list.remove(0);
        long removeTime = System.nanoTime() - start;

        start = System.nanoTime();
        int value = list.get(size / 2);
        long accessTime = System.nanoTime() - start;

        start = System.nanoTime();
        for (int song : list) {
            value = song;
        }
        long iterateTime = System.nanoTime() - start;

        System.out.println("\nArrayList Results");
        System.out.println("Add: " + addTime + " ns");
        System.out.println("Insert at front: " + insertTime + " ns");
        System.out.println("Remove at front: " + removeTime + " ns");
        System.out.println("Get middle: " + accessTime + " ns");
        System.out.println("Iteration: " + iterateTime + " ns");
    }

    static void testLinkedList(int size) {
        LinkedList<Integer> list = new LinkedList<>();

        long start = System.nanoTime();

        for (int i = 0; i < size; i++) {
            list.add(i);
        }

        long addTime = System.nanoTime() - start;

        start = System.nanoTime();
        list.add(0, 999);
        long insertTime = System.nanoTime() - start;

        start = System.nanoTime();
        list.remove(0);
        long removeTime = System.nanoTime() - start;

        start = System.nanoTime();
        int value = list.get(size / 2);
        long accessTime = System.nanoTime() - start;

        start = System.nanoTime();
        for (int song : list) {
            value = song;
        }
        long iterateTime = System.nanoTime() - start;

        System.out.println("\nLinkedList Results");
        System.out.println("Add: " + addTime + " ns");
        System.out.println("Insert at front: " + insertTime + " ns");
        System.out.println("Remove at front: " + removeTime + " ns");
        System.out.println("Get middle: " + accessTime + " ns");
        System.out.println("Iteration: " + iterateTime + " ns");
    }

    static void testArrayDeque(int size) {
        ArrayDeque<Integer> deque = new ArrayDeque<>();

        long start = System.nanoTime();

        for (int i = 0; i < size; i++) {
            deque.addLast(i);
        }

        long addTime = System.nanoTime() - start;

        start = System.nanoTime();
        deque.addFirst(999);
        long insertTime = System.nanoTime() - start;

        start = System.nanoTime();
        deque.removeFirst();
        long removeTime = System.nanoTime() - start;

        start = System.nanoTime();
        int value = deque.peekFirst();
        long accessTime = System.nanoTime() - start;

        start = System.nanoTime();
        for (int song : deque) {
            value = song;
        }
        long iterateTime = System.nanoTime() - start;

        System.out.println("\nArrayDeque Results");
        System.out.println("Add at end: " + addTime + " ns");
        System.out.println("Add at front: " + insertTime + " ns");
        System.out.println("Remove at front: " + removeTime + " ns");
        System.out.println("Peek first: " + accessTime + " ns");
        System.out.println("Iteration: " + iterateTime + " ns");
    }

    public static void main(String[] args) {

        int[] sizes = {1000, 5000, 10000};

        for (int size : sizes) {
            System.out.println("\n==============================");
            System.out.println("PLAYLIST SIZE: " + size);

            testArrayList(size);
            testLinkedList(size);
            testArrayDeque(size);
        }
    }
}
