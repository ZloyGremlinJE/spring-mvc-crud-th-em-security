package web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import web.dao.RoleDAO;
import web.model.Role;
import java.util.Collection;

@Service
public class RoleServiceImpl implements RoleService{

    @Autowired
    private RoleDAO roleDAO;

    @Transactional
    @Override
    public Collection<Role> findAll() {
        return roleDAO.findAll();
    }

    @Transactional
    @Override
    public Role getRole(int id) {
        return roleDAO.getRole(id);
    }
}
