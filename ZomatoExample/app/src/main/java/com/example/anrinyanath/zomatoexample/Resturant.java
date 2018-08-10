package com.example.anrinyanath.zomatoexample;

public class Resturant {
    private String name,address,locality,imageurl,lat,longitude,rating,offers;

    public Resturant(String name, String address, String locality, String imageurl, String lat, String longitude, String rating, String offers) {
        this.name = name;
        this.address = address;
        this.locality = locality;
        this.imageurl = imageurl;
        this.lat = lat;
        this.longitude = longitude;
        this.rating = rating;
        this.offers = offers;
    }

    public Resturant(String name, String address, String locality, String imageurl, String lat, String longitude, String rating) {
        this.name = name;
        this.address = address;
        this.locality = locality;
        this.imageurl = imageurl;

        this.lat = lat;
        this.longitude = longitude;
        this.rating = rating;
    }

    public String getOffers() {
        return offers;
    }

    public void setOffers(String offers) {
        this.offers = offers;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLocality() {
        return locality;
    }

    public void setLocality(String locality) {
        this.locality = locality;
    }

    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }
}
