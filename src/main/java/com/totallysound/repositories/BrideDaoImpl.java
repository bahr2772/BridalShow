package com.totallysound.repositories;

import com.totallysound.entities.Bride;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by bahr2772 on 12/5/16.
 */

@Repository
public class BrideDaoImpl  implements BrideDao{

    @SuppressWarnings ("SpringJavaAutowiringInspection")
    @Autowired
    private JdbcTemplate jdbcTemplate;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());


    public List<Bride> findAllOrderByNameAsc () {
        List<Bride> result = jdbcTemplate.query (
                "SELECT *  FROM bride ORDER BY Name ASC", new BrideMapper ());
        return result;

    }

    public void save (Bride bride)  {
        List<Bride> result = jdbcTemplate.query (
                "SELECT * FROM bride WHERE UPPER(email) = '" + bride.getEmail ().toUpperCase () + "'", new BrideMapper ());
         String sql;
        if (result.size () <= 0 || result == null)
            sql = "INSERT into bride(name, email, wedding_date, phone_number, checked_in, pre_registered, how_did_you_hear, number_of_guest) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        else
            sql = "UPDATE bride SET name = ?, email = ?, wedding_date = ?, phone_number = ?, checked_in = ?, pre_registered = ?, how_did_you_hear = ?, number_of_guest = ? " +
                    "WHERE id = " + result.get (0).getId ();
        jdbcTemplate.update (
                    sql,
                    new Object[]{bride.getName (), bride.getEmail (), bride.getWeddingDate (), bride.getPhoneNumber (), bride.isCheckedIn (), bride.isPreRegistered (),
                            bride.getHowDidYouHear (), bride.getNumberOfGuest ()});


    }

    public Bride findByEmailEqualsIgnoreCase (String email) {
        List<Bride> result = jdbcTemplate.query (
                "SELECT *  FROM bride WHERE email=" + email + " ORDER BY Name ASC", new BrideMapper ());
        if (result != null && result.size () > 0)
            return result.get (0);

        return null;

    }

    public List<Bride> findByNameContainingIgnoreCase (String name) {
        List<Bride> result = jdbcTemplate.query (
                "SELECT *  FROM bride WHERE UPPER(name)= '" + name.toUpperCase () + "' ORDER BY Name ASC", new BrideMapper ());

        return result;

    }

    public Bride findByNameEqualsIgnoreCaseOrEmailEqualsIgnoreCase (String name, String email) {
        if (name == null)
            name ="";
        if (email == null)
            email = "";

        List<Bride> result = jdbcTemplate.query (
                "SELECT *  FROM bride WHERE UPPER(name) ='" + name.toUpperCase () + "' OR UPPER (email) = '"+ email.toUpperCase () + "' ORDER BY Name ASC",
                new BrideMapper ());
             System.out.println (result);

        if (result != null && result.size () > 0)
        return result.get (0);

        return null;

    }


    public List<Bride> findByEmailEquals (String email) {
        List<Bride> result = jdbcTemplate.query (
                "SELECT *  FROM bride WHERE  name = '" + email + "' ORDER BY Name ASC", new BrideMapper ());

        return result;

    }


    public List<Bride>  findByEmailContainingIgnoreCase ( String email) {
        List<Bride> result = jdbcTemplate.query (
                "SELECT *  FROM bride WHERE UPPER (email) LIKE ('" + email.toUpperCase () + "') AND UPPER (email) = '"+ email.toUpperCase () + "' ORDER BY Name ASC",
                new BrideMapper ());

        return result;

    }

}
