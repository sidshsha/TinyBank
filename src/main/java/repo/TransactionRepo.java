package repo;

import Constants.TransactionType;
import entity.Transaction;

import java.sql.Array;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class TransactionRepo implements TransactionRepository{

    AtomicInteger id = new AtomicInteger(0);
    Map<Integer, List<Transaction>> userTransactions = new HashMap<>();
    Map<Integer, Transaction> transactions = new HashMap<>();

    public boolean executeDepositOrWithdraw(int userId, double amount, TransactionType transactionType) {
        int txnId = generateTxnId();
        Transaction txn = Transaction.builder()
                .setTransactionId(txnId)
                .setUserId(userId)
                .setTransactionAmount(amount)
                .setTransactionType(transactionType)
                .setCreatedAt(Timestamp.from(Instant.now()))
                .build();

        transactions.put(txnId, txn);
        System.out.println("txn="+txn);

        if(!userTransactions.containsKey(userId)){
            userTransactions.put(userId, new ArrayList<>());
        }
        userTransactions.get(userId).add(txn);


        return true;
    }

    @Override
    public boolean transferMoney(int userId, int targetUserId, double amount) {
        int txnId = generateTxnId();
        if(transactions.containsKey(txnId)){
            throw new IllegalArgumentException("Transaction already exists"+ txnId);
        }
        Transaction txn = Transaction.builder()
                .setTransactionId(txnId)
                .setUserId(userId)
                .setTargetUserId(targetUserId)
                .setTransactionAmount(amount)
                .setTransactionType(TransactionType.TRANSFER)
                .setCreatedAt(Timestamp.from(Instant.now()))
                .build();
        transactions.put(txnId, txn);

        if(!userTransactions.containsKey(userId)){
            userTransactions.put(userId, new ArrayList<>());
        }
        userTransactions.get(userId).add(txn);

        if(!userTransactions.containsKey(targetUserId)){
            userTransactions.put(targetUserId, new ArrayList<>());
        }
        userTransactions.get(targetUserId).add(txn);
        return true;
    }

    @Override
    public List<Transaction> viewTransactionHistory(int userId, int size) {
        if(!userTransactions.containsKey(userId)){
            return new ArrayList<>();
        }
        List<Transaction> list = userTransactions.get(userId);
        return list.stream()
                .sorted((t1, t2) -> t2.getCreatedAt().compareTo(t1.getCreatedAt()))
                .limit(size)
                .collect(Collectors.toList());
    }

    private int generateTxnId(){
        return id.incrementAndGet();
    }
}
