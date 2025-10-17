package com.capstone;

import java.util.Scanner;
import static com.capstone.StringFormatter.*;

public class Menu {
        static Scanner sc = new Scanner(System.in);

    static void mainMenu(){

        char choosenChar = ' ';
        boolean isValid = false;

        while (!isValid){
            System.out.printf(YELLOW + "%n==================== " + BOLD + UNDERLINE + "Salescorp Main Menu" + RESET +
                    YELLOW + " ====================%n" + RESET);

            System.out.println("Welcome to your online seller ledger!");
            System.out.println("Manage your product sales, expenses, and business reports.\n");

            System.out.println("S - Add New Sale");
            System.out.println("E - Record Expense");
            System.out.println("L - View Ledger");
            System.out.println("X - Exit Application");

            System.out.print(BLUE + "Select an option to continue: " + RESET);

            String input = sc.nextLine().toLowerCase().trim();

            if(input.length() == 1 && "selx".contains(input)){
                isValid = true;
                choosenChar = input.charAt(0);
            }else{
                System.out.printf("%n"+RED+"Invalid input!"+RESET+" Please enter the character assigned to the menu.%n");
            }

        }
        switch (choosenChar){
            case 's':
                    Services.addTransaction(sc, "deposit");
                    mainMenu();
                break;
            case 'e':
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
            System.out.printf(YELLOW+"%n==================== "+UNDERLINE+"Salescorp Ledger Menu"+RESET+YELLOW+" ====================%n"+RESET);
            System.out.println("A - View All Records");
            System.out.println("S - View Sales Only");
            System.out.println("E - View Expenses Only");
            System.out.println("R - Reports & Summaries");
            System.out.println("H - Return to Home Menu");

            System.out.print(BLUE + "What would you like to view? " + RESET);
            String input = sc.nextLine().toLowerCase().trim();
            if(input.length() == 1 && "aserh".contains(input)){
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
            case 's':
                Services.showTransactions("Deposit");
                ledgerMenu();
                break;
            case 'e':
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
            System.out.printf(YELLOW+"%n====================  "+UNDERLINE+"Salescorp Reports"+RESET+YELLOW+" ====================%n"+RESET);

            System.out.println("1 - month to Date");
            System.out.println("2 - Previous Month");
            System.out.println("3 - Year to Date");
            System.out.println("4 - Previous Year");
            System.out.println("5 - Search by Vendor");
            System.out.println("6 - Search by Amount");
            System.out.println("7 - Search by Description");
            System.out.println("8 - Summary");
            System.out.println("0 - Back");

            System.out.print(BLUE + "Select a report option to continue: " + RESET);
            String input = sc.nextLine().toLowerCase().trim();

            if(input.length() == 1 && "012345678".contains(input)){
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
            case '8':
                Services.displaySummary();
                break;
            default:
                System.out.println("Invalid input.");
                break;
        }
    }
}
