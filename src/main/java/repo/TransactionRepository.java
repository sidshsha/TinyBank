package repo;

import Constants.TransactionType;
import entity.Transaction;

import java.util.List;

public interface TransactionRepository {
    boolean transferMoney(int userId, int targetUserId, double amount);
    List<Transaction> viewTransactionHistory(int userId, int pageSize);
    boolean executeDepositOrWithdraw(int userId, double amount, TransactionType transactionType);
}
