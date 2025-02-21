package com.rmnlcn.Spring_CRUD_MVC.controllers;

import com.rmnlcn.Spring_CRUD_MVC.entities.Member;
import com.rmnlcn.Spring_CRUD_MVC.member.WebMember;
import com.rmnlcn.Spring_CRUD_MVC.services.MemberService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.util.logging.Logger;

@Controller
@RequestMapping("/register")
public class RegistrationController {

    private Logger logger = Logger.getLogger(getClass().getName());

    private MemberService memberService;

    @Autowired
    public RegistrationController(MemberService memberService) {
        this.memberService = memberService;
    }

    @InitBinder
    public void initBinder(WebDataBinder dataBinder) {
        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);

        dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
    }

    @GetMapping("/showRegistrationForm")
    public String showMyLoginPage(Model theModel) {

        theModel.addAttribute("webMember", new WebMember());

        return "registers/registration-form";
    }

    @PostMapping("/processRegistrationForm")
    public String processRegistrationForm(
            @Valid @ModelAttribute("webMember") WebMember theWebMember,
            BindingResult theBindingResult,
            HttpSession session, Model theModel) {

        String memberName = theWebMember.getMemberName();
        logger.info("Processing registration form for: " + memberName);

        // form validation
        if (theBindingResult.hasErrors()){
            return "registers/registration-form";
        }

        // check the database if member already exists
        Member existing = memberService.findByMemberName(memberName);
        if (existing != null){
            theModel.addAttribute("webMember", new WebMember());
            theModel.addAttribute("registrationError", "Member name already exists.");

            logger.warning("Member name already exists.");
            return "registers/registration-form";
        }

        // create member account and store in the database
        memberService.save(theWebMember);

        logger.info("Successfully created user: " + memberName);

        // place user in the web http session for later use
        session.setAttribute("member", theWebMember);

        return "registers/registration-confirmation";
    }
}
