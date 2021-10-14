package banking;

import java.util.Scanner;

import static banking.Account.check;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;
        boolean exit2 = false;
        boolean dbCreated = false;
        DataBaseManager databaseManager = new DataBaseManager();
        Account newAccount = new Account();
        Account currAccount = null;
        while (!exit) {
            if (!dbCreated) {
                databaseManager.makeDBConnection();
                dbCreated = true;
            }

            System.out.println("1. Create an account\n" +
                    "2. Log into account\n" +
                    "0. Exit");
            int meni = scanner.nextInt();
            switch (meni) {
                case 1:
                    newAccount.createCardNumber();
                    newAccount.createCardPin();
                    databaseManager.addAccount(newAccount);
                    System.out.println("Your card has been created\n" +
                            "Your card number:\n" + newAccount.getCardNumber() + "\n" +
                            "Your card PIN:\n" + newAccount.getCardPin());
                    break;
                case 2:
                    System.out.println("Enter your card number:");
                    String cardNumber = scanner.next();
                    System.out.println("Enter your PIN:");
                    String cardPin = scanner.next();
                    if (databaseManager.isCardInDatabase(cardNumber, cardPin)) {
                        currAccount = new Account(cardNumber, cardPin);
                        System.out.println("You have successfully logged in!");
                        exit2 = false;
                    } else {
                        System.out.println("Wrong card number or PIN");
                        meni = 3;
                    }
                    System.out.println();
                    break;
                case 0:
                    System.out.println("\nBye!");
                    databaseManager.closeDBConnection();
                    exit = true;
                    break;
            }

            if (meni == 2) {
                while (!exit2) {
                    System.out.println("1. Balance\n" +
                            "2. Add income\n" +
                            "3. Do transfer\n" +
                            "4. Close account\n" +
                            "5. Log out\n" +
                            "0. Exit");
                    int meni2 = scanner.nextInt();
                    switch (meni2) {
                        case 1:
                            System.out.println("\nBalance: " + databaseManager.returnBalance(currAccount.getCardNumber()));
                            break;
                        case 2:
                            System.out.println("\nEnter income:");
                            int money = scanner.nextInt();
                            currAccount.setBalance(money);
                            databaseManager.updateBalance(currAccount.getCardNumber(), money);
                            System.out.println("Income was added!");
                            break;
                        case 3:
                            System.out.println("\nTransfer\n" +
                                    "Enter card number:");
                            String number = scanner.next();
                            if (currAccount.getCardNumber().equals(number)) {
                                System.out.println("You can't transfer money to the same account!");
                            } else if (!check(number))
                                System.out.println("Probably you made a mistake in the card number. Please try again!");
                            else if (!databaseManager.isCardInDatabase(number))
                                System.out.println("Such a card does not exist.");
                            else {
                                System.out.println("\nEnter how much money you want to transfer:");
                                int transfer = scanner.nextInt();
                                if (transfer >= databaseManager.returnBalance(currAccount.getCardNumber()))
                                    System.out.println("Not enough money!");
                                else {
                                    System.out.println("Success!");
                                    databaseManager.updateBalance(currAccount.getCardNumber(), -transfer);
                                    databaseManager.updateBalance(number, +transfer);
                                }
                            }
                            break;
                        case 4:
                            databaseManager.deleteAccount(currAccount);
                            System.out.println("The account has been closed!");
                            exit2 = true;
                            break;
                        case 5:
                            System.out.println("\nYou have successfully logged out!");
                            currAccount = null;
                            exit2 = true;
                            break;
                        case 0:
                            System.out.println("\nBye!");
                            databaseManager.closeDBConnection();
                            exit2 = true;
                            exit = true;
                            break;
                    }
                }
            }
        }
    }
}