package com.totallysound.controller;

import com.totallysound.repositories.BrideDao;
import com.totallysound.entities.Bride;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private BrideDao brideDao;

    @GetMapping(value = {"/", "/index", "/welcome"})
    public String brideSearchForm(Model model){
        model.addAttribute("bride", new Bride());
        return "index";
    }


    @RequestMapping("/create")
    public String create(String email, String name, String weddingDate, Model model){
            Bride bride = new Bride(email, name);
            bride.setEmail(email);
            bride.setName(name);
            bride.setWeddingDate(weddingDate);
            brideDao.save(bride);
            model.addAttribute("brides", brideDao.findOne(bride.getId()));
        return "result";
    }

    @RequestMapping("/find-by-email")
    public String getByEmail(String email, Model model){
        model.addAttribute("brides", brideDao.findByEmailEquals(email));
        System.out.print(String.valueOf(brideDao.findByEmailEquals(email).getId()));
        return "result";
    }

    @RequestMapping("/checkin")
    public String getByNameAndEmail(String name, String email, Model model) {
        Bride bride = brideDao.findByNameEqualsIgnoreCaseOrEmailEqualsIgnoreCase(name, email);

        if (bride.isCheckedIn()) {
            model.addAttribute("message", "You have already been checked-in. If this is an error please see a staff member.");
    } else {
        bride.setCheckedIn(true);
            brideDao.save(bride);
            model.addAttribute("brides", bride);
    }

        return "result";
    }


    @RequestMapping("/find-by-name")
    public String getByName(String name, String email, Model model){
            System.out.println(name);
            System.out.println(email);
            model.addAttribute("brides", brideDao.findByNameContainingIgnoreCase(name));
            model.addAttribute("message", "Thank you, You have been checked in. Please enjoy the day. This page will be redirected in 5 seconds.");
        return  "result";
    }


    @RequestMapping("/delete")
    @ResponseBody
    public String delete(long id){
        try{
            Bride bride = new Bride(id);
            brideDao.delete(bride);
        } catch (Exception e){
            return "Error deleting";
        }
        return "User deleted";
    }


}