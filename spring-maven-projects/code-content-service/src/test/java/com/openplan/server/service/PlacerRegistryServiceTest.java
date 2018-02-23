package com.openplan.server.service;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.openplan.server.entity.model.PlacerRegistry;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PlacerRegistryServiceTest {
    
    @Autowired
    private PlacerRegistryService placerRegistryService;

    @Test
    public void testInsert() {
        PlacerRegistry model = new PlacerRegistry();
        model.setCorporationId(1);
        model.setPlacerId(12);
        model.setUnid("xxxxxxxxxxxxxxxxxxx");
        model = placerRegistryService.insert(model);
        
        assertTrue("*************** insert failed", (model != null));
    }

}
