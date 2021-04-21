package me.pvpnik.emmber.mobs.regular.skeleton;

import me.pvpnik.emmber.Emmber;
import me.pvpnik.emmber.ServerClock;
import me.pvpnik.emmber.api.entity.CustomEntities;
import me.pvpnik.emmber.api.entity.entities.EmmberSkeleton;
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

public class DrakenSkeleton extends EmmberSkeleton {

    public DrakenSkeleton(World world) {
        super(world);
    }

    public DrakenSkeleton(Location loc) {
        super(loc);
        setCustomNameVisible(true);
        setLevel(8). // level
        setCustomName(CustomEntities.DrakenSkeleton.getCustomName()); // change 'drakenskeleton' to mob's name
        setExp(8). // exp
        setMinDamage(57). // attack
        setMaxDamage(70). // attck
        setArmor(0). // defense
        setHp(245). // health
        setMinMoney(1). // money
        setMaxMoney(1); // money
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

    public static DrakenSkeleton spawn(@Nonnull final Location location) {
        if (!location.getChunk().isLoaded()) {
            location.getChunk().load();
        }
        final DrakenSkeleton drakenskeleton = new DrakenSkeleton(location);
        Emmber.getInstance().serverClock.addAction(new ServerClock.Action(drakenskeleton.getUniqueID().toString(), 1, o -> {
            Emmber.getInstance().entityManager.add(drakenskeleton);
            ((CraftWorld) location.getWorld()).getHandle().addEntity(drakenskeleton);
        }).setRepeat(false));
        return drakenskeleton;
    }

}
