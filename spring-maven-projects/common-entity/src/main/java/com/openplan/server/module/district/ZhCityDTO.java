package com.openplan.server.module.district;

import com.openplan.server.http.base.ResponseBodyDTO;

public class ZhCityDTO implements ResponseBodyDTO {

    private static final long serialVersionUID = 3831276138118580795L;

    private Integer cityNo;

    private String cityName;

    private Integer provinceNo;

    private String provinceName;

    private String firstLetter;

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

    public String getFirstLetter() {
        return firstLetter;
    }

    public void setFirstLetter(String firstLetter) {
        this.firstLetter = firstLetter;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

}