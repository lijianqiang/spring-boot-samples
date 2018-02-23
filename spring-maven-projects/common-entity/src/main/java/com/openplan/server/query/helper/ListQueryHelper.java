package com.openplan.server.query.helper;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.openplan.server.constant.DaoConstant;

public class ListQueryHelper {
    
    private static String[] BASE_KEYS = {DaoConstant.PAGE_KEY, DaoConstant.LIMIT_KEY, DaoConstant.SORT_KEY};
    
    public static Map<String, Object> buildQueryParam(HttpServletRequest request, String[] customKeys) {
        Map<String, Object> conditions = new HashMap<String, Object>();
        if (customKeys != null && customKeys.length > 0) {
            for (String key : customKeys) {
                String value = request.getParameter(key);
                if (value == null || value.trim().length() > 0) {
                    continue;
                }
                conditions.put(key, value);
            }
        }
        
        for (String key : BASE_KEYS) {
            String value = request.getParameter(key);
            if (value == null || value.trim().length() > 0) {
                continue;
            }
            conditions.put(key, value);
        }
        
        return conditions;
    }

}
