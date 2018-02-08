package com.openplan.server.service;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.openplan.server.domain.model.Placer;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PlacerServiceTest {
    
    private static final Logger LOG = LoggerFactory.getLogger(PlacerServiceTest.class);

    @Autowired
    private PlacerService placerService;
    
    @Test
    public void testDeleteById() {
        Placer placer = new Placer();
        placer.setId(3);
        boolean isDel = placerService.deleteById(placer);
        assertTrue("*************** delete failed", isDel);
    }
    
    @Test
    public void testInsert() {
        for (int i=10;i<15;i++) {
            Placer placer = new Placer();
            placer.setCityNo(350100);
            placer.setGeohash("abcdefaa" + i);
            placer.setIntro("苏宁广场的xxx" + i);
            placer.setLatitude("26.9234" + i);
            placer.setLevel(i);
            placer.setLongitude("126.123456");
            placer.setMarker("350102A000003");
            placer.setName("苏宁广场"+i);
            placer.setPlaceType(i);
            placer.setProvinceNo(350000);
            placer.setRegionNo(350102);
            placer.setRented(false);
            placer.setXlength(2000 + i);
            placer.setYlength(1000 + i);
            placer = placerService.insert(placer);
            LOG.info("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
            assertTrue("xxxxxxxxxxxx", placer != null);
        }
    }

    @Test
    public void testGetByMarker() {
        Placer placer = placerService.getByMarker("350102A000002");
        assertTrue("*************** testGetByMarker failed", placer != null);
    }

    @Test
    public void testUpdateById() {
        Placer placer = placerService.getById(2);
        placer.setIntro("xxxxxx");
        boolean isOk = placerService.updateById(placer);
        assertTrue("***** update failed", isOk);
    }

    @Test
    public void testListByCityNo() {
        List<Placer> placers = placerService.listByCityNo(350100);
        assertTrue("***** testListByCityNo failed", placers.size() == 2);
    }

    @Test
    public void testListByLikeGeohash() {
        List<Placer> placers = placerService.listByLikeGeohash("abcdefaaaa", 4);
        assertTrue("***** testListByLikeGeohash failed", placers.size() == 2);
    }

}
