package repo;

import Constants.TransactionType;
import entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UserRepoTest {

    private UserRepo userRepo;

    @BeforeEach
    void setUp() {
        userRepo = new UserRepo();
    }

    @Test
    void createUser_ShouldCreateAndReturnNewUser() {
        String userName = "User ABC";
        User user = userRepo.createUser(userName);
        assertEquals(userName, user.getName(), "User ABC");
    }

    @Test
    void deactivateUser_ShouldDeactivateActiveUser() {
        User user = userRepo.createUser("User ABC");
        boolean result = userRepo.deactivateUser(user.getId());
        assertTrue(result, "true");
    }

    @Test
    void deactivateUser_ShouldThrowExceptionIfUserIsInvalid() {
        int invalidUserId = 999;
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                userRepo.deactivateUser(invalidUserId));
        assertEquals("Invalid UserId999", exception.getMessage());
    }

    @Test
    void viewUserAccountBalance_ShouldReturnBalanceForValidUser() {
        User user = userRepo.createUser("User XYZ");
        double expectedBalance = 0.0;
        double actualBalance = userRepo.viewUserAccountBalance(user.getId());
        assertEquals(expectedBalance, actualBalance);
    }

    @Test
    void updateUserAccountBalance_ShouldUpdateBalanceForDeposit() {
        User user = userRepo.createUser("User XYZ");
        double depositAmount = 100.0;
        userRepo.updateUserAccountBalance(user.getId(), depositAmount, TransactionType.DEPOSIT);
        assertEquals(depositAmount, user.getBalance());
    }

    @Test
    void updateUserAccountBalance_ShouldUpdateBalanceForWithdrawal() {
        User user = userRepo.createUser("User XYZ");
        userRepo.updateUserAccountBalance(user.getId(), 100.0, TransactionType.DEPOSIT);
        double withdrawalAmount = 50.0;
        userRepo.updateUserAccountBalance(user.getId(), withdrawalAmount, TransactionType.WITHDRAWL);
        assertEquals(50.0, user.getBalance());
    }
}
