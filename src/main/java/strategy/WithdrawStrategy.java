package strategy;

import Constants.TransactionType;
import repo.TransactionRepository;
import repo.UserRepository;

public class WithdrawStrategy implements TransactionStrategy {
    @Override
    public boolean executeTransaction(int userId, double amount, TransactionType transactionType, TransactionRepository transactionRepo, UserRepository userRepository) {
        // Validate if the user has sufficient balance
        double currentBalance = userRepository.viewUserAccountBalance(userId);
        if (currentBalance < amount) {
            throw new IllegalArgumentException("Insufficient funds for user: " + userId);
        }
        // Generate a transaction and update the transaction repository
        return transactionRepo.executeDepositOrWithdraw(userId, amount, transactionType);
    }
}
