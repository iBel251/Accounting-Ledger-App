package com.capstone;
import java.io.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import static com.capstone.Menu.sc;

public class Services {

    //Add transaction will handle both deposit and payment, the type will be passed to it as an argument.
    // Type (Deposit or Payment)
    public static void addTransaction(Scanner sc, String type){
        System.out.println("Enter the transaction details.");

        String vendor = Validators.validateString("Vendor: ", sc);

        String description = Validators.validateString("Description: ", sc);

        double amount = Validators.validatePositiveDouble("Enter amount: ", sc);

        //Format date and time to string in order to prevent type mismatch when creating transaction object
        String formattedDate = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        String formattedTime = LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"));

        //If user is recording payment then the amount will be saved as a negative amount on the ledger.
        if(type.equalsIgnoreCase("payment")){
            amount = -amount;
        }
        //create transaction object
        Transaction t = new Transaction(formattedDate, formattedTime, description, vendor, amount);

        //format the values to be stored on to the csv file then call csvWriter to store it.
        String transaction = String.join("|",t.getDate(),t.getTime(),t.getDescription(),t.getVendor(),t.getAmount()+"");
        Tools.csvWriter(transaction);

    }

    public static void showTransactions(String type){
        File ledger = new File("transactions.csv");


        try(BufferedReader reader = new BufferedReader(new FileReader(ledger))){
            String line;
            while((line = reader.readLine()) != null){

                String[] values = line.split("\\|");

                if(values[4].equalsIgnoreCase("amount")){
                    Tools.headerFormatter();
                    continue;
                }

                Transaction t = createTransactionObject(line);

                double amount = Double.parseDouble(values[4]);

                if(type.equalsIgnoreCase("payment")){
                    if(amount < 0){
                        t.printFormatter();
                    }
                }else if(type.equalsIgnoreCase("deposit")){
                    if(amount > 0){
                        t.printFormatter();
                    }
                }else{
                    t.printFormatter();
                }
            }
            System.out.println("-----------------------------------------------------------------------");

            Tools.enterToContinue();
        }catch (IOException e){
            System.out.println("Error!" + e.getMessage());
        }
    }

    public static void showReports(String type){
        File ledger = new File("transactions.csv");

        try (BufferedReader reader = new BufferedReader(new FileReader(ledger))) {
            String line;
            while((line = reader.readLine()) != null){
                String[] sections = line.split("\\|");

                //Print the first header line before applying any filters
                if(sections[4].equalsIgnoreCase("amount")){
                    Tools.headerFormatter();
                    continue;
                }

                // Create an object from the values of the transaction.
                Transaction t = createTransactionObject(line);

                //Parse the date section in to date format and extract the month and date in order to do the filtering.
                LocalDate transactionDate = LocalDate.parse(sections[0]);
                int transactionMonth = transactionDate.getMonthValue();
                int transactionYear = transactionDate.getYear();

                //Get the current date, year and month values
                LocalDate today = LocalDate.now();
                int currentMonth = today.getMonthValue();
                int currentYear = today.getYear();

                switch (type){
                    case "MonthToDate" :{
                        if(transactionMonth == currentMonth && transactionYear == currentYear){
                            t.printFormatter();
                        }
                        break;
                    }
                    case "PreviousMonth" :{
                        if(transactionMonth + 1 == currentMonth && transactionYear == currentYear){
                            t.printFormatter();
                        }
                        break;
                    }
                    case "YearToDate" :{
                        if(transactionYear == currentYear){
                            t.printFormatter();
                        }
                        break;
                    }
                    case "PreviousYear" :{
                        if(transactionYear + 1 == currentYear){
                            t.printFormatter();
                        }
                        break;
                    }
                }
            }
            System.out.println("-----------------------------------------------------------------------");

            Tools.enterToContinue();
            Menu.reportsMenu();

        }catch (IOException e){
            System.out.println("Error on file." + e.getMessage());
        }
    }

    public static Transaction createTransactionObject(String transaction){
        String[] sections = transaction.split("\\|");
        String date = sections[0];
        String time = sections[1];
        String description = sections[2];
        String vendor = sections[3];
        double amount = Double.parseDouble(sections[4]);

        return new Transaction(date, time, description, vendor, amount);
    }

    public static void customSearch(String searchBy, String input){
        File ledger = new File("transactions.csv");

        try(BufferedReader reader = new BufferedReader(new FileReader(ledger))){
            String line;
            while((line = reader.readLine()) != null){
                String[] sections = Tools.transactionSplitter(line);

                if(sections[4].equalsIgnoreCase("amount")){
                    Tools.headerFormatter();
                    continue;
                }

                Transaction t = createTransactionObject(line);

                switch (searchBy){
                    case "vendor":{
                        if(sections[3].toLowerCase().contains(input.toLowerCase())){
                            t.printFormatter();
                        }
                        break;
                    }
                    case "description":{
                        if(sections[2].toLowerCase().contains(input.toLowerCase())){
                            t.printFormatter();
                        }
                        break;
                    }
                    case "amount":{
                        double amount = Double.parseDouble(sections[4]);
                        if(Math.abs(amount) == Math.abs(Double.parseDouble(input))){
                            t.printFormatter();
                        }
                        break;
                    }
                    default:
                        System.out.println("No matching record found.");
                        break;
                }
            }
            System.out.println("-----------------------------------------------------------------------");

            Tools.enterToContinue();
            Menu.reportsMenu();
        }catch (IOException e){
            System.out.println("Error reading file." + e.getMessage());
        }
    }

}
