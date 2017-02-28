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
    public String create(String email, String name, String weddingDate, String phoneNumber, String howDidYouHear, String numberOfGuest, Model model) {

            List<Bride> brideListEmail = (brideDao.findByEmailContainingIgnoreCase(email));


            if (brideListEmail.isEmpty()) {
                Bride bride = new Bride();
                bride.setEmail(email);
                bride.setName(name);
                bride.setWeddingDate(weddingDate);
                bride.setPhoneNumber(phoneNumber);
                bride.setHowDidYouHear(howDidYouHear);
                bride.setNumberOfGuest(numberOfGuest);
                bride.setCheckedIn(true);
                brideDao.save(bride);
                model.addAttribute("bride", bride);
                model.addAttribute("email", bride.getEmail());
                model.addAttribute("message", "success");
                return brideSearchForm(model);
            } else {
                if (brideListEmail.get(0).isCheckedIn()) {
                    model.addAttribute("message", "alreadyCheckedin");
                    model.addAttribute("name", brideListEmail.get(0).getName());
                    model.addAttribute("email", brideListEmail.get(0).getEmail());
                }
                else
                model.addAttribute("message", "userFound");

                return brideSearchForm(model);
            }
    }



    @RequestMapping("/checkin")
    public String getByNameAndEmail(String name, String email, String numberOfGuest, Model model) {
        Bride bride = brideDao.findByNameEqualsIgnoreCaseOrEmailEqualsIgnoreCase(name, email);


        if(bride != null) {
            if (bride.isCheckedIn()) {
                model.addAttribute("message", "alreadyCheckedin");
                model.addAttribute("name", bride.getName());
                model.addAttribute("email", bride.getEmail());
                model.addAttribute("bride", bride);
                return brideSearchForm(model);
            } else {
                bride.setCheckedIn(true);
                bride.setNumberOfGuest(numberOfGuest);
                brideDao.save(bride);
                model.addAttribute("bride", bride);
                model.addAttribute("email", bride.getEmail());
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



    @GetMapping("/GARBAGE")
    @ResponseBody
    public String cleanGarbage (){

        int mb = 1024*1024;

        //Getting the runtime reference from system
        Runtime runtime = Runtime.getRuntime();

        System.out.println("##### Heap utilization statistics [MB] #####");

        //Print used memory
        System.out.println("Used Memory:"
                + (runtime.totalMemory() - runtime.freeMemory()) / mb);

        //Print free memory
        System.out.println("Free Memory:"
                + runtime.freeMemory() / mb);

        //Print total available memory
        System.out.println("Total Memory:" + runtime.totalMemory() / mb);

        //Print Maximum available memory
        System.out.println("Max Memory:" + runtime.maxMemory() / mb);

            System.gc();


        //Print used memory
        System.out.println("Used Memory:"
                + (runtime.totalMemory() - runtime.freeMemory()) / mb);

        //Print free memory
        System.out.println("Free Memory:"
                + runtime.freeMemory() / mb);

        //Print total available memory
        System.out.println("Total Memory:" + runtime.totalMemory() / mb);

        //Print Maximum available memory
        System.out.println("Max Memory:" + runtime.maxMemory() / mb);
        return "Cleaned";
    }

}
