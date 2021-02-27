package web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import web.dao.RoleDAO;
import web.dao.UserDAO;
import web.model.User;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private UserDAO userDAO;

    @Autowired
    public UserServiceImpl(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    @Transactional
    public List<User> getUsers() {
        return userDAO.getUsers();
    }

    @Override
    public User getUserByName(String s) {
        return userDAO.getUserByName(s);
    }

    @Override
    @Transactional
    public void saveUser(User theUser) {

        userDAO.saveUser(theUser);
    }

    @Override
    @Transactional
    public User getUser(int theId) {
        return userDAO.getUser(theId);
    }

    @Override
    @Transactional
    public void deleteUser(int theId) {
        userDAO.deleteUser(theId);
    }

}
