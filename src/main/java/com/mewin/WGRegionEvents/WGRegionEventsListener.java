package com.mewin.WGRegionEvents;

import com.mewin.WGRegionEvents.events.*;
import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import com.sk89q.worldguard.protection.managers.RegionManager;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import me.pvpnik.emmber.Emmber;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.event.server.PluginEnableEvent;

/**
 * Source: https://dev.bukkit.org/projects/worldguard-region-events
 */
public class WGRegionEventsListener implements Listener {
    private WorldGuardPlugin wgPlugin;

    private Emmber plugin;

    private Map<Player, Set<ProtectedRegion>> playerRegions;

    public WGRegionEventsListener(Emmber plugin, WorldGuardPlugin wgPlugin) {
        this.plugin = plugin;
        this.wgPlugin = wgPlugin;
        this.playerRegions = new HashMap<Player, Set<ProtectedRegion>>();
        new RegionTestEvent(plugin);
    }

    @EventHandler
    public void onPlayerKick(PlayerKickEvent e) {
        Set<ProtectedRegion> regions = this.playerRegions.remove(e.getPlayer());
        if (regions != null)
            for (ProtectedRegion region : regions) {
                RegionLeaveEvent leaveEvent = new RegionLeaveEvent(region, e.getPlayer(), MovementWay.DISCONNECT, (PlayerEvent) e);
                RegionLeftEvent leftEvent = new RegionLeftEvent(region, e.getPlayer(), MovementWay.DISCONNECT, (PlayerEvent) e);
                this.plugin.getServer().getPluginManager().callEvent((Event) leaveEvent);
                this.plugin.getServer().getPluginManager().callEvent((Event) leftEvent);
            }
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent e) {
        Set<ProtectedRegion> regions = this.playerRegions.remove(e.getPlayer());
        if (regions != null)
            for (ProtectedRegion region : regions) {
                RegionLeaveEvent leaveEvent = new RegionLeaveEvent(region, e.getPlayer(), MovementWay.DISCONNECT, (PlayerEvent) e);
                RegionLeftEvent leftEvent = new RegionLeftEvent(region, e.getPlayer(), MovementWay.DISCONNECT, (PlayerEvent) e);
                this.plugin.getServer().getPluginManager().callEvent((Event) leaveEvent);
                this.plugin.getServer().getPluginManager().callEvent((Event) leftEvent);
            }
    }

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent e) {
        e.setCancelled(updateRegions(e.getPlayer(), MovementWay.MOVE, e.getTo(), e));
    }

    @EventHandler
    public void onPlayerTeleport(PlayerTeleportEvent e) {
        e.setCancelled(updateRegions(e.getPlayer(), MovementWay.TELEPORT, e.getTo(), e));
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        updateRegions(e.getPlayer(), MovementWay.SPAWN, e.getPlayer().getLocation(), e);
    }

    @EventHandler
    public void onPlayerRespawn(PlayerRespawnEvent e) {
        updateRegions(e.getPlayer(), MovementWay.SPAWN, e.getRespawnLocation(), e);
    }

    @EventHandler
    public void onPluginEnable(PluginEnableEvent e) {
        for (Player player : Bukkit.getOnlinePlayers()) {
            updateRegions(player, MovementWay.SERVER_ENABLE, player.getLocation(), null);
        }
    }

    private synchronized boolean updateRegions(final Player player, final MovementWay movement, Location to, final PlayerEvent event) {
        Set<ProtectedRegion> regions;
        if (this.playerRegions.get(player) == null) {
            regions = new HashSet<ProtectedRegion>();
        } else {
            regions = new HashSet<ProtectedRegion>(this.playerRegions.get(player));
        }
        Set<ProtectedRegion> oldRegions = new HashSet<ProtectedRegion>(regions);
        RegionManager rm = this.wgPlugin.getRegionManager(to.getWorld());
        if (rm == null)
            return false;
        HashSet<ProtectedRegion> appRegions = new HashSet<ProtectedRegion>(rm.getApplicableRegions(to).getRegions());
        ProtectedRegion globalRegion = rm.getRegion("__global__");
        if (globalRegion != null)
            appRegions.add(globalRegion);
        for (ProtectedRegion region : appRegions) {
            if (!regions.contains(region)) {
                RegionEnterEvent e = new RegionEnterEvent(region, player, movement, event);
                this.plugin.getServer().getPluginManager().callEvent((Event) e);
                if (e.isCancelled()) {
                    regions.clear();
                    regions.addAll(oldRegions);
                    return true;
                }
                Bukkit.getScheduler().runTaskLater(this.plugin, () -> {
                    RegionEnteredEvent regionEnteredEvent = new RegionEnteredEvent(region, player, movement, event);
                    WGRegionEventsListener.this.plugin.getServer().getPluginManager().callEvent(regionEnteredEvent);
                }, 1L);
                regions.add(region);
            }
        }
        Iterator<ProtectedRegion> itr = regions.iterator();
        while (itr.hasNext()) {
            final ProtectedRegion region = itr.next();
            if (!appRegions.contains(region)) {
                if (rm.getRegion(region.getId()) != region) {
                    itr.remove();
                    continue;
                }
                RegionLeaveEvent regionLeaveEvent = new RegionLeaveEvent(region, player, movement, event);
                this.plugin.getServer().getPluginManager().callEvent((Event) regionLeaveEvent);
                if (regionLeaveEvent.isCancelled()) {
                    regions.clear();
                    regions.addAll(oldRegions);
                    return true;
                }
                Bukkit.getScheduler().runTaskLater(this.plugin, () -> {
                    RegionLeftEvent regionLeftEvent = new RegionLeftEvent(region, player, movement, event);
                    WGRegionEventsListener.this.plugin.getServer().getPluginManager().callEvent(regionLeftEvent);
                }, 1L);
                itr.remove();
            }
        }
        this.playerRegions.put(player, regions);
        return false;
    }
}
