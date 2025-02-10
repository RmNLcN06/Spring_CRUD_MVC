package com.rmnlcn.Spring_CRUD_MVC.controllers;

import com.rmnlcn.Spring_CRUD_MVC.entities.Wrestler;
import com.rmnlcn.Spring_CRUD_MVC.services.WrestlerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/wrestlers")
public class WrestlerController {

    private final WrestlerService wrestlerService;

    @Autowired
    public WrestlerController(WrestlerService theWrestlerService) {
        wrestlerService = theWrestlerService;
    }

    // add mapping for "/list"
    @GetMapping("/list")
    public String listWrestlers(Model theModel) {

        // get the wrestlers from DB
        List<Wrestler> theWrestlers = wrestlerService.findAll();

        // add to spring model
        theModel.addAttribute("wrestlers", theWrestlers);
        
        return "list-wrestlers";
    }
}
