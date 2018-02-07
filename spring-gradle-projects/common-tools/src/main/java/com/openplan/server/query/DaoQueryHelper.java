package com.openplan.server.query;

import java.util.Map;

import com.openplan.server.constant.DaoConstant;

public class DaoQueryHelper {
    
    public static Integer getLimit(Map<String, Object> params) {
        Integer limit = toInteger(params.get(DaoConstant.LIMIT_KEY));
        if (limit == null) {
            limit = DaoConstant.LIMIT_MIN;
        }
        limit = limit > 0 ? limit : 1;
        return limit;
    }
    
    public static Integer getOffset(Map<String, Object> params) {
        Integer offset = toInteger(params.get(DaoConstant.OFFSET_KEY));
        if (offset == null) {
            offset = DaoConstant.OFFSET_DEFAULT;
        }
        return offset;
    }
    
    public static String getOrder(Map<String, Object> params) {
        String order = String.valueOf(params.get(DaoConstant.ORDER_KEY));
        return DaoQueryHelper.filterSql(order, DaoConstant.ORDER_DEFAULT);
    }
    
    public static String getSort(Map<String, Object> params) {
        String order = String.valueOf(params.get(DaoConstant.SORT_KEY));
        return DaoQueryHelper.filterSql(order, DaoConstant.SORT_ASC);
    }

    public static int resetOffset(int limit, int page) {
        if (page < 1) {
            return 0;
        }
        int offset = limit * (page - 1);
        return offset;
    }

    public static int resetPage(Map<String, Object> params) {
        if (params == null) {
            return resetPage(DaoConstant.LIMIT_MAX, DaoConstant.OFFSET_DEFAULT);
        }
        Integer limit = getLimit(params);

        Integer offset = getOffset(params);

        return resetPage(limit, offset);

    }

    public static int resetPage(int limit, int offset) {
        return (offset / limit) + 1;
    }

    public static String filterSql(String src, String def) {
        if (src == null || src.length() < 1) {
            return def;
        }
        if (src.matches("[A-Za-z0-9_]+") == false) {
            return def;
        }
        return src;
    }
    
    public static Integer toInteger(Object o) {
        if (o == null) {
            return null;
        }
        try {
            return Integer.valueOf(o.toString());
        } catch (NumberFormatException e) {
            return null;
        }
    }
}
