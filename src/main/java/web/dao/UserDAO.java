package web.dao;

import web.model.Role;
import web.model.User;

import java.util.Collection;
import java.util.List;

public interface UserDAO {

    List<User> getUsers();

    void saveUser(User theCustomer);

    User getUser(int theId);

    void deleteUser(int theId);

    public Collection<Role> findAll();

    public Role findOne(Integer id);

}
