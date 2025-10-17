package com.capstone;

import java.util.Scanner;

import static com.capstone.Menu.mainMenu;
import static com.capstone.StringFormatter.*;

public class Main {
    public static void main(String[] args){
        System.out.printf(CYAN + "%n╔════════════════════════════════════════════════════════════════════════╗%n");
        System.out.printf("║******************************* "+RED+BOLD+UNDERLINE+"WELCOME"+RESET+CYAN+" ********************************║%n");
        System.out.printf("║******************** \uD83D\uDCB0\uD83D\uDCB0"+RED+BOLD+UNDERLINE+"ACCOUNTING LEDGER APP"+RESET+CYAN+"\uD83D\uDCB0\uD83D\uDCB0 ********************║%n");
        System.out.printf("╚════════════════════════════════════════════════════════════════════════╝%n%n"+RESET);

        mainMenu();
    }

//═ ║ ╔ ╗ ╚ ╝
}
