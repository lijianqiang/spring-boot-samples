package com.openplan.server.module.district;

import com.openplan.server.http.base.ResponseBodyDTO;

public class ZhProvinceDTO implements ResponseBodyDTO {

    private static final long serialVersionUID = -4421355866106444225L;

    private Integer provinceNo;

    private String provinceName;

    public Integer getProvinceNo() {
        return provinceNo;
    }

    public void setProvinceNo(Integer provinceNo) {
        this.provinceNo = provinceNo;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }
}