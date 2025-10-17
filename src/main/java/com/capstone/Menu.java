package com.capstone;

import java.util.Scanner;
import static com.capstone.StringFormatter.*;

public class Menu {
        static Scanner sc = new Scanner(System.in);

    static void mainMenu(){

        char choosenChar = ' ';
        boolean isValid = false;

        while (!isValid){
            System.out.printf(YELLOW+"%n################### "+UNDERLINE+"Main Menu"+RESET+YELLOW+" ###################%n"+RESET);

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
                System.out.printf("%n"+RED+"Invalid input!"+RESET+" Please enter D, P, L, or X.%n");
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
            System.out.printf(YELLOW+"%n################### "+UNDERLINE+"Ledger Menu"+RESET+YELLOW+" ###################%n"+RESET);
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
                System.out.printf("%n"+RED+"Invalid input!"+RESET+" Please enter the character assigned to the menu.");
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
            System.out.printf(YELLOW+"%n################### "+UNDERLINE+"Reports"+RESET+YELLOW+" ###################%n"+RESET);

            System.out.println("Choose the report type you want to be displayed.");
            System.out.println("1 - month to Date");
            System.out.println("2 - Previous Month");
            System.out.println("3 - Year to Date");
            System.out.println("4 - Previous Year");
            System.out.println("5 - Search by Vendor");
            System.out.println("6 - Search by Amount");
            System.out.println("7 - Search by Description");
            System.out.println("0 - Back");

            String input = sc.nextLine().toLowerCase().trim();

            if(input.length() == 1 && "01234567".contains(input)){
                isValid = true;
                choosenChar = input.charAt(0);
            }else{
                System.out.println(RED+"Invalid input!"+RESET+" Please enter the character assigned to the menu.");
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
                String vendor = sc.nextLine();

                Services.customSearch("vendor", vendor);
                break;
            case '6':

                //We will use the validatePositiveDouble method to accept input from user and handle possible errors
                double amount = Validators.validatePositiveDouble("Enter amount", sc);

                Services.customSearch("amount", Double.toString(amount));
                break;
            case '7':
                System.out.print("Enter Description: ");
                String description = sc.nextLine();

                Services.customSearch("description", description);
                break;
//            case '8':
//                System.out.print("Enter vendor name: ");
//                String input = sc.nextLine();
//
//                Services.customSearch("vendor", input);
//                break;
            default:
                System.out.println("Invalid input.");
                break;
        }
    }
}
