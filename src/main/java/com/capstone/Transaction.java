package com.capstone;

public class Transaction {
    private String date, time, description, vendor;
    private double amount;

    public Transaction(String date, String time, String description, String vendor, double amount){
        this.date = date;
        this.time = time;
        this.description = description;
        this.vendor = vendor;
        this.amount = amount;
    }

    public double getAmount() {
        return amount;
    }

    public String getVendor() {
        return vendor;
    }

    public String getDescription() {
        return description;
    }

    public String getTime() {
        return time;
    }

    public String getDate() {
        return date;
    }

    public void printFormatter(){
        System.out.printf("%-12s \u001B[33m%-8s\u001B[0m %-20s %-15s", date, time, description, vendor);
        if (amount < 0) {
            //make amount red if it is negative(payment)
            System.out.printf("\u001B[31m%10.2f\u001B[0m%n", amount);
        } else {
            // default color for positive (for deposits)
            System.out.printf("%10.2f%n", amount);
        }
    }
}
