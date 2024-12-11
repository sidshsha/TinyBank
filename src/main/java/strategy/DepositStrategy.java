package strategy;

import Constants.TransactionType;
import repo.TransactionRepository;
import repo.UserRepository;

public class DepositStrategy implements TransactionStrategy {
    @Override
    public boolean executeTransaction(int userId, double amount, TransactionType transactionType, TransactionRepository transactionRepo, UserRepository userRepository) {
        // Generate a transaction and update the transaction repository
        return transactionRepo.executeDepositOrWithdraw(userId, amount, transactionType);
    }
}
