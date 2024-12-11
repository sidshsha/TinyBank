package service;

import entity.User;
import repo.UserRepository;

public class UserService {

    UserRepository userRepository;

    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public User createUser(String name) {
        return userRepository.createUser(name);
    }

    public boolean deactivateUser(int userId) {
        return userRepository.deactivateUser(userId);
    }

    public double viewAccountBalance(int userId) {
        return userRepository.viewUserAccountBalance(userId);
    }
}
