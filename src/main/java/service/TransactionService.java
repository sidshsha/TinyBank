package service;

import Constants.Constants;
import Constants.TransactionType;
import entity.Transaction;
import repo.TransactionRepository;
import repo.UserRepository;
import strategy.DepositStrategy;
import strategy.TransactionStrategy;
import strategy.WithdrawStrategy;

import java.util.List;

public class TransactionService {

    TransactionRepository transactionRepository;
    UserRepository userRepository;

    public TransactionService(TransactionRepository transactionRepository, UserRepository userRepository){
        this.transactionRepository = transactionRepository;
        this.userRepository = userRepository;
    }

    public void userAccountDepositOrWithdraw(int userId, TransactionType transactionType, double amount){
        TransactionStrategy transactionStrategy = null;
        boolean txnSuccessful = false;
        switch(transactionType){
            case TransactionType.DEPOSIT -> transactionStrategy = new DepositStrategy();
            case TransactionType.WITHDRAWL -> transactionStrategy = new WithdrawStrategy();
            default -> {
            }
        }
        if (transactionStrategy != null) {
            txnSuccessful = transactionStrategy.executeTransaction(userId, amount, transactionType, transactionRepository, userRepository);
        }
        if(txnSuccessful){
            userRepository.updateUserAccountBalance(userId, amount, transactionType);
        }
    }

    public void transferMoney(int userId, int targetUserId, double amount) {
        boolean transferSuccessful = transactionRepository.transferMoney(userId, targetUserId, amount);
        if(transferSuccessful){
            userRepository.updateUserAccountBalance(userId, amount, TransactionType.WITHDRAWL);
            userRepository.updateUserAccountBalance(targetUserId, amount, TransactionType.DEPOSIT);
        }
    }

    public List<Transaction> viewTransactionHistory(int userId) {
        return transactionRepository.viewTransactionHistory(userId, Constants.DEFAULT_PAGE_SIZE);
    }
}
