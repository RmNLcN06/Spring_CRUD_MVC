package com.rmnlcn.Spring_CRUD_MVC.services;

import com.rmnlcn.Spring_CRUD_MVC.entities.Member;
import com.rmnlcn.Spring_CRUD_MVC.member.WebMember;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface MemberService extends UserDetailsService {

    public Member findByMemberName(String memberName);

    void save(WebMember webMember);
}
