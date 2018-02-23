package com.openplan.server.module.corporation;

import java.util.List;
import java.util.Map;

import com.openplan.server.http.base.ResponseBodyDTO;

public class CorporationListDTO implements ResponseBodyDTO {

    private static final long serialVersionUID = -1053004353801169100L;

    private int total;

    private int page;

    private int size;

    private Map<String, Object> params;

    private List<CorporationDTO> rows;

    public CorporationListDTO() {
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

    public List<CorporationDTO> getRows() {
        return rows;
    }

    public void setRows(List<CorporationDTO> rows) {
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
