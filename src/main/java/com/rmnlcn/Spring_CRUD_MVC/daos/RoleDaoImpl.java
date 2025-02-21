package com.rmnlcn.Spring_CRUD_MVC.daos;

import com.rmnlcn.Spring_CRUD_MVC.entities.Member;
import com.rmnlcn.Spring_CRUD_MVC.entities.Role;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;

public class RoleDaoImpl implements RoleDao{

    @Autowired
    private EntityManager entityManager;

    public RoleDaoImpl(EntityManager theEntityManager) {
        this.entityManager = theEntityManager;
    }

    @Override
    public Role findRoleByName(String theRoleName) {

        // retrieve/read from database using role name
        TypedQuery<Role> theQuery = entityManager.createQuery("FROM Role WHERE name=:roleName", Role.class);
        theQuery.setParameter("roleName", theRoleName);

        Role theRole = null;
        try {
            theRole = theQuery.getSingleResult();
        }
        catch (Exception e) {
            theRole = null;
        }

        return theRole;
    }
}
