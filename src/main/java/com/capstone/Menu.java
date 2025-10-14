package com.capstone;

import java.util.Scanner;

public class Menu {
        static Scanner sc = new Scanner(System.in);

    static char mainMenu(){

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
        return choosenChar;
    }


    static char ledgerMenu(){

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
        return choosenChar;
    }

    static char reportsMenu(){
        char choosenChar = ' ';
        boolean isValid = false;

        while(!isValid){
            System.out.println("Choose the report type you want to be displayed.");
            System.out.println("1 - month to Date");
            System.out.println("2 - Previous Month");
            System.out.println("3 - Year to Year");
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
        return choosenChar;
    }
}
