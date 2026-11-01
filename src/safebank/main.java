package safebank;

import exceptions.*;

import java.util.Scanner;

public class main {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        SafeBank bank = new SafeBank();

        Account account = bank.registerAccount("Elijah", "1234");

        System.out.println("Welcome to SafeBank");
        System.out.println("Account Number: " + account.getAccountNumber());

        try {
            System.out.print("Enter deposit amount: ");
            int amount = Integer.parseInt(input.nextLine());

            bank.deposit(amount, account.getAccountNumber());

            System.out.println("Deposit successful");

            System.out.print("Enter withdrawal amount: ");
            int withdrawal = Integer.parseInt(input.nextLine());

            bank.withdraw(withdrawal, "1234", account.getAccountNumber());

            System.out.println("Withdrawal successful");
            System.out.println("Balance: " + account.getBalance());

        } catch (NumberFormatException e) {
            System.out.println("Invalid amount. Enter numbers only.");

        } catch (InvalidAmountException |
                 InsufficientFundsException |
                 WithdrawalLimitExceededException e) {

            System.out.println(e.getMessage());

        } catch (SafeBankException e) {
            System.out.println(e.getMessage());
        }

        input.close();
    }
}