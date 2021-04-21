package me.pvpnik.emmber.listeners;

import me.pvpnik.emmber.Emmber;
import me.pvpnik.emmber.core.scoreboard.EmmberScoreboards;
import me.pvpnik.emmber.items.Shield;
import me.pvpnik.emmber.items.food.Apple;
import me.pvpnik.emmber.items.food.CookedBeef;
import me.pvpnik.emmber.items.food.Foods;
import me.pvpnik.emmber.items.startingItems.*;
import me.pvpnik.emmber.utils.OUT;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.ExperienceOrb;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;

/**
 * Events:
 *   onFirtJoin(PlayerJoinEvent) - setting up stuff for first joined
 */
public class PlayerListener implements Listener {

    /**
     * Teleports the player to his saved checkpoint
     */
    @EventHandler (priority = EventPriority.MONITOR)
    public void onJoinTpToCheckPoint(PlayerJoinEvent e) {
        Emmber.getInstance().playerManager.get(e.getPlayer().getUniqueId()).teleportToCheckPoint();
    }

    @EventHandler
    public void onSlotChange(PlayerItemHeldEvent event) {
        Player player = event.getPlayer();
        new BukkitRunnable() {
            @Override
            public void run() {
                Emmber.getInstance().entityStatsManager.update(event.getPlayer());
            }
        }.runTaskLater(Emmber.getInstance(), 1);
    }

    @EventHandler
    public void onFirtJoin(PlayerJoinEvent e) {
        Player p = e.getPlayer();

        //if (!p.hasPlayedBefore()) {
            p.getInventory().setHelmet(new StartingHelmet());
            p.getInventory().setChestplate(new StartingChestplate());
            p.getInventory().setLeggings(new StartingLeggings());
            p.getInventory().setBoots(new StartingBoots());

            p.getInventory().setItem(0, new StartingSword());
            p.getInventory().setItem(1, new CookedBeef());
            p.getInventory().setItem(2, new Apple());
            p.getInventory().setItemInOffHand(new Shield((byte) 0));

            p.updateInventory();
        //}
    }

    @EventHandler
    public void onExpSpawn(EntitySpawnEvent event) {
        if (!(event.getEntity() instanceof ExperienceOrb)) {
            return;
        }
        event.setCancelled(true);
    }

    @EventHandler
    public void onEntityDeath(EntityDeathEvent e) {
        if (!(e.getEntity() instanceof Player)) {
            e.setDroppedExp(0);
            if (e.getEntity() instanceof LivingEntity) {
                e.getDrops().clear();
            }
        }
    }

    @EventHandler
    public void onEatingFood(PlayerItemConsumeEvent e) {
        Player p = e.getPlayer();
        try {
            Foods foods = Foods.valueOf(e.getItem().getType().name());
            double healthToGive = foods.getHealthToGive();
            p.setHealth(p.getHealth() + healthToGive > 20 ? 20 : p.getHealth() + healthToGive);
        } catch (Exception e1) {
            return;
        }
    }

}
