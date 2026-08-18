package com.atm;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Main {

    static Scanner sc = new Scanner(System.in);
    static Random random = new Random();

    static ArrayList<Account> accounts = new ArrayList<>();
    static long nextAccountNumber = 100001;

    public static void main(String[] args) {

        while (true) {

            System.out.println("========== ATM SIMULATOR ==========");
            System.out.println();
            System.out.println("1. Login");
            System.out.println("2. Create Account");
            System.out.println("3. Exit");
            System.out.println();
            System.out.print("Choose option: ");

            int choice = sc.nextInt();

            System.out.println();

            if (choice == 1) {
                login();
            } else if (choice == 2) {
                createAccount();
            } else if (choice == 3) {
                System.out.println("Thank you for using the ATM.");
                break;
            } else {
                System.out.println("Invalid option.");
            }

            System.out.println();
        }

        sc.close();
    }

    static void createAccount() {

        int pin = generatePin();

        Account account = new Account(nextAccountNumber, pin);
        accounts.add(account);

        System.out.println("Account created successfully!");
        System.out.println();
        System.out.println("Account Number: " + account.getAccountNumber());
        System.out.println("PIN: " + account.getPin());
        System.out.println("Balance: ₹" + account.getBalance());

        nextAccountNumber++;
    }

    static int generatePin() {

        int pin;

        do {
            pin = 1000 + random.nextInt(9000);
        } while (pinExists(pin));

        return pin;
    }

    static boolean pinExists(int pin) {

        for (Account account : accounts) {
            if (account.getPin() == pin) {
                return true;
            }
        }

        return false;
    }

    static void login() {

        System.out.print("Enter Account Number: ");
        long accountNumber = sc.nextLong();

        Account account = findAccount(accountNumber);

        if (account == null) {
            System.out.println("Account not found.");
            return;
        }

        for (int attempts = 1; attempts <= 3; attempts++) {

            System.out.print("Enter PIN: ");
            int pin = sc.nextInt();

            if (pin == account.getPin()) {
                System.out.println("Login successful!");
                atmMenu(account);
                return;
            }

            System.out.println("Incorrect PIN.");

            if (attempts < 3) {
                System.out.println("Attempts remaining: " + (3 - attempts));
            }
        }

        System.out.println("Too many incorrect attempts.");
    }

    static Account findAccount(long accountNumber) {

        for (Account account : accounts) {
            if (account.getAccountNumber() == accountNumber) {
                return account;
            }
        }

        return null;
    }

    static void atmMenu(Account account) {

        while (true) {

            System.out.println();
            System.out.println("========== ATM MENU ==========");
            System.out.println();
            System.out.println("1. Check Balance");
            System.out.println("2. Deposit Money");
            System.out.println("3. Withdraw Money");
            System.out.println("4. Transaction History");
            System.out.println("5. Change PIN");
            System.out.println("6. Logout");
            System.out.println();
            System.out.print("Choose option: ");

            int choice = sc.nextInt();

            if (choice == 1) {

                System.out.println("Current Balance: ₹" + account.getBalance());

            } else if (choice == 2) {

                depositMoney(account);

            } else if (choice == 3) {

                withdrawMoney(account);

            } else if (choice == 4) {

                showTransactionHistory(account);

            } else if (choice == 5) {

                changePin(account);

            } else if (choice == 6) {

                System.out.println("Logged out successfully.");
                return;

            } else {

                System.out.println("This option is not available yet.");
            }
        }
    }

    static void depositMoney(Account account) {

        System.out.print("Enter deposit amount: ₹");
        long amount = sc.nextLong();

        if (amount <= 0) {
            System.out.println("Amount must be greater than ₹0.");
            return;
        }

        if (amount > 100000) {
            System.out.println("Maximum deposit is ₹100000 per transaction.");
            return;
        }

        account.deposit(amount);

        System.out.println("₹" + amount + " deposited successfully.");
        System.out.println("Current Balance: ₹" + account.getBalance());
    }

    static void withdrawMoney(Account account) {

        System.out.print("Enter withdrawal amount: ₹");
        long amount = sc.nextLong();

        if (amount <= 0) {
            System.out.println("Amount must be greater than ₹0.");
            return;
        }

        if (amount > 100000) {
            System.out.println("Maximum withdrawal is ₹100000 per transaction.");
            return;
        }

        if (!account.withdraw(amount)) {
            System.out.println("Insufficient balance.");
            return;
        }

        System.out.println("₹" + amount + " withdrawn successfully.");
        System.out.println("Current Balance: ₹" + account.getBalance());
    }

    static void showTransactionHistory(Account account) {

        System.out.println();
        System.out.println("========== TRANSACTION HISTORY ==========");

        if (account.getTransactions().isEmpty()) {
            System.out.println("No transactions found.");
            return;
        }

        for (int i = 0; i < account.getTransactions().size(); i++) {
            System.out.println((i + 1) + ". " + account.getTransactions().get(i));
        }
    }

    static void changePin(Account account) {

        System.out.print("Enter current PIN: ");
        int currentPin = sc.nextInt();

        if (currentPin != account.getPin()) {
            System.out.println("Incorrect current PIN.");
            return;
        }

        System.out.print("Enter new PIN: ");
        int newPin = sc.nextInt();

        if (newPin < 1000 || newPin > 9999) {
            System.out.println("PIN must be exactly 4 digits.");
            return;
        }

        if (newPin == currentPin) {
            System.out.println("New PIN must be different from current PIN.");
            return;
        }

        System.out.print("Confirm new PIN: ");
        int confirmPin = sc.nextInt();

        if (newPin != confirmPin) {
            System.out.println("PIN confirmation does not match.");
            return;
        }

        account.changePin(newPin);

        System.out.println("PIN changed successfully.");
    }
}