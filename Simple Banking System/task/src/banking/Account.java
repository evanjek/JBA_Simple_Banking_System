package banking;

public class Account {
    private String cardNumber;
    private String cardPin;

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
    public Account(){
        cardNumber = "400000" + String.format("%010d", (long) (Math.random() * 9999999999L));
        cardPin = String.format("%04d", (long) (Math.random() * 9999L));
    }

    @Override
    public String toString() {
        return "Account{" +
                "cardNumber='" + cardNumber + '\'' +
                ", cardPin='" + cardPin + '\'' +
                '}';
    }
}
