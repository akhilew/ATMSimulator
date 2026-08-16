package com.atm;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

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
            System.out.println("Create Account selected.");
        } else if (choice == 3) {
            System.out.println("Thank you for using the ATM.");
        } else {
            System.out.println("Invalid option.");
        }

        sc.close();
    }
}