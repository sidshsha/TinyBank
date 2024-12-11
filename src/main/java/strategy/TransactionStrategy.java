package strategy;

import Constants.TransactionType;
import repo.TransactionRepo;
import repo.TransactionRepository;
import repo.UserRepository;

public interface TransactionStrategy {
    boolean executeTransaction(int userId, double amount, TransactionType transactionType, TransactionRepository transactionRepo, UserRepository userRepository);
}
