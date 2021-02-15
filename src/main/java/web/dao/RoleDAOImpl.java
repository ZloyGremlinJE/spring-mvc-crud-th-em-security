package web.dao;

import org.springframework.stereotype.Repository;
import web.model.Role;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.Collection;

@Repository
public class RoleDAOImpl implements RoleDAO {

    @PersistenceContext
    protected EntityManager entityManager;

    @Override
    public Collection<Role> findAll() {
        Query query = entityManager.createQuery("select r from Role r");
        return query.getResultList();
    }

    @Override
    public Role getRole(int id) {
        Role t = entityManager.find(Role.class, id);
        return t;
    }


}
