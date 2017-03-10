package com.totallysound.repositories;

import com.totallysound.entities.Bride;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by bahr2772 on 3/7/17.
 */


public class BrideMapper implements RowMapper<Bride> {

	public Bride mapRow(ResultSet rs, int rowNum) throws SQLException {
		Bride bride = new Bride();
		bride.setId (rs.getInt ("id"));
		bride.setName (rs.getString ("name"));
		bride.setEmail (rs.getString ("email"));
		bride.setWeddingDate (rs.getString ("wedding_date"));
		bride.setPhoneNumber (rs.getString ("phone_number"));
		bride.setCheckedIn (rs.getBoolean ("checked_in"));
		bride.setPreRegistered (rs.getBoolean ("pre_registered"));
		bride.setHowDidYouHear (rs.getString ("how_did_you_hear"));
		bride.setNumberOfGuest (rs.getString ("number_of_guest"));

		return bride;
	}
}