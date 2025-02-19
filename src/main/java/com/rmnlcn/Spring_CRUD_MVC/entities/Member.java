package com.rmnlcn.Spring_CRUD_MVC.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name="member")
public class Member {

    @NotNull(message="is required")
    @Size(min=1, message="is required")
    private String memberName;

    @NotNull(message="is required")
    @Size(min=1, message="is required")
    private String password;


    // constructors
    public Member() {}

    public Member(String memberName, String password) {
        this.memberName = memberName;
        this.password = password;
    }


    // getters / setters
    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
