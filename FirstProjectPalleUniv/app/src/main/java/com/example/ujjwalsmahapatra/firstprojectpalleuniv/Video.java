package com.example.ujjwalsmahapatra.firstprojectpalleuniv;

public class Video {
    private String video_id,vid_duration,vid_name,serial_no;

    public Video(String video_id, String vid_duration, String vid_name, String serial_no) {
        this.video_id = video_id;
        this.vid_duration = vid_duration;
        this.vid_name = vid_name;
        this.serial_no = serial_no;
    }

    public void setVideo_id(String video_id) {
        this.video_id = video_id;
    }

    public void setVid_duration(String vid_duration) {
        this.vid_duration = vid_duration;
    }

    public void setVid_name(String vid_name) {
        this.vid_name = vid_name;
    }

    public void setSerial_no(String serial_no) {
        this.serial_no = serial_no;
    }



    public String getVideo_id() {
        return video_id;
    }

    public String getVid_duration() {
        return vid_duration;
    }

    public String getVid_name() {
        return vid_name;
    }

    public String getSerial_no() {
        return serial_no;
    }
}
