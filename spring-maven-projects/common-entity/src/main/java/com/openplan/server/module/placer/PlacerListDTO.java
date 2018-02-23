package com.openplan.server.module.placer;

import java.util.List;
import java.util.Map;

import com.openplan.server.http.base.ResponseBodyDTO;

public class PlacerListDTO implements ResponseBodyDTO {

    private static final long serialVersionUID = 3027958343469494026L;

    private int total;

    private int page;

    private int size;

    private Map<String, Object> params;

    private List<PlacerDTO> rows;

    public PlacerListDTO() {
        this.total = 0;
        this.page = 1;
        this.size = 0;
    }
    
    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public Map<String, Object> getParams() {
        return params;
    }

    public void setParams(Map<String, Object> params) {
        this.params = params;
    }

    public List<PlacerDTO> getRows() {
        return rows;
    }

    public void setRows(List<PlacerDTO> rows) {
        this.rows = rows;
        if (rows != null) {
            this.size = rows.size();
        }
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
