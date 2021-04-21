package me.pvpnik.emmber.api.entity.entities;

import me.pvpnik.emmber.Emmber;
import me.pvpnik.emmber.api.entity.EmmberMob;
import me.pvpnik.emmber.api.entity.EntityStats;
import me.pvpnik.emmber.api.jobs.EmmberJob;
import me.pvpnik.emmber.utils.HebrewUtil;
import me.pvpnik.emmber.utils.NMSUtils;
import me.pvpnik.emmber.utils.OUT;
import net.minecraft.server.v1_12_R1.*;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_12_R1.CraftWorld;
import org.bukkit.craftbukkit.v1_12_R1.inventory.CraftItemStack;
import org.bukkit.entity.LivingEntity;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class EmmberZombieVillager extends EntityZombieVillager implements EmmberMob {

    private int level = 1;
    private double hp = 10;
    private double armor = 0;
    private int minDamage = 1;
    private int maxDamage = 2;
    private int exp = 1;
    private int minMoney = 1;
    private int maxMoney = 2;

    public EmmberZombieVillager(World world) {
        super(world);
    }

    public EmmberZombieVillager(Location loc) {
        super(((CraftWorld) loc.getWorld()).getHandle());
        setLocation(loc.getX(), loc.getY(), loc.getZ(), loc.getPitch(), loc.getYaw());
        prepare(world.D(new BlockPosition(this)), null);
        getLivingEntity().setRemoveWhenFarAway(false);
    }

    /**
     * Change Entity's attribute here
     */
    @Override
    protected void initAttributes() {
        /*
        MOVEMENT_SPEED("generic.movementSpeed", GenericAttributes.MOVEMENT_SPEED)
        FOLLOW_RANGE("generic.followRange", GenericAttributes.FOLLOW_RANGE)
        ATTACK_SPEED("generic.attackSpeed", GenericAttributes.g),
        KNOCKBACK_RESISTANCE("generic.knockbackResistance", GenericAttributes.c);
     */
        super.initAttributes();
        //getAttributeInstance(NMSUtils.Attributes.MOVEMENT_SPEED.asIAttribute()).setValue(0.23f);
    }

    /**
     * Equip the entity here
     */
    @Nullable
    @Override
    public GroupDataEntity prepare(DifficultyDamageScaler difficultydamagescaler, @Nullable GroupDataEntity groupdataentity) {
        groupdataentity = super.prepare(difficultydamagescaler, groupdataentity);
        //setSlot(EnumItemSlot.MAINHAND, new StartingSword());
        return groupdataentity;
    }

    /**
     * Handles updating entity's hp in {@link EntityStats}
     * Actual damage calculation happens in: ?
     * @param damagesource - damage source {@link DamageSource#translationIndex}
     * @param damage - the damage
     * @return
     */
    @Override
    public boolean damageEntity(DamageSource damagesource, float damage) {
        //OUT.toServer("1damagesource: " + damagesource.translationIndex + ", dmg: " + damage);
        if (damagesource.translationIndex.equals("outOfWorld")) { // /kill command
            return super.damageEntity(damagesource, damage);
        }
        if (damagesource.translationIndex.equals("generic")) { // damage from player
            EntityStats entityStats = Emmber.getInstance().entityStatsManager.get(getLivingEntity()); // getting entity's stats
            double newHealth = entityStats.getAtomicHp().addAndGet(-damage);
            if (newHealth <= 0) {
                return super.damageEntity(damagesource, getHealth() * 2); // kill entity with max dmg
            }
            setVanillaHealth((float) newHealth);
        }
        damage = 0;
        return super.damageEntity(damagesource, damage);
    }

    /**
     * Only items with hebrew display name can be added to entity's drops
     *
     * @param itemStack - the ItemStack to add
     */
    public void addDrop(@Nonnull org.bukkit.inventory.ItemStack itemStack) {
        this.a(CraftItemStack.asNMSCopy(itemStack), 0.0F);
    }

    /**
     * Only items with hebrew display name can be added to entity's drops
     *
     * @param itemstack - the item stack to add
     * @param f         - ?
     * @return ?
     */
    @Override
    public EntityItem a(net.minecraft.server.v1_12_R1.ItemStack itemstack, float f) {
        if (itemstack != null && !HebrewUtil.isHebrew(itemstack.getName())) {
            return null;
        }
        return super.a(itemstack, f);
    }

    /**
     * Fired(by spigot) when the entity is died
     * @param damagesource
     */
    @Override
    public void die(DamageSource damagesource) {
        super.die(damagesource);
        EntityLiving killer = ci();
        if (killer != null) {
            EmmberJob emmberJob = Emmber.getInstance().playerManager.get(killer.getUniqueID());
            if (emmberJob != null) {
                emmberJob.addExp(getExp());
            }
        }
        Emmber.getInstance().spawnerManager.removeEntity(getUniqueID());
    }

    /**
     * Equip entity, set armor + item in main and off hand
     *
     * @param enumitemslot - {@link EnumItemSlot}
     * @param itemstack
     */
    public EmmberZombieVillager setSlot(EnumItemSlot enumitemslot, org.bukkit.inventory.ItemStack itemstack) {
        setSlot(enumitemslot, CraftItemStack.asNMSCopy(itemstack));
        return this;
    }

    /**
     * Adds entity's level before the name.
     */
    @Override
    public void setCustomName(String s) {
        super.setCustomName(s + ChatColor.YELLOW + " [" + ChatColor.WHITE + getLevel() + ChatColor.YELLOW + "]");
    }

    @Override
    public void setAirTicks(int i) {
        super.setAirTicks(i);
    }

    public LivingEntity getLivingEntity() {
        return ((LivingEntity) this.getBukkitEntity());
    }

    public int getLevel() {
        return level;
    }

    public EmmberZombieVillager setLevel(int level) {
        this.level = level;
        return this;
    }

    public double getHp() {
        return hp;
    }

    public void setVanillaHealth(float health) {
        if (health > 2048) {
            health = 2048;
        }
        if (getMaxHealth() < health) {
            getAttributeInstance(NMSUtils.Attributes.MAX_HEALTH.asIAttribute()).setValue(health);
        }
        setHealth(health);
    }

    public EmmberZombieVillager setHp(double hp) {
        this.hp = hp;
        return this;
    }

    public double getArmor() {
        return armor;
    }

    public EmmberZombieVillager setArmor(int armor) {
        this.armor = armor;
        return this;
    }

    public int getMinDamage() {
        return minDamage;
    }

    public EmmberZombieVillager setMinDamage(int minDamage) {
        this.minDamage = minDamage;
        return this;
    }

    public int getMaxDamage() {
        return maxDamage;
    }

    public EmmberZombieVillager setMaxDamage(int maxDamage) {
        this.maxDamage = maxDamage;
        return this;
    }

    public double getMovementSpeed() {
        return getAttributeInstance(NMSUtils.Attributes.MOVEMENT_SPEED.asIAttribute()).getValue();
    }

    public int getExp() {
        return exp;
    }

    public EmmberZombieVillager setExp(int exp) {
        this.exp = exp;
        return this;
    }

    public int getMinMoney() {
        return minMoney;
    }

    public EmmberZombieVillager setMinMoney(int minMoney) {
        this.minMoney = minMoney;
        return this;
    }

    public int getMaxMoney() {
        return maxMoney;
    }

    public EmmberZombieVillager setMaxMoney(int maxMoney) {
        this.maxMoney = maxMoney;
        return this;
    }
}
