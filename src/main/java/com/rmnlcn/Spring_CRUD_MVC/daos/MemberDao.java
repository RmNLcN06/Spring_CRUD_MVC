package com.rmnlcn.Spring_CRUD_MVC.daos;

import com.rmnlcn.Spring_CRUD_MVC.entities.Member;

public interface MemberDao {
    Member findByMemberName(String memberName);

    void save(Member member);
}
