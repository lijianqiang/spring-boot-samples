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
    
    private static final String TAG = "abc1234";
    
    @Autowired
    private PlacerService placerService;
    
    @Test
    public void testDeleteById() {
    	int i = 1000;
    	Placer placer = new Placer();
        placer.setCityNo(350100);
        placer.setGeohash(TAG + i);
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
        
        boolean isDel = placerService.deleteById(placer);
        assertTrue("*************** delete failed", isDel);
    }
    
    @Test
    public void testInsert() {
        for (int i=10;i<15;i++) {
            Placer placer = new Placer();
            placer.setCityNo(350100);
            placer.setGeohash(TAG + i);
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
    	int i = 1001;
    	String maker = "maker" + System.currentTimeMillis();
    	Placer placer = new Placer();
        placer.setCityNo(350100);
        placer.setGeohash(TAG + i);
        placer.setIntro("苏宁广场的xxx" + i);
        placer.setLatitude("26.9234" + i);
        placer.setLevel(i);
        placer.setLongitude("126.123456");
        placer.setMarker(maker);
        placer.setName("苏宁广场"+i);
        placer.setPlaceType(i);
        placer.setProvinceNo(350000);
        placer.setRegionNo(350102);
        placer.setRented(false);
        placer.setXlength(2000 + i);
        placer.setYlength(1000 + i);
        placer = placerService.insert(placer);
        
        placer = placerService.getByMarker(maker);
        assertTrue("*************** testGetByMarker failed", placer != null);
    }

    @Test
    public void testUpdateById() {
    	int i = 1001;
    	Placer placer = new Placer();
        placer.setCityNo(350100);
        placer.setGeohash(TAG + i);
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
        
        placer = placerService.getById(placer.getId());
        placer.setIntro("xxxxxx");
        boolean isOk = placerService.updateById(placer);
        assertTrue("***** update failed", isOk);
    }

    @Test
    public void testListByCityNo() {
    	int i = 1002;
    	Placer placer = new Placer();
        placer.setCityNo(350100);
        placer.setGeohash(TAG + i);
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
        
        List<Placer> placers = placerService.listByCityNo(350100);
        assertTrue("***** testListByCityNo failed", placers.size() > 0);
    }

    @Test
    public void testListByLikeGeohash() {
    	int i = 1003;
    	Placer placer = new Placer();
        placer.setCityNo(350100);
        placer.setGeohash(TAG + i);
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
        
        List<Placer> placers = placerService.listByLikeGeohash(TAG, 6);
        assertTrue("***** testListByLikeGeohash failed", placers.size() > 0);
    }

}
