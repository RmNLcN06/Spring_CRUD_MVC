package com.rmnlcn.Spring_CRUD_MVC.services;

import com.rmnlcn.Spring_CRUD_MVC.daos.MemberDao;
import com.rmnlcn.Spring_CRUD_MVC.daos.RoleDao;
import com.rmnlcn.Spring_CRUD_MVC.entities.Member;
import com.rmnlcn.Spring_CRUD_MVC.entities.Role;
import com.rmnlcn.Spring_CRUD_MVC.member.WebMember;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

@Service
public class MemberServiceImpl implements MemberService {

    private final MemberDao memberDao;

    private final RoleDao roleDao;

    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public MemberServiceImpl(MemberDao memberDao, RoleDao roleDao, BCryptPasswordEncoder passwordEncoder) {
        this.memberDao = memberDao;
        this.roleDao = roleDao;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Member findByMemberName(String memberName) {
        // check the database if the member already exists
        return memberDao.findByMemberName(memberName);
    }

    @Override
    public void save(WebMember webMember) {
        Member member = new Member();

        // assign member details to the member object
        member.setMemberName(webMember.getMemberName());
        member.setPassword(passwordEncoder.encode(webMember.getPassword()));
        member.setFirstName(webMember.getFirstName());
        member.setLastName(webMember.getLastName());
        member.setEmail(webMember.getEmail());
        member.setEnabled(true);

        // give member default role of "simple user"
        member.setRoles(Arrays.asList(roleDao.findRoleByName("SIMPLE_USER")));

        // save member in the database
        memberDao.save(member);
    }

    private Collection<SimpleGrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();

        for(Role tempRole : roles) {
            SimpleGrantedAuthority tempAuthority = new SimpleGrantedAuthority(tempRole.getName());
            authorities.add(tempAuthority);
        }

        return authorities;
    }

    @Override
    public UserDetails loadUserByUsername(String memberName) throws UsernameNotFoundException {
        Member member = memberDao.findByMemberName(memberName);

        if (member == null) {
            throw new UsernameNotFoundException("Invalid member name or password.");
        }

        Collection<SimpleGrantedAuthority> authorities = mapRolesToAuthorities(member.getRoles());

        return new org.springframework.security.core.userdetails.User(member.getMemberName(), member.getPassword(), authorities);
    }
}
