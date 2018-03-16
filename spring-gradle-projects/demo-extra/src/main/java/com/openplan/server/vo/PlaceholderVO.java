package com.openplan.server.vo;

import java.io.Serializable;

public class PlaceholderVO implements Serializable {
    public static final long serialVersionUID = 4510216306919051387L;

    public Integer id;

    public String unid;

    public String name;

    public Integer placer_id;
    
    public Integer province_no;

    public Integer city_no;

    public Integer region_no;

    public String longitude;

    public String latitude;

    public String geohash;
    
    public String address;

    public Boolean enable;

    public Integer type;

    public Long update_at;

}
