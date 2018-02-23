package com.openplan.server.module.district;

import java.util.ArrayList;
import java.util.List;

import com.openplan.server.http.base.ResponseBodyDTO;

public class ZhProvinceListDTO implements ResponseBodyDTO {

    private static final long serialVersionUID = 3934131856401875682L;

    private int size;

    private List<ZhProvinceDTO> rows;
    
    public ZhProvinceListDTO() {
        this(new ArrayList<ZhProvinceDTO>());
    }
    
    public ZhProvinceListDTO(List<ZhProvinceDTO> rows) {
        this.rows = rows;
        this.size = rows != null ? rows.size() : 0;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public List<ZhProvinceDTO> getRows() {
        return rows;
    }

    public void setRows(List<ZhProvinceDTO> rows) {
        this.rows = rows;
    }

}
