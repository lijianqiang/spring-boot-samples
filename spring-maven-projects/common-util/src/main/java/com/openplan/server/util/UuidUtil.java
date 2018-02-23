package com.openplan.server.util;

import java.util.UUID;

public class UuidUtil {

    public static String getUuid() {
       UUID uuid = UUID.randomUUID(); 
        return uuid.toString().replaceAll("-", "");
    }
}
