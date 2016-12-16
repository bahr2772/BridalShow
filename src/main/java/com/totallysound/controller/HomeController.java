package com.totallysound.controller;

import com.totallysound.entities.Bride;
import com.totallysound.repositories.BrideDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
    public String create(String email, String name, String weddingDate, String phoneNumber, String howDidYouHear, Model model) {

            List<Bride> brideList = brideDao.findByNameContainingIgnoreCase(name);
            List<Bride> brideListEmail = (brideDao.findByEmailEqualsIgnoreCase(email));


            if (brideList.isEmpty() && brideListEmail.isEmpty()) {
                Bride bride = new Bride();
                bride.setEmail(email);
                bride.setName(name);
                bride.setWeddingDate(weddingDate);
                bride.setPhoneNumber(phoneNumber);
                bride.setHowDidYouHear(howDidYouHear);
                bride.setCheckedIn(true);
                brideDao.save(bride);
                model.addAttribute("bride", brideDao.findOne(bride.getId()));
                model.addAttribute("message", "success");
                return brideSearchForm(model);
            } else {
                model.addAttribute("message", "userFound");
                return brideSearchForm(model);
            }
    }

    @RequestMapping("/find-by-email")
    public String getByEmail(String email, Model model){
        try {
            model.addAttribute("brides", brideDao.findByEmailEquals(email));
            System.out.print(String.valueOf(brideDao.findByEmailEquals(email).getId()));
            return "result";
        } catch (NullPointerException e){

        }
            model.addAttribute("error", "notFound");
            return "index";
    }

    @RequestMapping("/checkin")
    public String getByNameAndEmail(String name, String email, Model model) {
        Bride bride = brideDao.findByNameEqualsIgnoreCaseOrEmailEqualsIgnoreCase(name, email);

        if(bride != null) {
            if (bride.isCheckedIn()) {
                model.addAttribute("message", "alreadyCheckedin");
                model.addAttribute("name", bride.getName());
                return brideSearchForm(model);
            } else {
                bride.setCheckedIn(true);
                brideDao.save(bride);
                model.addAttribute("bride", bride);
                model.addAttribute("name", bride.getName());
                model.addAttribute("message","success");
                return brideSearchForm(model);
            }
        }else {
            model.addAttribute("message", "notFound");
            return brideSearchForm(model);
        }
    }


    @RequestMapping("/find-by-name")
    public String getByName(String name, String email, Model model){
            System.out.println(name);
            System.out.println(email);
            model.addAttribute("bride", brideDao.findByNameContainingIgnoreCase(name));
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