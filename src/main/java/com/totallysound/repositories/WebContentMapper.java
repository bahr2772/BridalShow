package com.totallysound.repositories;


import com.totallysound.entities.WebContent;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by bahr2772 on 3/8/17.
 */


public class WebContentMapper implements RowMapper<WebContent> {

	public WebContent mapRow(ResultSet rs, int rowNum) throws SQLException {
		WebContent webContent = new WebContent ();
		webContent.setId (rs.getInt ("id"));
		webContent.setProperty (rs.getString ("property"));
		webContent.setValue (rs.getString ("value"));

		return webContent;
	}
}