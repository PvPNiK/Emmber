package me.pvpnik.emmber.mobs.regular.zombieVillager;

import me.pvpnik.emmber.Emmber;
import me.pvpnik.emmber.ServerClock;
import me.pvpnik.emmber.api.entity.CustomEntities;
import me.pvpnik.emmber.api.entity.entities.EmmberSkeleton;
import me.pvpnik.emmber.api.entity.entities.EmmberZombieVillager;
import me.pvpnik.emmber.utils.NMSUtils;
import net.minecraft.server.v1_12_R1.DifficultyDamageScaler;
import net.minecraft.server.v1_12_R1.EnumItemSlot;
import net.minecraft.server.v1_12_R1.GroupDataEntity;
import net.minecraft.server.v1_12_R1.World;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_12_R1.CraftWorld;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class WeakGoblinmau extends EmmberZombieVillager {

    public WeakGoblinmau(World world) {
        super(world);
    }

    public WeakGoblinmau(Location loc) {
        super(loc);
        setCustomNameVisible(true);
        setLevel(7). // level
        setCustomName(CustomEntities.WeakGoblinmau.getCustomName()); // change 'weakgoblinmau' to mob's name
        setExp(13). // exp
        setMinDamage(68). // attack
        setMaxDamage(70). // attck
        setArmor(0). // defense
        setHp(2150). // health
        setMinMoney(3). // money
        setMaxMoney(3); // money
    }

    @Override
    protected void initAttributes() {
        super.initAttributes();
        getAttributeInstance(NMSUtils.Attributes.MOVEMENT_SPEED.asIAttribute()).setValue(0.23f); // speed
    }

    @Override
    public GroupDataEntity prepare(DifficultyDamageScaler difficultydamagescaler, @Nullable GroupDataEntity groupdataentity) {
        groupdataentity = super.prepare(difficultydamagescaler, groupdataentity);
        return groupdataentity;
    }

    public static WeakGoblinmau spawn(@Nonnull final Location location) {
        if (!location.getChunk().isLoaded()) {
            location.getChunk().load();
        }
        final WeakGoblinmau weakgoblinmau = new WeakGoblinmau(location);
        Emmber.getInstance().serverClock.addAction(new ServerClock.Action(weakgoblinmau.getUniqueID().toString(), 1, o -> {
            Emmber.getInstance().entityManager.add(weakgoblinmau);
            ((CraftWorld) location.getWorld()).getHandle().addEntity(weakgoblinmau);
        }).setRepeat(false));
        return weakgoblinmau;
    }

}
