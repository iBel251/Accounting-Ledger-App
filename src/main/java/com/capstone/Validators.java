package com.capstone;

import java.util.Scanner;

public class Validators {

    public static String validateString(String prompt, Scanner sc){

        //Accepts user input and scanner object (to prevent initiating a scanner everytime we need it)
        //Prompts the user to input untill they provide valid non empty string value.

        boolean isValid = false;
        String validatedInput = " ";
        while(!isValid){
            System.out.print(prompt);
            String input = sc.nextLine();
            if(input.isBlank()){
                System.out.println("We can't accept empty value, Please try again.");
            }else{
                validatedInput = input;
                isValid = true;
            }
        }
        return validatedInput;
    }

    public static double validatePositiveDouble(String prompt, Scanner sc){

        //Accepts user input
        //Checks if it is a number or not, if user inserted wrong characters it will prompt them to enter again
        //After a valid input it checks for positive value to be used
        boolean isValid = false;
        double validatedInput = 0;

        while(!isValid){
            System.out.print(prompt);
            if(sc.hasNextDouble()){
                double amount = sc.nextDouble();
                sc.nextLine();
                if(amount <= 0){
                    System.out.println("Amount must be positive.");
                }else{
                    validatedInput = amount;
                    isValid = true;
                }
            }else{
                System.out.println("Only numbers are accepted.");
                sc.nextLine();
            }
        }
        return validatedInput;
    }
}
