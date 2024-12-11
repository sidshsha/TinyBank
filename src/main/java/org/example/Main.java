package org.example;

import Constants.TransactionType;
import entity.Transaction;
import entity.User;
import repo.TransactionRepo;
import repo.UserRepo;
import repo.UserRepository;
import service.TransactionService;
import service.UserService;

import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
        UserRepo userRepo = new UserRepo();
        UserService userService = new UserService(userRepo);

        User user1 = userService.createUser("Alex");
        System.out.println("User="+user1.getName() + " created successfully");

        User user2 = userService.createUser("Bob");
        System.out.println("User="+user2.getName() + " created successfully");

        User user3 = userService.createUser("Kevin");
        System.out.println("User="+user3.getName() + " created successfully");

        boolean result = userService.deactivateUser(user3.getId());
        if(result){
            System.out.println("User="+user3.getName() + " deactivated successfully");
        }

        TransactionService transactionService = new TransactionService(new TransactionRepo(), userRepo);
        transactionService.userAccountDepositOrWithdraw(user1.getId(), TransactionType.DEPOSIT, 1000);
        double userBalance1 = userService.viewAccountBalance(user1.getId());
        System.out.println("userBalanceAfterDeposit="+ userBalance1);

        transactionService.userAccountDepositOrWithdraw(user1.getId(), TransactionType.WITHDRAWL, 500);
        double userBalance2 = userService.viewAccountBalance(user1.getId());
        System.out.println("userBalanceAfterWithdrawl="+ userBalance2);

        transactionService.transferMoney(user1.getId(), user2.getId(), 500);
        double userBalance3 = userService.viewAccountBalance(user1.getId());
        System.out.println("userBalanceAfterTransfer="+ userBalance3);

        List<Transaction> user1Transactions = transactionService.viewTransactionHistory(user1.getId());
        for(Transaction txn : user1Transactions){
            System.out.print("user1Transactions="+ txn);
            System.out.println();
        }

        List<Transaction> user2Transactions = transactionService.viewTransactionHistory(user2.getId());
        for(Transaction txn : user2Transactions){
            System.out.print("user2Transactions="+ txn);
            System.out.println();
        }

    }
}