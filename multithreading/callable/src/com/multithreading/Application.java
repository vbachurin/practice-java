package com.multithreading;

import java.io.IOException;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Application {

    public static void main(String[] args) {
        ExecutorService executor = Executors.newCachedThreadPool();

        Callable<Integer> callableObj = () -> {
            Random rand = new Random();
            int duration = rand.nextInt(4000);

            if (duration > 2000) {
                throw new IOException("Sleeping for too long.");
            }

            System.out.println("Starting  ...");
            Thread.sleep(duration);
            System.out.println("Finished");

            return duration;
        };

        Future<Integer> future = executor.submit(callableObj);
        executor.shutdown();

        try {
            System.out.println("Duration: " + future.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            IOException ex = (IOException) e.getCause();
            System.out.println(ex.getMessage());
        }
    }
}
