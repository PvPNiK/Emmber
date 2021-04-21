package me.pvpnik.emmber.core.song;

import com.sk89q.worldguard.bukkit.WGBukkit;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;
import me.pvpnik.emmber.Emmber;
import me.pvpnik.emmber.core.song.timer.SongTimerManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.UUID;

public class SongManager {

    private SongTimerManager songTimerManager;
    // region, song
    private HashMap<String, Song> songs = new HashMap<String, Song>();

    public SongManager() {
        songTimerManager = new SongTimerManager();
        for (Songs songs : Songs.values()) {
            addSong(songs.getRegion(), new Song(songs.getSong(), songs.getLength()));
        }
        Bukkit.getPluginManager().registerEvents(new SongListener(this), Emmber.getInstance());
    }

    public void play(Player p, String region) {
        Song song = getSong(region);

        if (song == null) {
            return;
        }

        Song s = songs.get(region);
        UUID uuid = p.getUniqueId();

        songTimerManager.add(uuid, p, s);
    }

    public void unPlay(Player p, String region) {
        if (!songs.containsKey(region)) return;
        if (!songTimerManager.hasTimer(p.getUniqueId())) return;

        songTimerManager.remove(p.getUniqueId());
    }

    public void playAll(String region) {
        for (Player p : Bukkit.getOnlinePlayers()) {
            for (ProtectedRegion r : WGBukkit.getRegionManager(p.getWorld()).getApplicableRegions(p.getLocation())) {
                if (r.getId().equalsIgnoreCase(region)) {
                    play(p, region);
                }
            }
        }
    }

    public void unPlayAll(String region) {
        for (Player p : Bukkit.getOnlinePlayers()) {
            unPlay(p, region);
        }
    }

    public boolean addSong(String region, Song song) {
        if (songs.containsKey(region)) {
            return false;
        }

        songs.put(region, song);
        playAll(region);

        return true;
    }

    public boolean removeSong(String region) {
        if (!songs.containsKey(region)) {
            return false;
        }

        unPlayAll(region);
        songs.remove(region);

        return true;
    }

    public Song getSong(String region) {
        return songs.get(region);
    }

    public boolean regionHasSong(String region) {
        return songs.containsKey(region);
    }


    public SongTimerManager getSongTimerManager() {
        return songTimerManager;
    }
}
