package me.pvpnik.emmber.core.song;

import org.bukkit.SoundCategory;
import org.bukkit.entity.Player;

public class Song {

    private String song;
    private int length;

    public Song(String song, int length) {
        setSong(song);
        setLength(length);
    }

    public void play(Player p) {
        p.playSound(p.getLocation(), song, SoundCategory.MUSIC, 100, 1);
    }

    public void unPlay(Player p) {
        p.stopSound(song);
    }

    public String getSong() {
        return song;
    }

    public void setSong(String song) {
        this.song = song;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

}
