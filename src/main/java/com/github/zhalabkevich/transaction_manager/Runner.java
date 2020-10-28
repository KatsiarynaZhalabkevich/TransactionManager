package com.github.zhalabkevich.transaction_manager;

import com.github.zhalabkevich.transaction_manager.controller.TransactionController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import java.util.Scanner;

public class Runner {

    public static void main(String[] args) {
        TransactionController controller = new TransactionController();
        Scanner scanner = new Scanner(System.in);
        ResourceBundle resource = ResourceBundle.getBundle("prop");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(resource.getString("prop.datePattern"));

        String merchant;
        LocalDateTime start, end;

        //while loop show a server work
        while (true) {
            System.out.println("Welcome To The Transaction Analyser!");
            System.out.println("Please, enter the information:");
            System.out.println("fromDate in format 20/08/2018 12:00:00");
            scanner.hasNext();
            start = LocalDateTime.parse(scanner.next(), formatter);
            System.out.println("toDate in format 20/08/2018 13:00:00");
            scanner.hasNext();
            end = LocalDateTime.parse(scanner.next(), formatter);

            System.out.println("Merchant:");
            scanner.hasNext();
            merchant = scanner.next();

            System.out.println("Number of transactions = " + controller.getTotal(merchant, start, end));
            System.out.println("Average Transaction Value = " + controller.getAverageValue(merchant, start, end));

            System.out.println("*-*-*-*-*-*-*\n");

        }
    }
}
