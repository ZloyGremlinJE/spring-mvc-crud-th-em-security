package web.service;

import web.model.Role;

import java.util.Collection;

public interface RoleService {

    public Collection<Role> findAll();

    public Role getRole(int id);
}
