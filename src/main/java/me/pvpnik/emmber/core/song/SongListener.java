package me.pvpnik.emmber.core.song;

import com.mewin.WGRegionEvents.events.RegionEnterEvent;
import com.mewin.WGRegionEvents.events.RegionLeaveEvent;
import com.sk89q.worldguard.bukkit.WGBukkit;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;
import me.pvpnik.emmber.Emmber;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.scheduler.BukkitRunnable;

public class SongListener implements Listener {
    
    private SongManager songManager;
    
    public SongListener(SongManager songManager) {
        this.songManager = songManager;
    }
    
    @EventHandler
    public void onRegionEnter(RegionEnterEvent e) {
        if (!songManager.regionHasSong(e.getRegion().getId()))
            return;

        songManager.play(e.getPlayer(), e.getRegion().getId());
    }

    @EventHandler
    public void onRegionLeave(RegionLeaveEvent e) {
        if (!songManager.regionHasSong(e.getRegion().getId()))
            return;

        Player p = e.getPlayer();

        new BukkitRunnable() {
            @Override
            public void run() {

                for (ProtectedRegion r : WGBukkit.getRegionManager(p.getWorld()).getApplicableRegions(p.getLocation())) {

                    if (!songManager.regionHasSong(r.getId())) {
                        continue;
                    }

                    songManager.play(p, r.getId());
                    return;
                }

            }
        }.runTaskLater(Emmber.getInstance(), 1);
    }

    @EventHandler
    public void onServerQuit(PlayerQuitEvent e) {
        if (songManager.getSongTimerManager().hasTimer(e.getPlayer().getUniqueId())) {
            songManager.getSongTimerManager().remove(e.getPlayer().getUniqueId());
        }
    }
    
}
