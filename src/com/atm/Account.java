package com.atm;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Account {

    private long accountNumber;
    private String pin;
    private long balance;
    private ArrayList<String> transactions;

    private DateTimeFormatter formatter =
            DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");

    public Account(long accountNumber, String pin) {
        this.accountNumber = accountNumber;
        this.pin = pin;
        this.balance = 0;
        this.transactions = new ArrayList<>();
    }

    public long getAccountNumber() {
        return accountNumber;
    }

    public String getPin() {
        return pin;
    }

    public long getBalance() {
        return balance;
    }

    public ArrayList<String> getTransactions() {
        return transactions;
    }

    public void changePin(String newPin) {
        pin = newPin;
    }

    public void deposit(long amount) {
        balance += amount;

        String dateTime = LocalDateTime.now().format(formatter);

        transactions.add("Deposited ₹" + amount
                + " | Balance: ₹" + balance
                + " | " + dateTime);
    }

    public boolean withdraw(long amount) {
        if (amount > balance) {
            return false;
        }

        balance -= amount;

        String dateTime = LocalDateTime.now().format(formatter);

        transactions.add("Withdrawn ₹" + amount
                + " | Balance: ₹" + balance
                + " | " + dateTime);

        return true;
    }
}