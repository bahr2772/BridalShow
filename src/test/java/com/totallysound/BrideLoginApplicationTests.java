package com.totallysound;

import com.totallysound.entities.Bride;
import com.totallysound.repositories.BrideDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Random;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BrideLoginApplicationTests {



	@Autowired
	private BrideDao brideDao;

	@Test
	public void getRandomBride(){
		List<Bride> brideList = brideDao.findAllOrderByNameAsc ();
		Random ran = new Random ();
		int index = ran.nextInt (brideList.size ());
		System.out.print (brideList.get (index));

		assert (brideList.get (index).getEmail ().length () > 0);
	}

}
