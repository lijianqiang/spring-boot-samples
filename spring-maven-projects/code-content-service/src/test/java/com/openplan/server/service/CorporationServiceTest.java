package com.openplan.server.service;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.openplan.server.entity.model.Corporation;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CorporationServiceTest {
    
    @Autowired
    private CorporationService corporationService;

    @Test
    public void testInsert() {
        Corporation corporation = new Corporation();
        corporation.setAddress("address");
        corporation.setAvatarUrl("https://www.baidu.com");
        corporation.setCityName("福州");
        corporation.setCityNo(350100);
        corporation.setCompanyType(1);
        corporation.setGeohash("xxxxxxxxxxxx");
        corporation.setIntro("intro");
        corporation.setLatitude("26.111222");
        corporation.setLevel(1);
        corporation.setLongitude("126.234999");
        corporation.setName("宝龙城市广场");
        corporation.setProvinceName("福建省");
        corporation.setProvinceNo(350000);
        corporation.setRegionName("台江区");
        corporation.setRegionNo(350103);
        corporation.setUnid("xxxxxxxxxxxxxxxxxxxxxxx");
        corporation = corporationService.insert(corporation);
        
        assertTrue("*************** insert failed", (corporation != null));
    }

}
