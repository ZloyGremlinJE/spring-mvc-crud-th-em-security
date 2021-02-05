package web.service;

import web.model.User;

import java.util.List;

public interface UserService {
    List<User> getUsers();

    void saveUser(User theUser);

    User getUser(int theId);

    void deleteUser(int theId);
}
