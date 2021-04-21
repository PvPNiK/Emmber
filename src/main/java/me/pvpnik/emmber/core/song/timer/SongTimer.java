package me.pvpnik.emmber.core.song.timer;

import me.pvpnik.emmber.Emmber;
import me.pvpnik.emmber.core.song.Song;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class SongTimer extends BukkitRunnable {
    private int length;
    private Player player;
    private Song song;

    public SongTimer(Player player, Song song) {
        this.player = player;
        this.song = song;
        this.length = song.getLength();
        this.runTaskTimer(Emmber.getInstance(), 0, 1L);
        song.play(player);
    }

    @Override
    public void run() {
        if (length <= 0) {
            song.play(player);
            length = song.getLength();
        }
        length--;
    }

    @Override
    public synchronized void cancel() throws IllegalStateException {
        song.unPlay(player);
        super.cancel();
    }
}
