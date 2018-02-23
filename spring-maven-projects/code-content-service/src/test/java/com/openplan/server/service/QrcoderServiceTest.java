package com.openplan.server.service;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.openplan.server.entity.model.Qrcoder;
import com.openplan.server.util.UuidUtil;

@RunWith(SpringRunner.class)
@SpringBootTest
public class QrcoderServiceTest {
    
    @Autowired
    private QrcoderService qrcodeService;

    @Test
    public void testGetById() {
        Qrcoder qrcode = qrcodeService.getById(1);
        assertEquals("testGetById", new Integer(1), qrcode.getId());
    }

    @Test
    public void testDeleteById() {
        Qrcoder qrcode = new Qrcoder();
        qrcode.setId(1);
        boolean isDel = qrcodeService.deleteById(qrcode);
        assertEquals("testDeleteById", new Integer(1), isDel ? new Integer(1) : new Integer(0));
        
    }

    @Test
    public void testInsert() {
        for (int i=0;i<30;i++) {
            Qrcoder qrcode = new Qrcoder();
            qrcode.setEnable(false);
            qrcode.setGeohash("abcaa" + i);
            qrcode.setLatitude("26.1230" + i);
            qrcode.setLongitude("122.1200" + i);
            qrcode.setName("测试qrcode" + i);
            qrcode.setPlacerId(i);
            String uuid = UuidUtil.getUuid();
            qrcode.setUnid(uuid);
            qrcode = qrcodeService.insert(qrcode);
            assertEquals("testInsert", uuid, qrcode.getUnid());
        }
    }

    @Test
    public void testUpdateById() {
        Qrcoder qrcode = qrcodeService.getById(1);
        String name = "newname";
        qrcode.setName(name);
        boolean isUpd = qrcodeService.updateById(qrcode);
        assertEquals("testUpdateById1", new Integer(1), isUpd ? new Integer(1) : new Integer(0));
        qrcode = qrcodeService.getById(1);
        assertEquals("testUpdateById2", name, qrcode.getName());
    }

    @Test
    public void testCountAll() {
        int num = qrcodeService.countAll();
        System.out.println("testCountAll:" + num);
    }
    
    /*
    public static void main(String[] args) {
        Qrcoder qrcode = new Qrcoder();
        qrcode.setEnable(false);
        qrcode.setGeohash("asdfasfdasdf");
        qrcode.setLatitude("26.123");
        qrcode.setLongitude("122.123123");
        qrcode.setName("测试qrcode");
        qrcode.setPlacerId(1);
        String uuid = UuidUtil.getUuid();
        qrcode.setUnid(uuid);
        Gson gson = new Gson();
        System.out.println(gson.toJson(qrcode));
    }
    */

}
