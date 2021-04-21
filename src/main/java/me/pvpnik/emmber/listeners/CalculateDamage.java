package me.pvpnik.emmber.listeners;

import me.pvpnik.emmber.Emmber;
import me.pvpnik.emmber.api.entity.EntityStats;
import me.pvpnik.emmber.api.holograms.temporary.HologramDamage;
import me.pvpnik.emmber.core.NightMultiplier;
import me.pvpnik.emmber.utils.ActionBar;
import me.pvpnik.emmber.utils.OUT;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;

import java.util.Random;

public class CalculateDamage implements Listener {

    /*@EventHandler
    public void dm(EntityDamageByEntityEvent e) {
        Entity entity = e.getEntity();
        Entity player = e.getDamager();

        if (e.getEntity() instanceof EmmberSkeleton) {
            player.sendMessage("e.getEntity() instanceof EmmberSkeleton");
        }
        Player player1  = (Player) player;

        EntityStats entityStats = Emmber.getInstance().entityStatsManager.get((LivingEntity) entity);
        EntityStats playerStats = Emmber.getInstance().entityStatsManager.get(player1);

        ((LivingEntity) entity).damage(3);
        if (Emmber.getInstance().entityManager.get(entity.getUniqueId()) != null) {
            ActionBar.sendActionbar(player1, "HP: " + ((LivingEntity) entity).getHealth() + "/" + Emmber.getInstance().entityManager.get(entity.getUniqueId()).getHp());
        }
    }*/

    @EventHandler
    public void playerHitEntity(EntityDamageByEntityEvent e) {
        if (!(e.getDamager() instanceof Player)) {
            return;
        }
        if (!(e.getEntity() instanceof LivingEntity) && e.getEntity() instanceof Player) {
            return;
        }
        LivingEntity entity = (LivingEntity) e.getEntity();
        if (Emmber.getInstance().entityManager.get(entity.getUniqueId()) == null) { // not EmmberEntity
            return;
        }
        if (e.getEntity() instanceof ArmorStand) {
            return;
        }
        Player player  = (Player) e.getDamager();

        e.setDamage(0);
        double damage = calculateDamage(getEntityStats(player), getEntityStats(entity), null);
        //OUT.toConsole("dmg: " + damage);
        if (damage == 0) {
            new HologramDamage(player, (int) damage, entity.getLocation());
            return;
        }
        entity.damage(damage);

        new HologramDamage(player, (int) damage, entity.getLocation());

        if (Emmber.getInstance().entityManager.get(entity.getUniqueId()) != null) {
            ActionBar.sendActionbar(player, "HP: " + (int) entity.getHealth() + "/" + Emmber.getInstance().entityManager.get(entity.getUniqueId()).getHp());
        } else { // should no be shown
            ActionBar.sendActionbar(player, "HP: " + (int) entity.getHealth() + "/" + entity.getMaxHealth());
        }
    }

    /**
     *
     * @param att
     * @param def
     * @param damageCause
     * @return
     */
    public double calculateDamage(EntityStats att, EntityStats def, EntityDamageEvent.DamageCause damageCause) {
        if (att.getEntity() instanceof Player && def.getEntity() instanceof Player) {
            if (damageCause == EntityDamageEvent.DamageCause.PROJECTILE) {
                return def.getMaxHp() * randomDouble(0.05, 0.08);
            } else {
                return def.getMaxHp() * randomDouble(0.08, 0.12);
            }
        }
        Random random = new Random();

        int missChance = def.getLevel() - att.getLevel();
        if (missChance > 0) { // entity's level is higher
            missChance *= 10;
            int miss = 1 + random.nextInt(100);
            if (missChance >= miss) {
                return 0; // missed
            }
        }

        double min = att.getDmg() * 0.8;
        double max = att.getDmg() * 1.8;
        double damage = min - 1 + random.nextInt((int) (max - min + 1));
        //OUT.toConsole("player dmg: " + damage + ", d: " + att.getDmg());
        double defense = def.getArmor();

        /*if (random.nextInt(100) + 1 <= player's critical) {
        damage *= (1 + (double) (critical / 100));
        }*/

        double finalDamage = (damage - defense) / NightMultiplier.getCurrentNightMultiplier();
        return finalDamage < 0 ? 0 : finalDamage;
    }

    private double randomDouble(double min, double max) {
        return Math.random() < 0.5D ? (1.0D - Math.random()) * (max - min) + min : Math.random() * (max - min) + min;
    }

    private EntityStats getEntityStats(LivingEntity entity) {
        return Emmber.getInstance().entityStatsManager.get(entity);
    }

}
