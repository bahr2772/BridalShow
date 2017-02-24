package com.totallysound.repositories.resource;

import com.totallysound.entities.Bride;
import com.totallysound.repositories.BrideDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by bahr2772 on 2/14/17.
 */

@RestController
@RequestMapping("/api")
public class ApI {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());


    @Autowired
    private BrideDao brideDao;


    @PostMapping(value = "/registerBride")
    public String registerBride(@RequestParam(value = "email", required = false)String email,
                                @RequestParam(value = "name", required = false)String name,
                                @RequestParam(value = "phone", required = false)String phone,
                                @RequestParam(value = "weddingDate", required = false)String weddingDate,
                                @RequestParam(value = "howDidyouHearAbout", required = false)String howDidyouHearAbout,
                                @RequestParam(value = "numberOfGuest", required = false)String numberOfGuest){

        logger.info("=== Registering Bride ===");
        Bride bride = brideDao.findByEmailEqualsIgnoreCase(email);

        if (bride == null) {
            bride = new Bride();
            bride.setEmail(email);
            bride.setName(name);
            bride.setPhoneNumber(phone);
            bride.setWeddingDate(weddingDate);
            bride.setHowDidYouHear(howDidyouHearAbout);
            bride.setNumberOfGuest(numberOfGuest);
            bride.setCheckedIn(true);
            brideDao.save(bride);
            logger.info("Bride Not Found Saving Bride and checking in with email ["+email+"]");
            logger.info("=== End ===");
            return "brideSaved";

        } else {
            if (bride.isCheckedIn()) {
                logger.info("Bride Found and Already Checked in with email of ["+email+"]");
                logger.info("=== End ===");
                return "brideFoundAndCheckedIn";
            } else {
                logger.info("Bride Found with email of ["+email+"]");
                logger.info("=== End ===");
                return "brideFound";
            }
        }
    }

    @PostMapping(value = "/checkUser")
    public String checkUser(@RequestParam(value = "email", required = false) String email, @RequestParam(value = "numberOfGuest", required = false) String numberOfGuest){
        logger.info("=== Checking In Bride ===");
        logger.info("Checking for user [" + email + "], Number of Guest[" + numberOfGuest + "]");

        Bride bride = brideDao.findByEmailEqualsIgnoreCase(email);

        if (bride == null) {
            logger.info("Bride not found with email of ["+email+"]");
            logger.info("=== End ===");
            return "notFound";
        }
        else if (bride.isCheckedIn()) {
            logger.info("Bride already checked in.");
            logger.info("=== End ===");
            return "AlreadyCheckedIn";
        } else {
            bride.setCheckedIn(true);
            bride.setNumberOfGuest(numberOfGuest);
            brideDao.save(bride);
            logger.info("Bride is Created and Checked in:" + bride.toString());
            logger.info("=== End ===");
            return "checkedIn";
        }
    }

}
