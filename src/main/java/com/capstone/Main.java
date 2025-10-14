package com.capstone;

import java.util.Scanner;

import static com.capstone.Menu.mainMenu;

public class Main {
    public static void main(String[] args){

        optionDisplay(mainMenu());
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
            case 'x':
                System.out.println("Bye bye");
                break;
            default:
                System.out.println("Invalid input.");
        }
    }
}
