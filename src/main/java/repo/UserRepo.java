package repo;

import Constants.TransactionType;
import Constants.UserState;
import entity.User;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;


public class UserRepo implements UserRepository {
    Map<Integer, User> userMap = new HashMap<>();
    AtomicInteger id = new AtomicInteger(0);
    @Override
    public User createUser(String name) {
        int userId = id.incrementAndGet();
        User user = User.builder()
                .setId(userId)
                .setName(name)
                .setUserState(UserState.ACTIVE_USER)
                .setBalance(0.0)
                .build();
        userMap.put(userId, user);
        return user;
    }

    @Override
    public boolean deactivateUser(int userId) {
        User user = userMap.get(userId);
        if(user == null){
            throw new IllegalArgumentException("Invalid UserId"+ userId);
        }
        UserState userState = user.getUserState();
        if(!userState.equals(UserState.ACTIVE_USER)){
            throw new IllegalArgumentException("User is not active"+ userId);
        }
        user.setUserState(UserState.INACTIVE_USER);
        return true;
    }

    @Override
    public double viewUserAccountBalance(int userId) {
        User user = userMap.get(userId);
        if(user == null){
            throw new IllegalArgumentException("Invalid UserId"+ userId);
        }
        return user.getBalance();
    }

    @Override
    public void updateUserAccountBalance(int userId, double amount, TransactionType transactionType) {
        User user = userMap.get(userId);
        if(user == null){
            throw new IllegalArgumentException("Invalid UserId"+ userId);
        }
        switch(transactionType){
            case TransactionType.DEPOSIT -> user.setBalance(user.getBalance()+amount);

            case TransactionType.WITHDRAWL -> user.setBalance(user.getBalance()-amount);

        }
    }
}
