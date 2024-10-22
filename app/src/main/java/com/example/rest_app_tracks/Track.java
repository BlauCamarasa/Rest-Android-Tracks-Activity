package com.example.rest_app_tracks;

public class Track {
    String id;
    String title;
    String singer;
    static int lastId;

    public Track(String title, String singer) {
        this.title = title;
        this.singer = singer;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSinger() {
        return singer;
    }

    public void setSinger(String singer) {
        this.singer = singer;
    }

    public static int getLastId() {
        return lastId;
    }

    public static void setLastId(int lastId) {
        Track.lastId = lastId;
    }
}
