package com.rmnlcn.Spring_CRUD_MVC.securities;

import com.rmnlcn.Spring_CRUD_MVC.entities.Member;
import com.rmnlcn.Spring_CRUD_MVC.services.MemberService;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    private final MemberService memberService;

    public CustomAuthenticationSuccessHandler(MemberService theMemberService) {
        memberService = theMemberService;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        System.out.println("In customAuthenticationSuccessHandler");

        String memberName = authentication.getName();

        System.out.println("memberName=" + memberName);

        Member theMember = memberService.findByMemberName(memberName);

        // now place in the session
        HttpSession session = request.getSession();
        session.setAttribute("member", theMember);

        // forward to home page
        response.sendRedirect(request.getContextPath() + "/simple-users");
    }
}
