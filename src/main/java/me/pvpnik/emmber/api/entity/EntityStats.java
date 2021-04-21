package me.pvpnik.emmber.api.entity;

import com.google.common.util.concurrent.AtomicDouble;
import me.pvpnik.emmber.Emmber;
import me.pvpnik.emmber.api.events.armor.ArmorListener;
import me.pvpnik.emmber.api.item.ItemLoreUtils;
import me.pvpnik.emmber.core.NightMultiplier;
import org.bukkit.Bukkit;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.EntityEquipment;
import org.bukkit.inventory.ItemStack;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Raw entity stats,
 * Only what the entity wears and holding
 */
public class EntityStats {

    LivingEntity entity;

    /**
     * Entity's level
     */
    private AtomicInteger level;
    /**
     * Total dmg the entity has
     */
    private AtomicInteger dmg;
    /**
     * How much hp the entity has
     */
    private AtomicDouble hp;
    private double maxHp;
    /**
     * How much armor the entity has
     */
    private AtomicInteger armor;

    public EntityStats(LivingEntity entity) {
        this.entity = entity;
        level = new AtomicInteger(1);
        dmg = new AtomicInteger(0);
        hp = new AtomicDouble(entity.getHealth());
        armor = new AtomicInteger(0);
        maxHp = hp.get();
        reload();
    }

    public void reload() {
        level.set(1);
        dmg.set(0);
        hp.set(entity.getHealth());
        armor.set(0);
        if (entity.isValid()) {
            loadArmorContents();
            loadItemInMainHand();
            loadBaseStats();
        }
        maxHp = hp.get();

        if (!(entity instanceof Player)) { // mob, not a player
            if (Emmber.getInstance().getWorld().getTime() > 14000) { // night
                double multiplier = NightMultiplier.getCurrentNightMultiplier();
                dmg.set((int) Math.ceil(dmg.get() * multiplier));
                hp.set(Math.ceil(hp.get() * multiplier));
                armor.set((int) Math.ceil(armor.get() * multiplier));
            }
        }
        /*if (entity instanceof Player) {
            Player player = (Player) entity;
            player.sendMessage("level: " + level);
            player.sendMessage("dmg: " + dmg);
            player.sendMessage("hp: " + hp + ", max: " + maxHp);
            player.sendMessage("armor: " + armor);
        }*/
    }

    private void loadArmorContents() {
        for (ItemStack itemStack : getEquipment().getArmorContents()) {
            if (ArmorListener.isAirOrNull(itemStack)) {
                continue;
            }
            dmg.addAndGet(ItemLoreUtils.getDamage(itemStack));
            hp.addAndGet(ItemLoreUtils.getHp(itemStack));
            armor.addAndGet(ItemLoreUtils.getArmor(itemStack));
        }
    }

    private void loadItemInMainHand() {
        ItemStack itemStack = getEquipment().getItemInMainHand();
        if (ArmorListener.isAirOrNull(itemStack)) {
            return;
        }
        dmg.addAndGet(ItemLoreUtils.getDamage(itemStack));
        hp.addAndGet(ItemLoreUtils.getHp(itemStack));
        armor.addAndGet(ItemLoreUtils.getArmor(itemStack));
    }

    private void loadBaseStats() {
        if (entity instanceof Player) {

        } else {
            EmmberMob emmberMob = Emmber.getInstance().entityManager.get(entity.getUniqueId());
            if (emmberMob == null) {
                return;
            }
            level.set(emmberMob.getLevel());
            dmg.addAndGet(((emmberMob.getMaxDamage() - emmberMob.getMinDamage()) / 2) + emmberMob.getMinDamage());
            hp.addAndGet(emmberMob.getHp() - entity.getHealth()); // - entity.getHealth(), to reset line 42: hp = new AtomicDouble(entity.getHealth());
            armor.addAndGet((int) emmberMob.getArmor());
        }
    }

    private EntityEquipment getEquipment() {
        return entity.getEquipment();
    }

    public LivingEntity getEntity() {
        return entity;
    }

    public void setEntity(LivingEntity entity) {
        this.entity = entity;
    }

    public int getDmg() {
        return dmg.get();
    }

    public double getHp() {
        return hp.get();
    }

    public AtomicDouble getAtomicHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp.set(hp);
    }

    public int getArmor() {
        return armor.get();
    }

    public int getLevel() {
        return level.get();
    }

    public double getMaxHp() {
        return maxHp;
    }

    @Override
    public String toString() {
        return "EntityStats{" +
                "entity=" + entity +
                ", dmg=" + dmg +
                ", hp=" + hp +
                ", armor=" + armor +
                '}';
    }
}
