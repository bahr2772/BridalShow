package com.totallysound;

import com.totallysound.entities.Bride;
import com.totallysound.repositories.BrideDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BrideLoginApplicationTests {

	@Test
	public void contextLoads() {
	}

	@Autowired
	private BrideDao brideDao;

	@Test
	public void brideExsits_failIfNotFound(){
		String email = "bahr2772@gmail.com";
		Bride bride = brideDao.findByEmailEquals(email);
		if (bride != null)
			assert true;
		else
			assert false;
	}

}
