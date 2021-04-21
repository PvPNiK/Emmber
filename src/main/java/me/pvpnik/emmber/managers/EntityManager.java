package me.pvpnik.emmber.managers;

import me.pvpnik.emmber.Emmber;
import me.pvpnik.emmber.api.entity.EmmberMob;
import net.minecraft.server.v1_12_R1.PlayerInteractManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.PluginDisableEvent;
import org.bukkit.event.world.ChunkUnloadEvent;
import org.bukkit.material.Lever;

import javax.annotation.Nullable;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.HashSet;
import java.util.UUID;
import java.util.logging.Logger;

public class EntityManager implements Listener {

    private HashMap<UUID, EmmberMob> entities;

    public EntityManager() {
        entities = new HashMap<>();

        Bukkit.getPluginManager().registerEvents(this, Emmber.getInstance());
    }

    @Nullable
    public EmmberMob get(UUID uuid) {
        return entities.get(uuid);
    }

    /**
     * Reloads all stats for registered EmmberMobs
     */
    public void reloadEntityStats() {
        ((HashMap<UUID, EmmberMob>) entities.clone()).values().forEach(emmberMob -> {
            Emmber.getInstance().entityStatsManager.update(emmberMob.getUniqueID());
        });
    }

    /**
     * Removes the entity from hashmap + his physical form in game
     * @param uuid - entity's uuid
     * @return FALSE if there is no such uuid in {@link EntityManager#entities}
     */
    public boolean remove(UUID uuid) {
        EmmberMob entity = entities.remove(uuid);
        if (entity == null) {
            return false;
        }
        if (entity.getLivingEntity().isValid()) {
            entity.getLivingEntity().remove();
            Emmber.getInstance().entityStatsManager.remove(uuid);
        }
        return true;
    }

    public void remove() {
        for (UUID uuid : new HashSet<>(entities.keySet())) {
            if (Bukkit.getOfflinePlayer(uuid) == null) { // not player
                remove(uuid);
            }
        }
    }

    /**
     * Adds an entity to the hashmap
     * @param entity - the entity itself
     */
    public void add(EmmberMob entity) {
        remove(entity.getUniqueID());
        entities.putIfAbsent(entity.getUniqueID(), entity);
    }

    /**
     * Removes all entities when the plugin disabled
     */
    @EventHandler
    public void onDisable(PluginDisableEvent e) {
        for (UUID uuid : new HashSet<>(entities.keySet())) {
            remove(uuid);
        }
    }

    /**
     *  Deleting the entity in an unloaded chunk
     */
    @EventHandler
    public void onChunkUnload(ChunkUnloadEvent event) {
        for (Entity entity : event.getChunk().getEntities()) {
            Emmber.getInstance().spawnerManager.removeEntity(entity.getUniqueId());
            /*EmmberMob emmberMob = Emmber.getInstance().entityManager.get(entity.getUniqueId());
            if (emmberMob != null) {
                Emmber.getInstance().spawnerManager.removeEntity(emmberMob.getUniqueID());
            }*/
        }
    }

}
