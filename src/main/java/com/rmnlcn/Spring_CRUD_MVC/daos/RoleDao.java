package com.rmnlcn.Spring_CRUD_MVC.daos;

import com.rmnlcn.Spring_CRUD_MVC.entities.Role;

public interface RoleDao {

    public Role findRoleByName(String theRoleName);
}
