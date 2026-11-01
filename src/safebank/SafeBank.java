package safebank;

import exceptions.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SafeBank {

    private final List<Account> listOfAccount = new ArrayList<>();

    private static final int WITHDRAWAL_LIMIT = 100_000;

    public Account registerAccount(String name, String pin) {

        if (name == null || name.isBlank()) {
            throw new SafeBankException("Name must not be blank");
        }

        if (pin == null || !pin.matches("\\d{4}")) {
            throw new SafeBankException("PIN must be exactly 4 digits");
        }

        Random random = new Random();

        Account account = new Account(
                name,
                random.nextInt(100_000_000, 1_000_000_000),
                0,
                pin
        );

        listOfAccount.add(account);

        return account;
    }

    private Account findAccount(int accountNumber) {

        for (Account account : listOfAccount) {
            if (account.getAccountNumber() == accountNumber) {
                return account;
            }
        }

        throw new SafeBankException("Account not found");
    }

    public String deposit(int amount, int accountNumber) {

        if (amount <= 0) {
            throw new InvalidAmountException(
                    "Deposit amount must be greater than zero"
            );
        }

        Account account = findAccount(accountNumber);

        account.setBalance(account.getBalance() + amount);

        return "Deposit successful";
    }

    public String withdraw(int amount, String pin, int accountNumber) {

        if (amount <= 0) {
            throw new InvalidAmountException(
                    "Withdrawal amount must be greater than zero"
            );
        }

        Account account = findAccount(accountNumber);

        if (!account.getPin().equals(pin)) {
            throw new SafeBankException("Incorrect PIN");
        }

        if (amount > account.getBalance()) {
            throw new InsufficientFundsException(
                    "Insufficient funds"
            );
        }

        if (amount > WITHDRAWAL_LIMIT) {
            throw new WithdrawalLimitExceededException(
                    "Withdrawal limit exceeded. Maximum is ₦100,000"
            );
        }

        account.setBalance(account.getBalance() - amount);

        return "Withdrawal successful";
    }
}