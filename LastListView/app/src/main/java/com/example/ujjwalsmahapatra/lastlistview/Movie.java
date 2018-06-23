package com.example.ujjwalsmahapatra.lastlistview;

public class Movie {
    private int sno;
    private String movieName,actor,actress;

    public int getSno() {
        return sno;
    }

    public void setSno(int sno) {
        this.sno = sno;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public String getActor() {
        return actor;
    }

    public void setActor(String actor) {
        this.actor = actor;
    }

    public String getActress() {
        return actress;
    }

    public void setActress(String actress) {
        this.actress = actress;
    }

    public Movie(int sno, String movieName, String actor, String actress) {
        this.sno = sno;
        this.movieName = movieName;
        this.actor = actor;
        this.actress = actress;
    }


}
