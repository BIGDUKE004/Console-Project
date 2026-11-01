package safebank;

public class Account {
    private String name;
    private int accountNumber;
    private int balance;
    private String pin;

    public Account(String name, int accountNumber, int balance, String pin) {
        this.name = name;
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.pin = pin;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

//    public void setAccountNumber(String accountNumber) {
//        this.accountNumber = accountNumber;
//    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }
}
