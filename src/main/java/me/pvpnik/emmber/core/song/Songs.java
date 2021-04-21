package me.pvpnik.emmber.core.song;

public enum Songs {

    shok("shok", "market", 180);

    private String region;
    private String song;
    private int length;

    Songs(String region, String song, int length) {
        this.region = region;
        this.song = song;
        this.length = length;
    }

    public String getRegion() {
        return region;
    }

    public String getSong() {
        return song;
    }

    public int getLength() {
        return length;
    }
}
