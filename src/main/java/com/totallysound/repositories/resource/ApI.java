package com.totallysound.repositories.resource;

import com.totallysound.entities.Bride;
import com.totallysound.repositories.BrideDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by bahr2772 on 2/14/17.
 */

@RestController
@RequestMapping("/api")
public class ApI {
@Autowired
    private BrideDao brideDao;


    @PostMapping(value = "/registerBride")
    public String registerBride(@RequestParam(value = "email", required = false)String email,
                                @RequestParam(value = "name", required = false)String name,
                                @RequestParam(value = "phone", required = false)String phone,
                                @RequestParam(value = "weddingDate", required = false)String weddingDate,
                                @RequestParam(value = "howDidYouHear", required = false)String howDidYouHear,
                                @RequestParam(value = "numberOfGust", required = false)String numberOfGuest){

        Bride bride = brideDao.findByEmailEqualsIgnoreCase(email);

        if (bride == null) {
            System.out.println("Bride Not Found Saving Bride.");
            bride = new Bride();
            bride.setEmail(email);
            bride.setName(name);
            bride.setPhoneNumber(phone);
            bride.setWeddingDate(weddingDate);
            bride.setHowDidYouHear(howDidYouHear);
            bride.setNumberOfGuest(numberOfGuest);
            bride.setCheckedIn(true);
            brideDao.save(bride);
        return "brideSaved";

        } else {
            if (bride.isCheckedIn()) {
                System.out.println("Bride Found and Already Checked in.");
                return "brideFoundAndCheckedIn";
            } else {
                System.out.println("Bride Found.");
                return "brideFound";
            }
        }
    }

    @PostMapping(value = "/checkUser")
    public String checkUser(@RequestParam(value = "email", required = false) String email, @RequestParam(value = "numberOfGuest", required = false) String numberOfGuest){
        System.out.println("Checking for user [" + email + "], Number of Guest[" + numberOfGuest + "]");

        Bride bride = brideDao.findByEmailEqualsIgnoreCase(email);

        if (bride == null)
            return "notFound";

        else if (bride.isCheckedIn()) {
            System.out.println("Bride already checked in");
            return "AlreadyCheckedIn";
        } else {
            System.out.println("checked bride in");
            bride.setCheckedIn(true);
            bride.setNumberOfGuest(numberOfGuest);
            brideDao.save(bride);
            return "checkedIn";
        }
    }

}
