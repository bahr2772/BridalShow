package com.totallysound.repositories;

import com.totallysound.entities.Bride;

import java.util.List;

/**
 * Created by bahr2772 on 3/8/17.
 */


public interface BrideDao {

	public List<Bride> findAllOrderByNameAsc();
	public void save(Bride bride);
	public Bride findByEmailEqualsIgnoreCase (String email);
	public List<Bride> findByNameContainingIgnoreCase (String name);
	public Bride findByNameEqualsIgnoreCaseOrEmailEqualsIgnoreCase (String name, String email);
	public List<Bride> findByEmailEquals (String email);
	public List<Bride>  findByEmailContainingIgnoreCase ( String email);

}
