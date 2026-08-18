package com.atm;

import java.util.ArrayList;

public class Account {

    private long accountNumber;
    private int pin;
    private long balance;
    private ArrayList<String> transactions;

    public Account(long accountNumber, int pin) {
        this.accountNumber = accountNumber;
        this.pin = pin;
        this.balance = 0;
        this.transactions = new ArrayList<>();
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

    public ArrayList<String> getTransactions() {
        return transactions;
    }

    public void deposit(long amount) {
        balance += amount;
        transactions.add("Deposited ₹" + amount);
    }

    public boolean withdraw(long amount) {
        if (amount > balance) {
            return false;
        }

        balance -= amount;
        transactions.add("Withdrawn ₹" + amount);
        return true;
    }
}