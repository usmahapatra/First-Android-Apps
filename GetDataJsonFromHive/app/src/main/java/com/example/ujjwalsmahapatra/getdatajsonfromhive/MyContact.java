package com.example.ujjwalsmahapatra.getdatajsonfromhive;

public class MyContact {
    private int sno;
    private String name;
    private String mobile;

    public MyContact(int sno, String name, String mobile) {
        this.sno = sno;
        this.name = name;
        this.mobile = mobile;
    }

    public int getSno() {
        return sno;
    }

    public void setSno(int sno) {
        this.sno = sno;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
}
