package com.capstone;
import java.io.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

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
        csvWriter(transaction);

    }

    public static void showTransactions(String type){
        File ledger = new File("transactions.csv");


        try(BufferedReader reader = new BufferedReader(new FileReader(ledger))){
            String line;
            while((line = reader.readLine()) != null){

                String[] values = line.split("\\|");

                if(values[4].equalsIgnoreCase("amount")){
                    System.out.println(line);
                    continue;
                }

                double amount = Double.parseDouble(values[4]);

                if(type.equalsIgnoreCase("payment")){
                    if(amount < 0){
                        System.out.println(line);
                    }
                }else if(type.equalsIgnoreCase("deposit")){
                    if(amount > 0){
                        System.out.println(line);
                    }
                }else{
                    System.out.println(line);
                }
            }
        }catch (IOException e){
            System.out.println("Error!" + e.getMessage());
        }
    }

    //Accepts the provided inputs in string format,
    // check if the file transactions.csv exist (create it with headers if not)
    // then append the transaction on new line.)
    public static void csvWriter(String transaction){
        File file = new File("transactions.csv");
        boolean fileExists = file.exists() && file.length() > 0;
        try{
            FileWriter writer = new FileWriter(file,true);
            if(!fileExists){
                writer.write("date|time|description|vendor|amount\n");
            }
            writer.write(transaction + "\n");
            writer.close();
        }catch(IOException e){
            System.out.println("Error on file" + e.getMessage());
        }
    }

}
