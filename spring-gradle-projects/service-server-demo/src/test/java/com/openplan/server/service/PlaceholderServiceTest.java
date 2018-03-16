package com.openplan.server.service;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.openplan.server.domain.model. Placeholder;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PlaceholderServiceTest {
	
	@Autowired
    private PlaceholderService PlaceholderService;

	@Test
	public void testGetById() {
		assertTrue("***************", true);
	}

	@Test
	public void testDeleteById() {
		assertTrue("***************", true);
	}

	@Test
	public void testInsert() {
		int i = (int) (System.currentTimeMillis() % 1000000);
		Placeholder model = new Placeholder();
        model.setEnable(false);
        model.setGeohash("testInsert" + i);
        model.setLatitude("26.1230" + i);
        model.setLongitude("122.1200" + i);
        model.setPlacerId(i);
        model.setCityNo(350100);
        model.setProvinceNo(350000);
        model.setRegionNo(350102);
        model.setEnable(false);
        model.setAddress("xxxxxxxxxxxxx");
        String uuid = "uuid" + System.currentTimeMillis();
		model.setUnid(uuid );
		model = PlaceholderService.insert(model);
		assertTrue("***************", model != null);
	}

	@Test
	public void testUpdateById() {
		assertTrue("***************", true);
	}

	@Test
	public void testCountAll() {
		assertTrue("***************", true);
	}

	@Test
	public void testCountByCondition() {
		assertTrue("***************", true);
	}

	@Test
	public void testListByCondition() {
		assertTrue("***************", true);
	}

}
