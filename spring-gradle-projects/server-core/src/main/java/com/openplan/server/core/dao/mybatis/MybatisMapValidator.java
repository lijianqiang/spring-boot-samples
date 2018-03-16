package com.openplan.server.core.dao.mybatis;

import java.util.HashMap;
import java.util.Map;

import com.openplan.server.core.constant.DaoConstant;
import com.openplan.server.core.dao.DaoQueryHelper;

public class MybatisMapValidator {

    /**
     * offset 和 page，优先读取page
     * @param params
     * @return
     */
    public static Map<String, Object> prepare(Map<String, Object> params) {
        if (params == null) {
            params = new HashMap<String, Object>(6);
        }
        Integer limit = DaoQueryHelper.getLimit(params);
        params.put(DaoConstant.LIMIT_KEY, limit);
        
        Integer page = DaoQueryHelper.toInteger(params.get(DaoConstant.PAGE_KEY));
        if (page != null) {
            params.remove(DaoConstant.PAGE_KEY);
            params.put(DaoConstant.OFFSET_KEY, DaoQueryHelper.resetOffset(limit, page));
        } else {
            Integer offset = DaoQueryHelper.getOffset(params);
            params.put(DaoConstant.OFFSET_KEY, offset);
        }
        
        String order = (String) params.get(DaoConstant.ORDER_KEY);
        params.put(DaoConstant.ORDER_KEY, DaoQueryHelper.filterSql(order, DaoConstant.ORDER_DEFAULT));
        
        String sort = (String) params.get(DaoConstant.SORT_KEY);
        params.put(DaoConstant.SORT_KEY, DaoQueryHelper.filterSql(sort, DaoConstant.SORT_ASC));

        return params;
    }
    

    public static void main(String[] args) {
        Map<String, Object> params = null;
        params = prepare(params);

        System.out.println("str:" + DaoQueryHelper.filterSql("aaa dd", "xx"));
    }


    public static int resetPage(Map<String, Object> params) {
        return DaoQueryHelper.resetPage(params);
    }
}
