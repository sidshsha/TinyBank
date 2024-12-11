package entity;

import Constants.TransactionStatus;
import Constants.TransactionType;
import java.sql.Timestamp;

public class Transaction {
    private int transactionId;
    private int userId;
    private TransactionType transactionType;
    private TransactionStatus transactionStatus;
    private double transactionAmount;
    private int targetUserId;
    private Timestamp createdAt;

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public double getTransactionAmount() {
        return transactionAmount;
    }

    public Transaction(Builder builder) {
        this.transactionId = builder.transactionId;
        this.userId = builder.userId;
        this.transactionType = builder.transactionType;
        this.transactionStatus = builder.transactionStatus;
        this.transactionAmount = builder.transactionAmount;
        this.targetUserId = builder.targetUserId;
        this.createdAt = builder.createdAt;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "transactionId=" + transactionId +
                ", userId=" + userId +
                ", transactionType=" + transactionType +
                ", transactionStatus=" + transactionStatus +
                ", transactionAmount=" + transactionAmount +
                ", targetUserId=" + targetUserId +
                ", createdAt=" + createdAt +
                '}';
    }

    public static class Builder{
        private int transactionId;
        private int userId;
        private TransactionType transactionType;
        private TransactionStatus transactionStatus;
        private double transactionAmount;
        private int targetUserId;
        private Timestamp createdAt;

        public Builder setTransactionId(int transactionId){
            this.transactionId = transactionId;
            return this;
        }

        public Builder setUserId(int userId){
            this.userId = userId;
            return this;
        }

        public Builder setTransactionType(TransactionType transactionType){
            this.transactionType = transactionType;
            return this;
        }

        public Builder setTransactionStatus(TransactionStatus transactionStatus){
            this.transactionStatus = transactionStatus;
            return this;
        }

        public Builder setTransactionAmount(double transactionAmount){
            this.transactionAmount = transactionAmount;
            return this;
        }

        public Builder setTargetUserId(int targetUserId){
            this.targetUserId = targetUserId;
            return this;
        }

        public Builder setCreatedAt(Timestamp createdAt){
            this.createdAt = createdAt;
            return this;
        }

        public Transaction build(){
            return new Transaction(this);
        }
    }
    public static Builder builder(){
        return new Builder();
    }
}
