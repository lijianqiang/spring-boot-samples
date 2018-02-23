package com.openplan.server.service;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.openplan.server.entity.model.ZhCityExtra;
import com.openplan.server.entity.model.ZhProvince;
import com.openplan.server.entity.model.ZhRegionExtra;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DistrictServiceTest {
    
    @Autowired
    private DistrictService districtService;

    @Test
    public void testGetCityById() {
        ZhCityExtra city = districtService.getCityById(1);
        assertEquals("testGetCityById", new Integer(1), city.getId());
    }

    @Test
    public void testListCityAll() {
        List<ZhCityExtra> citys = districtService.listCityAll();
        assertEquals("testListCityAll", new Integer(345), new Integer(citys.size()));
    }

    @Test
    public void testListProvinceAll() {
        List<ZhProvince> provinces = districtService.listProvinceAll();
        assertEquals("testListCityAll", new Integer(34), new Integer(provinces.size()));
    }

    @Test
    public void testListRegionAll() {
        List<ZhRegionExtra> regions = districtService.listRegionAll();
        assertEquals("testListCityAll", new Integer(3144), new Integer(regions.size()));
    }

}
