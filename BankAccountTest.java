package com.bank;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;

public class BankAccountTest {
    private BankAccount account;

    @BeforeEach
    void setup() {
        account = new BankAccount(1000.0);
    }

    @Test
    void testDeposit() {
        account.deposit(500.0);
        assertEquals(1500.0, account.getBalance(), "Balance should increase after deposit");
    }

    @Test
    void testWithdraw() {
        account.withdraw(200.0);
        assertEquals(800.0, account.getBalance(), "Balance should decrease after withdrawal");
    }

    @Test
    void testWithdrawInsufficientFunds() {
        Exception ex = assertThrows(IllegalArgumentException.class, () -> {
            account.withdraw(2000.0);
        });
        assertEquals("Insufficient funds", ex.getMessage());
    }

    @Test
    void testTransactionHistory() {
        account.deposit(100);
        account.withdraw(50);
        List<String> transactions = account.getTransactions();
        assertTrue(transactions.get(1).contains("Deposited"));
        assertTrue(transactions.get(2).contains("Withdrew"));
    }
}