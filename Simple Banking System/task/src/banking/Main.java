package banking;

import java.util.Scanner;

public class Main {
    static String[] accountDetail;
    public static void main(String[] args) {
        int i = -1;
        int meni = -1;
        boolean ruuning = false;
        Scanner s = new Scanner(System.in);
        do {
            if (!ruuning){
                System.out.println("1. Create an account\n" +
                        "2. Log into account\n" +
                        "0. Exit");
                meni = s.nextInt();
                if (meni == 1) {
                    i++;
                    int array = i + 2;
                    Account newAccount = new Account();
                    accountDetail = new String[array];
                    accountDetail[i] = newAccount.getCardNumber();
                    accountDetail[++i] = newAccount.getCardPin();
                    System.out.println("Your card has been created\n" +
                            "Your card number:\n" + newAccount.getCardNumber() + "\n" +
                            "Your card PIN:\n" + newAccount.getCardPin() + "\n");
                } else if (meni == 2) {
                    System.out.println("Enter your card number:");
                    String cardNumber = s.next();
                    System.out.println("Enter your PIN:");
                    String cardPin = s.next();
                    if (accountDetail[0].equals(cardNumber) && accountDetail[1].equals(cardPin)) {
                        System.out.println("You have successfully logged in!");
                        ruuning = true;
                    }
                    else
                        System.out.println("Wrong card number or PIN!");
                    System.out.println();
                }else if (meni == 0)
                    System.out.println("\nBye!");
            }else{
                System.out.println("1. Balance\n" +
                                "2. Log out\n" +
                                "0. Exit");
                int meni2 = s.nextInt();
                if (meni2 == 1)
                    System.out.println("\nBalance: 0");
                else if (meni2 == 2) {
                    System.out.println("\nYou have successfully logged out!");
                    ruuning = false;
                }
                else if (meni2 == 0)
                    System.out.println("Bye!");
            }

        }while (meni != 0);
    }
}
