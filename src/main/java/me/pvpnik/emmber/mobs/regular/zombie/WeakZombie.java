package me.pvpnik.emmber.mobs.regular.zombie;

import me.pvpnik.emmber.Emmber;
import me.pvpnik.emmber.ServerClock;
import me.pvpnik.emmber.api.entity.CustomEntities;
import me.pvpnik.emmber.api.entity.entities.EmmberSkeleton;
import me.pvpnik.emmber.api.entity.entities.EmmberZombie;
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

public class WeakZombie extends EmmberZombie {

    public WeakZombie(World world) {
        super(world);
    }

    public WeakZombie(Location loc) {
        super(loc);
        setCustomNameVisible(true);
        setLevel(5). // level
        setCustomName(CustomEntities.WeakZombie.getCustomName()); // change 'weakzombie' to mob's name
        setExp(10). // exp
        setMinDamage(51). // attack
        setMaxDamage(53). // attck
        setArmor(0). // defense
        setHp(1000). // health
        setMinMoney(2). // money
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

    public static WeakZombie spawn(@Nonnull final Location location) {
        if (!location.getChunk().isLoaded()) {
            location.getChunk().load();
        }
        final WeakZombie weakzombie = new WeakZombie(location);
        Emmber.getInstance().serverClock.addAction(new ServerClock.Action(weakzombie.getUniqueID().toString(), 1, o -> {
            Emmber.getInstance().entityManager.add(weakzombie);
            ((CraftWorld) location.getWorld()).getHandle().addEntity(weakzombie);
        }).setRepeat(false));
        return weakzombie;
    }

}
