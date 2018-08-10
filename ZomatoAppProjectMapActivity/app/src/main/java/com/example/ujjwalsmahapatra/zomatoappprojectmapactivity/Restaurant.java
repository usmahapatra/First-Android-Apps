package com.example.ujjwalsmahapatra.zomatoappprojectmapactivity;

public class Restaurant {
    private String res_name, res_address, res_locality, res_imageurl, res_latitude, res_longitude, res_rating;

    public Restaurant(String res_name, String res_address, String res_locality, String res_imageurl, String res_latitude, String res_longitude, String res_rating) {
        this.res_name = res_name;
        this.res_address = res_address;
        this.res_locality = res_locality;
        this.res_imageurl = res_imageurl;
        this.res_latitude = res_latitude;
        this.res_longitude = res_longitude;
        this.res_rating = res_rating;
    }

    public String getRes_name() {
        return res_name;
    }

    public void setRes_name(String res_name) {
        this.res_name = res_name;
    }

    public String getRes_address() {
        return res_address;
    }

    public void setRes_address(String res_address) {
        this.res_address = res_address;
    }

    public String getRes_locality() {
        return res_locality;
    }

    public void setRes_locality(String res_locality) {
        this.res_locality = res_locality;
    }

    public String getRes_imageurl() {
        return res_imageurl;
    }

    public void setRes_imageurl(String res_imageurl) {
        this.res_imageurl = res_imageurl;
    }

    public String getRes_latitude() {
        return res_latitude;
    }

    public void setRes_latitude(String res_latitude) {
        this.res_latitude = res_latitude;
    }

    public String getRes_longitude() {
        return res_longitude;
    }

    public void setRes_longitude(String res_longitude) {
        this.res_longitude = res_longitude;
    }

    public String getRes_rating() {
        return res_rating;
    }

    public void setRes_rating(String res_rating) {
        this.res_rating = res_rating;
    }
}
