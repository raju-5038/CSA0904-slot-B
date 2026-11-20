
import java.util.*;
import java.util.concurrent.*;
import java.io.*;

public class Main {

    static final int TOTAL_FILES = 10000;


    static class ResultTask implements Callable<Boolean> {

        int fileId;

        ConcurrentHashMap<Integer, Integer> results;
        ConcurrentLinkedQueue<String> failures;

        ResultTask(int id,
                   ConcurrentHashMap<Integer, Integer> results,
                   ConcurrentLinkedQueue<String> failures) {

            this.fileId = id;
            this.results = results;
            this.failures = failures;
        }

        public Boolean call() {

            try {
                
                if (fileId % 100 == 0) {
                    throw new FileNotFoundException(
                        "File is missing"
                    );
                }

                
                if (fileId % 100 == 1) {
                    throw new IOException(
                        "File is corrupted"
                    );
                }

                
                int score = fileId % 101;

                results.put(fileId, score);

                return true;

            } catch (Exception e) {

                failures.add(
                    "File " + fileId + ": " + e.getMessage()
                );

                return false;
            }
        }
    }

    
    static void processFiles(int poolSize)
            throws InterruptedException, ExecutionException {

        ConcurrentHashMap<Integer, Integer> results =
            new ConcurrentHashMap<>();

        ConcurrentLinkedQueue<String> failures =
            new ConcurrentLinkedQueue<>();

        ExecutorService executor =
            Executors.newFixedThreadPool(poolSize);

        List<Future<Boolean>> futures = new ArrayList<>();

        long startTime = System.nanoTime();

        try {

            for (int i = 1; i <= TOTAL_FILES; i++) {

                ResultTask task =
                    new ResultTask(i, results, failures);

                Future<Boolean> future = executor.submit(task);

                futures.add(future);
            }

            executor.shutdown();

            int successCount = 0;
            int failureCount = 0;

            
            for (Future<Boolean> future : futures) {

                if (future.get()) {
                    successCount++;
                } else {
                    failureCount++;
                }
            }

            long endTime = System.nanoTime();

            double timeMs = (endTime - startTime) / 1000000.0;

            System.out.println("\nThread Pool Size: " + poolSize);
            System.out.println("Total Files: " + TOTAL_FILES);
            System.out.println("Successful Files: " + successCount);
            System.out.println("Failed Files: " + failureCount);
            System.out.printf("Processing Time: %.2f ms%n", timeMs);

            System.out.println("Sample failures:");

            int count = 0;

            for (String error : failures) {
                if (count == 3) {
                    break;
                }

                System.out.println(error);
                count++;
            }

        } finally {
            if (!executor.isTerminated()) {
                executor.shutdownNow();
            }
        }
    }

    public static void main(String[] args)
            throws InterruptedException, ExecutionException {

        int[] poolSizes = {1, 2, 4, 8};

        for (int size : poolSizes) {
            processFiles(size);
        }

        System.out.println("\nAll processing completed.");
    }
}
