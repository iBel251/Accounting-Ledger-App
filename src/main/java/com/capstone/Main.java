package com.capstone;

import java.util.Scanner;

public class Main {
    public static void main(String[] args){

        Scanner sc = new Scanner(System.in);
        boolean isRunning = true;

        while (isRunning){
            System.out.println("Please choose the letter form the options below.");
            System.out.println("D - Add Deposit");
            System.out.println("P - Make Payment");
            System.out.println("L - Ledger");
            System.out.println("X - Exit");

            char input = Character.toLowerCase(sc.next().charAt(0));

            if(input != 'x' ){
                optionDisplay(input);
            }else{
                System.out.println("System Closing...");
                isRunning = false;
            }

        }
    }

    static void optionDisplay(char chooseOption){

        switch (chooseOption){
            case 'd':
                System.out.println("Deposit");
                break;
            case 'p':
                System.out.println("Make payment");
                break;
            case 'l':
                System.out.println("Ledger");
                break;
            default:
                System.out.println("Invalid input.");
        }
    }
}
