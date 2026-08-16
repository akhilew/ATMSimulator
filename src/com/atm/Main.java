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
                System.out.println("Login selected.");
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
}