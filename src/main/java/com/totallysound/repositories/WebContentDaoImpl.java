package com.totallysound.repositories;

import com.totallysound.entities.WebContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by bahr2772 on 3/7/17.
 */

@Repository
public class WebContentDaoImpl implements WebContentDao {

    @SuppressWarnings ("SpringJavaAutowiringInspection")
    @Autowired
    private JdbcTemplate jdbcTemplate;


    public List<WebContent> findAll () {
        List<WebContent> webContentList = jdbcTemplate.query (
                "SELECT id, property, value FROM web_content",new WebContentMapper ());
        return webContentList;
    }

}