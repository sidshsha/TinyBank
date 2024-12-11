package repo;

import Constants.TransactionType;
import entity.Transaction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TransactionRepoTest {
    private TransactionRepo transactionRepo;

    @BeforeEach
    void setUp() {
        transactionRepo = new TransactionRepo();
    }

    @Test
    void executeDepositOrWithdraw_ShouldCreateDepositTransaction() {
        int userId = 1;
        double amount = 100.0;
        boolean result = transactionRepo.executeDepositOrWithdraw(userId, amount, TransactionType.DEPOSIT);
        assertTrue(result);
        List<Transaction> transactions = transactionRepo.viewTransactionHistory(userId, 10);
        assertEquals(1, transactions.size());
        Transaction txn = transactions.get(0);
        assertEquals(TransactionType.DEPOSIT, txn.getTransactionType());
        assertEquals(amount, txn.getTransactionAmount());
    }

    @Test
    void executeDepositOrWithdraw_ShouldCreateWithdrawTransaction() {
        int userId = 1;
        double amount = 50.0;
        boolean result = transactionRepo.executeDepositOrWithdraw(userId, amount, TransactionType.WITHDRAWL);
        assertTrue(result);
        List<Transaction> transactions = transactionRepo.viewTransactionHistory(userId, 10);
        assertEquals(1, transactions.size());
        Transaction txn = transactions.get(0);
        assertEquals(TransactionType.WITHDRAWL, txn.getTransactionType());
        assertEquals(amount, txn.getTransactionAmount());
    }

    @Test
    void transferMoney_ShouldCreateTransferTransactionForBothUsers() {
        int userId = 1;
        int targetUserId = 2;
        double amount = 200.0;
        boolean result = transactionRepo.transferMoney(userId, targetUserId, amount);
        assertTrue(result);
        List<Transaction> sourceUserTransactions = transactionRepo.viewTransactionHistory(userId, 10);
        assertEquals(1, sourceUserTransactions.size());

        List<Transaction> targetUserTransactions = transactionRepo.viewTransactionHistory(targetUserId, 10);
        assertEquals(1, targetUserTransactions.size());
    }

    @Test
    void viewTransactionHistory_ShouldReturnTransactionsInDescendingOrderOfCreatedAt() {
        int userId = 1;
        transactionRepo.executeDepositOrWithdraw(userId, 50.0, TransactionType.DEPOSIT);
        transactionRepo.executeDepositOrWithdraw(userId, 20.0, TransactionType.WITHDRAWL);
        transactionRepo.executeDepositOrWithdraw(userId, 100.0, TransactionType.DEPOSIT);
        List<Transaction> transactions = transactionRepo.viewTransactionHistory(userId, 10);
        assertEquals(3, transactions.size());
    }

    @Test
    void viewTransactionHistory_ShouldReturnEmptyListForUserWithoutTransactions() {
        int userId = 1;
        List<Transaction> transactions = transactionRepo.viewTransactionHistory(userId, 10);
        assertTrue(transactions.isEmpty());
    }
}
