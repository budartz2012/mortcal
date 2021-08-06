package com.company;

import java.text.NumberFormat;
import java.util.Scanner;

public class Main {

    final static byte MONTHS_IN_A_YEAR = 12;
    final static byte PERCENT = 100;

    public static void main(String[] args) {
        int principal = (int) readNumber("Principal:", 1000, 1_000_000);
        float annualInterests = (float) readNumber("Annual Interests:", 1.5, 3.5);
        byte years = (byte) readNumber("Years:", 1, 40 );

        printMortgage(principal, annualInterests, years);
        printPaymentSchedule(principal, annualInterests, years);
    }

    private static void printMortgage(int principal, float annualInterests, byte years) {
        double mortgage = calculateMortgage(principal, annualInterests, years);
        String mortgageFormatted = NumberFormat.getCurrencyInstance().format(mortgage);
        System.out.println();
        System.out.println("MORTGAGE");
        System.out.println("---------");
        System.out.println("Monthly Payments: " + mortgageFormatted);
    }

    private static void printPaymentSchedule(int principal, float annualInterests, byte years) {
        System.out.println();
        System.out.println("PAYMENT SCHEDULE");
        System.out.println("----------------");
        for (short month = 1; month <= years * MONTHS_IN_A_YEAR; month++){
            double balance = calculateBalance (principal, annualInterests, years, month);
            System.out.println(NumberFormat.getCurrencyInstance().format(balance));

        }
    }


    public static double readNumber (String prompt, double min, double max){
        Scanner scanner =new Scanner(System.in);
        double value;
        while (true) {
            System.out.print(prompt);
            value = scanner.nextFloat();
            if (value >= min && value <= max)
                break;
            System.out.println("please enter a value between" + min + "and" + max);
        }
        return value;

    }

    public static double calculateBalance(
            int principal,
            float annualInterests,
            byte years,
            short numberOfPaymentsMade

    ){
            float monthlyInterests = annualInterests / PERCENT / MONTHS_IN_A_YEAR;
            float numberOfPayments = years * MONTHS_IN_A_YEAR;

            double balance = principal
                    * (Math.pow(1 + monthlyInterests, numberOfPayments) - (Math.pow(1 + monthlyInterests, numberOfPaymentsMade)))
                    / (Math.pow(1 + monthlyInterests, numberOfPayments) - 1 );

            return balance;


    }
    public static double calculateMortgage(

            int principal,
            float annualInterests,
            byte years){

            int numberOfPayments = years * MONTHS_IN_A_YEAR;
            float monthlyInterests = annualInterests / PERCENT / MONTHS_IN_A_YEAR;

            double mortgage = principal
                * (monthlyInterests * Math.pow(1 + monthlyInterests, numberOfPayments))
                / (Math.pow(1 + monthlyInterests, numberOfPayments) - 1);


            return mortgage;
    }

}


