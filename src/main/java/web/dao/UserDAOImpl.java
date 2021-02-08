package web.dao;

import org.springframework.stereotype.Repository;
import web.model.Role;
import web.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

@Repository
public class UserDAOImpl implements UserDAO {

    private HashMap<Integer, Role> ALL_ROLE = new HashMap<Integer, Role>(){{
        put(0, new Role("Admin"));
        put(1, new  Role("User"));
        put(2, new Role("Manager"));
    }};

    @PersistenceContext
    protected EntityManager entityManager;

    @Override
    public List<User> getUsers() {
        Query query = entityManager.createQuery("select u from User u ");
        return query.getResultList();
    }

    @Override
    public void saveUser(User theUser) {
        entityManager.merge(theUser);
    }

//    private boolean exists(User theUser) {
//        try {
//            return entityManager.getReference(User.class, theUser.getId()) != null;
//        } catch (EntityNotFoundException e) {
//            return false;
//        }
//    }

    @Override
    public User getUser(int theId) {
        return entityManager.find(User.class, theId);
    }

    @Override
    public void deleteUser(int theId) {
        User t = entityManager.find(User.class, theId);
        if (t != null) {
            entityManager.remove(t);
        }
    }

//    @Override
//    public Role getRole(int theId) {
//        return entityManager.find(Role.class, theId);
//    }
//
//    @Override
//    public void deleteRole(int theId) {
//        Role t = entityManager.find(Role.class, theId);
//        if (t != null) {
//            entityManager.remove(t);
//        }
//    }

    @Override
    public Collection<Role> findAll() {
        Query query = entityManager.createQuery("select r from Role r ");
        return query.getResultList();
    }


    @Override
    public Role findOne(Integer id) {
        Role t = entityManager.find(Role.class, id);
        return t;
    }


}
