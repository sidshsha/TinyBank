package repo;

import Constants.TransactionType;
import entity.User;

public interface UserRepository {
    User createUser(String name);
    boolean deactivateUser(int userId);

    double viewUserAccountBalance(int userId);

    void updateUserAccountBalance(int userId, double amount, TransactionType transactionType);
}
