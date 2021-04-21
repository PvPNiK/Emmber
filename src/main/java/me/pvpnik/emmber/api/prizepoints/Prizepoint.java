package me.pvpnik.emmber.api.prizepoints;

import java.util.HashMap;
import java.util.UUID;

import me.pvpnik.emmber.Emmber;
import me.pvpnik.emmber.utils.OUT;
import org.bukkit.Location;
import org.bukkit.Material;

public class Prizepoint {

    private String name;
    private Location loc;
    private int prize;
    private int cooldown;
    private HashMap<UUID, Long> players = new HashMap<>();

    public Prizepoint(String name, Location loc, int prize, int cooldown) {
        this.name = Emmber.getInstance().localFileManager.messages.getMessage("prizepoints.names." + name);
        this.loc = loc;
        this.prize = prize;
        this.cooldown = cooldown;
        if (loc.getChunk().isLoaded()) {
            loc.getChunk().load();
        }
    }

    /**
     * Check's if a location is inside prizepoint's radius
     * @param location - the location inside prizepoint's radius
     * @return false if there is no barrier
     */
    public boolean isInside(Location location) {
        // no barrier at Y255
        if (!location.clone().add(0, (255-location.getBlockY()) , 0).getBlock().getType().equals(Material.BARRIER)) {
            return false;
        }
        for (int x = -getRadius(); x <= getRadius(); x++) {
            for (int z = -getRadius(); z <= getRadius(); z++) {
                Location loc = getLocation().clone().add(x, 0, z);
                if (location.getBlockZ() == loc.getBlockZ() && location.getBlockX() == loc.getBlockX()
                && ((location.getBlockY() > (loc.getBlockY() - 3)) && (location.getBlockY() < (loc.getBlockY() + 3))) ) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Sets barriers at Y255
     * Needed for player detection and lag reduce
     */
    public void build() {
        setBlocks(Material.BARRIER);
    }

    /**
     * Removes the barriers at Y255
     */
    public void destroy() {
        setBlocks(Material.AIR);
    }

    private void setBlocks(Material material) {
        for (int x = -getRadius(); x <= getRadius(); x++) {
            for (int z = -getRadius(); z <= getRadius(); z++) {
                getLocation().getWorld().getBlockAt(getLocation().clone().add(x, (255-getLocation().getY()), z)).setType(material);
            }
        }
    }

    /**
     * Prizepoint's radius
     * @return 2
     */
    public int getRadius() {
        return 2;
    }

    public String getName() {
        return name;
    }

    public Location getLocation() {
        return loc;
    }

    public int getPrize() {
        return prize;
    }

    public int getCooldown() {
        return cooldown;
    }

    public HashMap<UUID, Long> getPlayers() {
        return players;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLocation(Location loc) {
        this.loc = loc;
    }

    public void setPrize(int prize) {
        this.prize = prize;
    }

    public void setCooldown(int cooldown) {
        this.cooldown = cooldown;
    }

    public void setPlayers(HashMap<UUID, Long> players) {
        this.players = players;
    }
}
