package me.pvpnik.emmber.api.spawner;

import me.pvpnik.emmber.Emmber;
import me.pvpnik.emmber.ServerClock;
import me.pvpnik.emmber.utils.OUT;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.NPC;
import org.bukkit.entity.Player;

import javax.annotation.Nullable;
import java.util.*;

public abstract class EntitySpawner {

    /**
     * A way to identify each spawner
     */
    private String id;
    /**
     * how much seconds to wait between spawns
     */
    private int cooldown = 6;
    /**
     * max entities for this spawn
     */
    private int maxspawn = 5;
    private List<Location> locations = new ArrayList<>();
    private List<UUID> entities = new ArrayList<>();
    private boolean spawning = false;

    public EntitySpawner(String id) {
        this.id = id;
        reloadSeverClockAction();
    }

    /**
     * Executed when the spawner wants to spawn the mob
     * @return LivingEntity so EntitySpawner.java will have entity's uuid for {@link EntitySpawner#entities}
     */
    public abstract LivingEntity spawnEntity(Location location);

    @Nullable
    public Location getRandomLocation() {
        if (locations.isEmpty()) {
            return null;
        }
        int locPositionInList = new Random().nextInt(locations.size());
        Location location = locations.get(locPositionInList);
        return location;
    }

    /**
     * Only public for overriding the method, if needed.
     */
    public void reloadSeverClockAction() {
        ServerClock serverClock = Emmber.getInstance().serverClock;
        serverClock.deleteAction(id);
        serverClock.addAction(id, cooldown, o -> {
            if (spawning && entities.size() < maxspawn) {
                Location location = getRandomLocation();
                if (location != null) {
                    if (location.getChunk().isLoaded()) {
                        if (hasPlayersNear(location)) {
                            entities.add(spawnEntity(location).getUniqueId());
                        }
                    }
                }
            }
        });
        setSpawning(false);
    }

    protected boolean hasPlayersNear(Location location) {
        int r = 25; // search radius
        for (Entity en : location.getWorld().getNearbyEntities(location, r, r, r)) {
            if (Bukkit.getPlayer(en.getUniqueId()) != null) { // cannot use en instaceof Player, bc of Citizens
                return true;
            }
        }
        return false;
    }

    public void removeEntity(UUID uuid) {
        entities.remove(uuid);
        Emmber.getInstance().entityManager.remove(uuid);
    }

    public void removeEntities() {
        List<UUID> entities = new ArrayList<>(this.entities);
        this.entities.clear();
        for (UUID uuid : entities) {
            Emmber.getInstance().entityManager.remove(uuid);
        }
    }

    public String getId() {
        return id;
    }

    public int getCooldown() {
        return cooldown;
    }

    public void setCooldown(int cooldown) {
        this.cooldown = cooldown;
        reloadSeverClockAction();
    }

    public int getMaxspawn() {
        return maxspawn;
    }

    public void setMaxspawn(int maxspawn) {
        this.maxspawn = maxspawn;
    }

    public List<Location> getLocations() {
        return locations;
    }

    public void addLocation(Location loc) {
        if (!locationExist(loc)) {
            locations.add(loc);
        }
    }

    public void removeLocation(Location loc) {
        for (Location l : new ArrayList<>(locations)) {
            if (!l.equals(loc)) {
                continue;
            }
            locations.remove(l);
            break;
        }
    }

    public boolean locationExist(Location loc) {
        for (Location l : locations) {
            if (l.equals(loc)) {
                return true;
            }
        }
        return false;
    }

    public void setLocations(List<Location> locations) {
        this.locations = locations;
    }

    public List<UUID> getEntities() {
        return entities;
    }

    public boolean isSpawning() {
        return spawning;
    }

    public void setSpawning(boolean spawning) {
        this.spawning = spawning;
        ServerClock serverClock = Emmber.getInstance().serverClock;
        if (spawning) {
            serverClock.startAction(id);
        } else {
            serverClock.stopAction(id);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EntitySpawner that = (EntitySpawner) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
