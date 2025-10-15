package com.capstone;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Services {

    public static void addDeposit(Scanner sc){
        System.out.println("Enter the transaction details.");

        String vendor = Validators.validateString("Vendor: ", sc);

        String description = Validators.validateString("Description: ", sc);

        double amount = Validators.validatePositiveDouble("Enter amount: ", sc);

        //Format date and time to string in order to prevent type mismatch when creating transaction object
        String formattedDate = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        String formattedTime = LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"));

        //create transaction object
        Transaction t = new Transaction(formattedDate, formattedTime, description, vendor, amount);

        //format the values to be stored on to the csv file then call csvWriter to store it.
        String transaction = String.join("|",t.getDate(),t.getTime(),t.getDescription(),t.getVendor(),t.getAmount()+"");
        csvWriter(transaction);

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
