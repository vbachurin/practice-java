package com.multithreading;


public class Account {
    
    private int balance;
    
    
    public Account(int balance) {
        this.balance = balance;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }
    
    public static void transfer(Account src, Account dst, int amount) {
        src.decreaseBalance(amount);
        dst.increaseBalance(amount);
    }

    private void increaseBalance(int amount) {
        balance += amount;
    }

    private void decreaseBalance(int amount) {
        balance -= amount;
    }

}
