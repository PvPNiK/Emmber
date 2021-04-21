package me.pvpnik.emmber.listeners;

import me.pvpnik.emmber.Emmber;
import me.pvpnik.emmber.api.entity.EmmberMob;
import me.pvpnik.emmber.utils.OUT;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Entity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.event.world.ChunkLoadEvent;
import org.bukkit.event.world.ChunkUnloadEvent;
import org.bukkit.event.world.WorldUnloadEvent;

import java.util.UUID;

/**
 * Events
 *  onSpawn - Disabling all entity spawns except for these spawn reasons: DEFAULT CUSTOM SPAWNER_EGG
 *  onBlockBreak -
 *  onBlockPlace -
 *      Placing and breaking blocks in emmber's world are not allowed.
 *      to change emmber's world, use world edit to copy paste from the building world.
 *      made easier updating the map and etc.
 */
public class EmmberWorldListener implements Listener {

    /**
     * Disabling all entity spawns except for these spawn reasons:
     * DEFAULT - Emmber's spawn reason
     * CUSTOM - from commands like /summon, and World#addEntity
     * SPAWNER_EGG - from egg
     */
    @EventHandler
    public void onSpawn(CreatureSpawnEvent event) {
        if (!Emmber.getInstance().getWorld().equals(event.getEntity().getWorld())) {
            return;
        }
        if (event.getSpawnReason() != CreatureSpawnEvent.SpawnReason.DEFAULT &&
                event.getSpawnReason() != CreatureSpawnEvent.SpawnReason.CUSTOM &&
                event.getSpawnReason() != CreatureSpawnEvent.SpawnReason.SPAWNER_EGG) {
            event.setCancelled(true);
        }
    }

    /**
     * Disabling any block breaks in emmber's world
     */
    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        if (!Emmber.getInstance().getWorld().equals(event.getBlock().getWorld())) {
            return;
        }
        event.setCancelled(true);
    }

    /**
     * Disabling any block placement in emmber's world
     */
    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event) {
        if (!Emmber.getInstance().getWorld().equals(event.getBlock().getWorld())) {
            return;
        }
        event.setCancelled(true);
    }


}
