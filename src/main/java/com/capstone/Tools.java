package com.capstone;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import static com.capstone.Menu.sc;
import static com.capstone.StringFormatter.*;

public class Tools {
    public static String[] transactionSplitter(String line){

        return line.split("\\|");
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
            System.out.println(YELLOW+"✅ Transaction recorded successfully."+RESET);
            enterToContinue();
        }catch(IOException e){
            System.out.println("Error on file" + e.getMessage());
        }
    }

    public static void headerFormatter(){
        System.out.println("-----------------------------------------------------------------------");
        System.out.printf("\u001B[1m%-12s %-8s %-20s %-15s %10s%n",
                "Date", "Time", "Description", "Vendor", "Amount");
        System.out.println("-----------------------------------------------------------------------");
    }

    public static void enterToContinue() {
        System.out.printf("Press "+GREEN+BOLD+UNDERLINE+"Enter"+RESET+" to continue");
        sc.nextLine();

    }

}
