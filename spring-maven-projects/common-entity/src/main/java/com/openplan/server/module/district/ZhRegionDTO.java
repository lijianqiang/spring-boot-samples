package com.openplan.server.module.district;

import com.openplan.server.http.base.ResponseBodyDTO;

public class ZhRegionDTO implements ResponseBodyDTO {

    private static final long serialVersionUID = -7937765457929988242L;

    private Integer regionNo;

    private String regionName;

    private Integer cityNo;

    private String cityName;

    private Integer provinceNo;

    public Integer getRegionNo() {
        return regionNo;
    }

    public void setRegionNo(Integer regionNo) {
        this.regionNo = regionNo;
    }

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }

    public Integer getCityNo() {
        return cityNo;
    }

    public void setCityNo(Integer cityNo) {
        this.cityNo = cityNo;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public Integer getProvinceNo() {
        return provinceNo;
    }

    public void setProvinceNo(Integer provinceNo) {
        this.provinceNo = provinceNo;
    }

}