package banking;

public class Account {
    private String cardNumber;
    private String cardPin;
    private int balance;

    public Account(String cardNumber, String cardPin) {
        this.cardNumber = cardNumber;
        this.cardPin = cardPin;
        this.balance = balance;
    }

    public Account() {
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public void setCardPin(String cardPin) {
        this.cardPin = cardPin;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public String getCardPin() {
        return cardPin;
    }

    public int getBalance() {
        return balance;
    }

    public String createCardPin() {
        cardPin = String.format("%04d", (long) (Math.random() * 9999));
        return cardPin;
    }

    public String createCardNumber() {
        cardNumber = checkSum();
        return cardNumber;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public String checkSum() {
        String card = "400000" + String.format("%09d", (long) (Math.random() * 999999999L));
        for (int i = 0; i <= 9; i++) {
            String checksum = card + i;
            if (check(checksum)) {
                return checksum;
            }
        }
        throw new IllegalStateException("Probably you made a mistake in the card number. Please try again!\n");
    }

    public static boolean check(String cardNumber) {
        int sum = 0;
        boolean alternate = false;
        for (int i = cardNumber.length() - 1; i >= 0; i--) {
            int n = Integer.parseInt(cardNumber.substring(i, i + 1));
            if (alternate) {
                n *= 2;
                if (n > 9) {
                    n = (n % 10) + 1;
                }
            }
            sum += n;
            alternate = !alternate;
        }
        return (sum % 10 == 0);
    }
}