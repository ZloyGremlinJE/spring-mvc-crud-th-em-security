package web.dao;

import web.model.Role;

import java.util.Collection;

public interface RoleDAO {

    public Collection<Role> findAll();

    public Role getRole(int id);

}
