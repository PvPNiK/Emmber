package me.pvpnik.emmber.core.song.timer;

import me.pvpnik.emmber.core.song.Song;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class SongTimerManager {

    private HashMap<UUID, SongTimer> Timer = new HashMap<UUID, SongTimer>();

    public SongTimerManager() {

    }
    public void add(UUID uuid, Player p, Song s) {
        if (hasTimer(uuid)) {
            remove(uuid);
            Timer.put(uuid, new SongTimer(p, s));
        } else
            Timer.put(uuid, new SongTimer(p, s));
    }

    public boolean hasTimer(UUID uuid) {
        if (Timer.containsKey(uuid))
            return true;
        return false;
    }

    public boolean remove(UUID uuid) {
        if (hasTimer(uuid)) {
            Timer.get(uuid).cancel();
            Timer.remove(uuid);
            return true;
        }
        return false;
    }

    public void removeAll() {
        if (Timer.isEmpty())
            return;

        if (Timer.keySet().isEmpty())
            return;

        Set<UUID> timerKeys = new HashSet<>(Timer.keySet());
        for (UUID uuid : timerKeys)
            remove(uuid);
    }

}
