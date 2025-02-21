package com.rmnlcn.Spring_CRUD_MVC.daos;

import com.rmnlcn.Spring_CRUD_MVC.entities.Member;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class MemberDaoImpl implements MemberDao {

    private final EntityManager entityManager;

    @Autowired
    public MemberDaoImpl(EntityManager theEntityManager) {
        this.entityManager = theEntityManager;
    }

    @Override
    public Member findByMemberName(String theMemberName) {

        // retrieve/read from database using member name
        TypedQuery<Member> theQuery = entityManager.createQuery("FROM Member WHERE memberName=:mName AND enabled=true", Member.class);
        theQuery.setParameter("mName", theMemberName);

        Member theMember = null;
        try {
            theMember = theQuery.getSingleResult();
        }
        catch (Exception e) {
            theMember = null;
        }

        return theMember;
    }

    @Override
    @Transactional
    public void save(Member theMember) {
        // create the member
        entityManager.merge(theMember);
    }
}
