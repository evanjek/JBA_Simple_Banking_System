package banking;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    static String[] accountDetail;

    public static void main(String[] args) {
        Map<String, Account> mapAccount = new HashMap<>();
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;
        Account newAccount = null;
        while (!exit) {
            System.out.println("1. Create an account\n" +
                    "2. Log into account\n" +
                    "0. Exit");
            int meni = scanner.nextInt();
            switch (meni) {
                case 1:
                    newAccount = new Account();
                    mapAccount.put(newAccount.getCardNumber(), newAccount);
                    System.out.println("Your card has been created\n" +
                            "Your card number:\n" + newAccount.getCardNumber() + "\n" +
                            "Your card PIN:\n" + newAccount.getCardPin() + "\n");
                    break;
                case 2:
                    System.out.println("Enter your card number:");
                    String cardNumber = scanner.next();
                    System.out.println("Enter your PIN:");
                    String cardPin = scanner.next();
                    if (newAccount.getCardNumber().equals(cardNumber) && newAccount.getCardPin().equals(cardPin)) {
                        System.out.println("You have successfully logged in!");
                    } else
                        System.out.println("Wrong card number or PIN!");
                    System.out.println();
                    break;
                case 0:
                    System.out.println("\nBye!");
                    exit = true;
                    break;
            }
            if (meni == 2) {
                System.out.println("1. Balance\n" +
                        "2. Log out\n" +
                        "0. Exit");
                int meni2 = scanner.nextInt();
                switch (meni2) {
                    case 1:
                        System.out.println("\nBalance: 0");
                        break;
                    case 2:
                        System.out.println("\nYou have successfully logged out!");
                        break;
                    case 0:
                        System.out.println("Bye!");
                        exit = true;
                        break;
                }
            }

        }
    }
}
