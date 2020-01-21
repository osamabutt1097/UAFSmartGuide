package com.example.uafsmartguide;

public class PositionObject {
    String lat;
    String lon;
    String desc;
    String url;
    String name;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public PositionObject(String lat, String lon, String desc, String url, String name, String type) {
        this.lat = lat;
        this.lon = lon;
        this.desc = desc;
        this.url = url;
        this.name = name;
        this.type = type;
    }

    String type;

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

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public PositionObject(String lat, String lon, String desc, String name) {
        this.lat = lat;
        this.lon = lon;
        this.desc = desc;
        this.name = name;
    }

    public PositionObject(String lat, String lon, String desc, String url, String name) {
        this.lat = lat;
        this.lon = lon;
        this.desc = desc;
        this.url = url;
        this.name = name;
    }
}
