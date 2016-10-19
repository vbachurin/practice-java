package com.multithreading;

import java.util.concurrent.Semaphore;

public class Connection {

    private static Connection instance = new Connection();
    private int connections;
    private Semaphore sem = new Semaphore(10);

    private Connection() {}

    public static Connection getInstance() {
        return instance;
    }

    public void connect() {
        try {
            sem.acquire();
            doConnect();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            sem.release();
        }
    }

    private void doConnect() throws InterruptedException {
        synchronized (this) {
            connections++;
            System.out.println("Current connections: " + connections);
        }
        Thread.sleep(2000);
        synchronized (this) {
            connections--;
        }
    }
}
