package com.totallysound.repositories;

import com.totallysound.entities.Bride;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by bahr2772 on 12/5/16.
 */

@Transactional
public interface BrideDao extends CrudRepository<Bride, Long>{

    public Bride findByEmailEqualsIgnoreCase(String email);
    public List<Bride> findByNameContainingIgnoreCase(String name);
    public Bride findByNameEqualsIgnoreCaseOrEmailEqualsIgnoreCase(String name, String email);
    public Bride findByEmailEquals(String email);
    public List<Bride> findByEmailContainingIgnoreCase(String email);
}
