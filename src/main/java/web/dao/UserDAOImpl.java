package web.dao;

import org.springframework.stereotype.Repository;
import web.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class UserDAOImpl implements UserDAO {

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
}
