package me.pvpnik.emmber.managers;

import me.pvpnik.emmber.Emmber;
import me.pvpnik.emmber.api.entity.EntityStats;
import me.pvpnik.emmber.core.scoreboard.EmmberScoreboards;
import org.bukkit.Bukkit;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import javax.annotation.Nonnull;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class EntityStatsManager implements Listener {

    public ConcurrentHashMap<UUID, EntityStats> entitiesStats;

    public EntityStatsManager() {
        entitiesStats = new ConcurrentHashMap<UUID, EntityStats>();
        Bukkit.getPluginManager().registerEvents(this, Emmber.getInstance());
    }

    public EntityStats get(@Nonnull LivingEntity entity) {
        EntityStats entityStats = entitiesStats.get(entity.getUniqueId());
        if (entityStats == null) {
            entityStats = new EntityStats(entity);
            entitiesStats.put(entity.getUniqueId(), entityStats);
        }
        return entityStats;
    }

    public void update(UUID uuid) {
        if (entitiesStats.containsKey(uuid)) {
            entitiesStats.get(uuid).reload();
        }
    }

    public void update(Player player) {
        if (entitiesStats.containsKey(player.getUniqueId())) {
            entitiesStats.get(player.getUniqueId()).reload();
        } else {
            //player.sendMessage(get(player).toString());
        }
        Emmber.getInstance().emmberCoreManager.scoreboardManager.update(EmmberScoreboards.STATS, player);
    }

    public EntityStats remove(UUID uuid) {
        return entitiesStats.remove(uuid);
    }

    @EventHandler
    public void onEntityDeath(EntityDeathEvent e) {
        if (!(e.getEntity() instanceof Player)) {
            if (e.getEntity() instanceof LivingEntity) {
                entitiesStats.remove(e.getEntity().getUniqueId());
            }
        }
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        entitiesStats.remove(event.getPlayer().getUniqueId());
    }

}
