package personal.controllers;

import personal.model.Repository;
import personal.model.User;

import java.util.List;

public class UserController {
    private final Repository repository;

    public UserController(Repository repository) {
        this.repository = repository;
    }

    public User deleteUser(User user) {
        repository.deleteUser(user);
        return user;
    }
    public void saveUser(User user) {
        repository.CreateUser(user);
    }

    public List<User> readAllUsers(){
        return repository.getAllUsers();
    }

    public User updateUser(User user){
        User updatedUser = repository.updateUser(user);
        return updatedUser;
    }

    public User readUser(String userId) throws Exception {
        List<User> users = repository.getAllUsers();
        for (User user : users) {
            if (user.getId().equals(userId)) {
                return user;
            }
        }

        throw new Exception("User not found");
    }
}
