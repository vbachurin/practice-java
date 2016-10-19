package com.multithreading;

import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Runner {

    private Lock lock1 = new ReentrantLock();
    private Lock lock2 = new ReentrantLock();

    private static Account acc1 = new Account(10000);
    private static Account acc2 = new Account(10000);

    public void firstThread() {
        Random rand = new Random();
        for (int i = 0; i < 1000; i++) {
            acquireLocks(lock1, lock2);
            try {
                Account.transfer(acc1, acc2, rand.nextInt(100));
            } finally {
                lock1.unlock();
                lock2.unlock();
            }
        }
    }

    public void secondThread() {
        Random rand = new Random();
        for (int i = 0; i < 1000; i++) {
            acquireLocks(lock1, lock2);
            try {
                Account.transfer(acc2, acc1, rand.nextInt(100));
            } finally {
                lock1.unlock();
                lock2.unlock();
            }
        }
    }
    
    public void acquireLocks(Lock lock1, Lock lock2) {
        while(true) {
            boolean gotLock1 = true, gotLock2 = true;
            try {
                gotLock1 = lock1.tryLock();
                gotLock2 = lock2.tryLock();
            } finally {
                if (gotLock1 && gotLock2)
                    return;
                if (gotLock1)
                    lock1.unlock();
                if (gotLock2)
                    lock2.unlock();
            }
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void finished() {
        System.out.println(String.format(
                        "Total: %d; Account 1 balance: %d; Account 2 balance: %d",
                acc1.getBalance() + acc2.getBalance(), acc1.getBalance(), acc2.getBalance()));
    }

}
