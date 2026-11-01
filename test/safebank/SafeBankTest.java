package safebank;

import exceptions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SafeBankTest {

    SafeBank bank;
    Account account;

    @BeforeEach
    void setup() {
        bank = new SafeBank();
        account = bank.registerAccount("Elijah", "1234");
    }

    @Test
    void shouldRejectNegativeDeposit() {

        assertThrows(InvalidAmountException.class,
                () -> bank.deposit(-500, account.getAccountNumber()));
    }

    @Test
    void shouldRejectWithdrawalAboveBalance() {

        bank.deposit(50_000, account.getAccountNumber());

        assertThrows(InsufficientFundsException.class,
                () -> bank.withdraw(60_000, "1234", account.getAccountNumber()));
    }

    @Test
    void shouldRejectWithdrawalAboveLimit() {

        bank.deposit(200_000, account.getAccountNumber());

        assertThrows(WithdrawalLimitExceededException.class,
                () -> bank.withdraw(120_000, "1234", account.getAccountNumber()));
    }

    @Test
    void shouldRejectNonNumericInput() {

        assertThrows(NumberFormatException.class,
                () -> Integer.parseInt("hello"));
    }

    @Test
    void shouldAllowTransactionAfterFailedWithdrawal() {

        bank.deposit(200_000, account.getAccountNumber());

        assertThrows(WithdrawalLimitExceededException.class,
                () -> bank.withdraw(120_000, "1234", account.getAccountNumber()));

        bank.withdraw(50_000, "1234", account.getAccountNumber());

        assertEquals(150_000, account.getBalance());
    }
}