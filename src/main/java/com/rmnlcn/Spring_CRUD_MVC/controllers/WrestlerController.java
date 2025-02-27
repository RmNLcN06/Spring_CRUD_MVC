package com.rmnlcn.Spring_CRUD_MVC.controllers;

import com.rmnlcn.Spring_CRUD_MVC.entities.Wrestler;
import com.rmnlcn.Spring_CRUD_MVC.services.WrestlerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
        
        return "wrestlers/list-wrestlers";
    }

    @GetMapping("/showFormForAdd")
    public String showFormForAdd(Model theModel) {

        // create model attribute to bind form data
        Wrestler theWrestler = new Wrestler();

        theModel.addAttribute("wrestler", theWrestler);

        return "wrestlers/wrestler-form";
    }

    @GetMapping("/showFormForUpdate")
    public String showFormForUpdate(@RequestParam("wrestlerId") int theId, Model theModel) {

        // get the wrestler from the service
        Wrestler theWrestler = wrestlerService.findById(theId);

        // set wrestler in the model to prepopulate the form
        theModel.addAttribute("wrestler", theWrestler);

        // send over to the form
        return "wrestlers/wrestler-form";
    }

    @PostMapping("/save")
    public String saveWrestler(@ModelAttribute("wrestler") Wrestler theWrestler) {

        // save the wrestler
        wrestlerService.save(theWrestler);

        // use a redirect to prevent duplicate submissions
        return "redirect:/wrestlers/list";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("wrestlerId") int theId) {

        // delete the wrestler
        wrestlerService.deleteById(theId);

        // redirect to the /wrestlers/list
        return "redirect:/wrestlers/list";
    }
}
