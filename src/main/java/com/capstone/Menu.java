package com.capstone;

import java.util.Scanner;

public class Menu {
        static Scanner sc = new Scanner(System.in);

    static void mainMenu(){

        char choosenChar = ' ';
        boolean isValid = false;

        while (!isValid){
            System.out.println("Please choose the letter form the options below.");
            System.out.println("D - Add Deposit");
            System.out.println("P - Make Payment");
            System.out.println("L - Ledger");
            System.out.println("X - Exit");

            String input = sc.nextLine().toLowerCase().trim();

            if(input.length() == 1 && "dplx".contains(input)){
                isValid = true;
                choosenChar = input.charAt(0);
            }else{
                System.out.println("Invalid input! Please enter D, P, L, or X.");
            }

        }
        switch (choosenChar){
            case 'd':
                    Services.addTransaction(sc, "deposit");
                    mainMenu();
                break;
            case 'p':
                    Services.addTransaction(sc, "payment");
                    mainMenu();
                break;
            case 'l':
                ledgerMenu();
                break;
            case 'x':
                System.out.println("Bye bye");
                break;
            default:
                System.out.println("Invalid input.");
                mainMenu();
                break;
        }
    }


    static void ledgerMenu(){

        char choosenChar = ' ';
        boolean isValid = false;

        while(!isValid){
            System.out.println("Ledger Menu");
            System.out.println("A - All");
            System.out.println("D - Deposits");
            System.out.println("P - Payments");
            System.out.println("R - Reports");
            System.out.println("H - Home");

            String input = sc.nextLine().toLowerCase().trim();
            if(input.length() == 1 && "adprh".contains(input)){
                isValid = true;
                choosenChar = input.charAt(0);
            }else{
                System.out.println("Invalid input, Please enter the character assigned to the menu.");
            }
        }
        switch (choosenChar){
            case 'a':
                Services.showTransactions("All");
                ledgerMenu();
                break;
            case 'd':
                Services.showTransactions("Deposit");
                ledgerMenu();
                break;
            case 'p':
                Services.showTransactions("Payment");
                ledgerMenu();
                break;
            case 'r':
                reportsMenu();
            case 'h':
                mainMenu();
                break;
            default:
                System.out.println("Invalid input.");
                ledgerMenu();
                break;
        }
    }

    static void reportsMenu(){
        char choosenChar = ' ';
        boolean isValid = false;

        while(!isValid){
            System.out.println("Choose the report type you want to be displayed.");
            System.out.println("1 - month to Date");
            System.out.println("2 - Previous Month");
            System.out.println("3 - Year to Date");
            System.out.println("4 - Previous Year");
            System.out.println("5 - Search by Vendor");
            System.out.println("0 - Back");

            String input = sc.nextLine().toLowerCase().trim();

            if(input.length() == 1 && "012345".contains(input)){
                isValid = true;
                choosenChar = input.charAt(0);
            }else{
                System.out.println("Invalid input, Please enter the character assigned to the menu.");
            }
        }
        switch (choosenChar){

            case '0':
                System.out.println("0");
                ledgerMenu();
                break;
            case '1':
                Services.showReports("MonthToDate");
                break;
            case '2':
                Services.showReports("PreviousMonth");
                break;
            case '3':
                Services.showReports("YearToDate");
                break;
            case '4':
                Services.showReports("PreviousYear");
                break;
            case '5':
                System.out.print("Enter vendor name: ");
                String input = sc.nextLine();

                Services.customSearch("vendor", input);
                break;
            default:
                System.out.println("Invalid input.");
                break;
        }
    }
}
