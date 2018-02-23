package com.openplan.server.github.geohash;

/**
 * Created by mike on 5/5/16.
 */
public class LocationBean {

    private String name = "";

    private double longitude = 0.0;
    private double latitude = 0.0;

    private String geoHash = "";

    private String address = "";

    public LocationBean(String name, double longitude, double latitude, String address) {
        this.name = name;
        this.longitude = longitude;
        this.latitude = latitude;
        this.address = address;

        this.geoHash = new GeoHash(longitude, latitude, 20).encodeHash();

        System.out.println(geoHash);

    }

    public String getGeoHash() {
        return geoHash;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setGeoHash(String geoHash) {
        this.geoHash = geoHash;
    }

}
