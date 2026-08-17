package com.atm;

public class Account {

    private long accountNumber;
    private int pin;
    private long balance;

    public Account(long accountNumber, int pin) {
        this.accountNumber = accountNumber;
        this.pin = pin;
        this.balance = 0;
    }

    public long getAccountNumber() {
        return accountNumber;
    }

    public int getPin() {
        return pin;
    }

    public long getBalance() {
        return balance;
    }

    public void deposit(long amount) {
        balance += amount;
    }

    public boolean withdraw(long amount) {
        if (amount > balance) {
            return false;
        }

        balance -= amount;
        return true;
    }
}