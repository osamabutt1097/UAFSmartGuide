package com.example.uafsmartguide;

public class LocationAttributes {

    public LocationAttributes() {
    }

    public LocationAttributes(String desc, String lat, String lon, String name, String type, String url) {
        this.desc = desc;
        this.lat = lat;
        this.lon = lon;
        this.name = name;
        this.type = type;
        this.url = url;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLon() {
        return lon;
    }

    public void setLon(String lon) {
        this.lon = lon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    String desc,lat,lon,name,type,url;


}
