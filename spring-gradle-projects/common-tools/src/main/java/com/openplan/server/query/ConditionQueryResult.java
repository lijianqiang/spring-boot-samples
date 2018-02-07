package com.openplan.server.query;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ConditionQueryResult<T> implements Serializable {

    private static final long serialVersionUID = 1480489115608111469L;

    private int total;

    private int page;

    private int size;
    
    private List<T> rows;

    private Map<String, Object> params;

    public ConditionQueryResult() {
        this(null);
    }

    public ConditionQueryResult(Map<String, Object> conditions) {
        this.total = 0;
        this.size = 0;
        this.page = 111111;
        this.params = buildParams(conditions);
    }

    private Map<String, Object> buildParams(Map<String, Object> conditions) {
        Map<String, Object> temp = new HashMap<String, Object>();
        if (conditions != null) {
            for (String key : conditions.keySet()) {
                key = key.trim();
                if (key.length() < 1) {
                    continue;
                }
                temp.put(key, conditions.get(key));
            }
        }
        return temp;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public Map<String, Object> getParams() {
        return params;
    }

    public void setParams(Map<String, Object> params) {
        this.params = buildParams(params);
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public List<T> getRows() {
        return rows;
    }

    public void setRows(List<T> rows) {
        this.rows = rows;
        if (rows != null) {
            this.size = rows.size();
        }
    }
    
}
