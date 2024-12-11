package entity;

import Constants.UserState;

import java.util.List;

public class User {
    private int id;
    private String name;
    private UserState userState;
    private double balance;

    public User(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.userState = builder.userState;
        this.balance = builder.balance;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UserState getUserState() {
        return userState;
    }

    public void setUserState(UserState userState) {
        this.userState = userState;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public static class Builder{
        private int id;
        private String name;
        private UserState userState;
        private double balance;

        public Builder setId(int id){
            this.id = id;
            return this;
        }

        public Builder setName(String name){
            this.name = name;
            return this;
        }

        public Builder setUserState(UserState userState){
            this.userState = userState;
            return this;
        }

        public Builder setBalance(double balance){
            this.balance = balance;
            return this;
        }

        public User build(){
            return new User(this);
        }
    }
    public static Builder builder(){
        return new Builder();
    }
}
