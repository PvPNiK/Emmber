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
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_12_R1.CraftWorld;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class UpgradedSkeleton extends EmmberSkeleton {

    public UpgradedSkeleton(World world) {
        super(world);
    }

    public UpgradedSkeleton(Location loc) {
        super(loc);
        setCustomNameVisible(true);
        setLevel(3). // level
        setCustomName(CustomEntities.UpgradedSkeleton.getCustomName()); // change 'upgradedskeleton' to mob's name
        setExp(7). // exp
        setMinDamage(38). // attack
        setMaxDamage(41). // attack
        setArmor(0). // defense
        setHp(500). // health
        setMinMoney(2). // money
        setMaxMoney(2); // money
    }

    @Override
    protected void initAttributes() {
        super.initAttributes();
        getAttributeInstance(NMSUtils.Attributes.MOVEMENT_SPEED.asIAttribute()).setValue(0.23f); // speed
    }

    @Override
    public GroupDataEntity prepare(DifficultyDamageScaler difficultydamagescaler, @Nullable GroupDataEntity groupdataentity) {
        groupdataentity = super.prepare(difficultydamagescaler, groupdataentity);
        setSlot(EnumItemSlot.CHEST, new ItemStack(Material.LEATHER_CHESTPLATE));
        return groupdataentity;
    }

    public static UpgradedSkeleton spawn(@Nonnull final Location location) {
        if (!location.getChunk().isLoaded()) {
            location.getChunk().load();
        }
        final UpgradedSkeleton upgradedskeleton = new UpgradedSkeleton(location);
        Emmber.getInstance().serverClock.addAction(new ServerClock.Action(upgradedskeleton.getUniqueID().toString(), 1, o -> {
            Emmber.getInstance().entityManager.add(upgradedskeleton);
            ((CraftWorld) location.getWorld()).getHandle().addEntity(upgradedskeleton);
        }).setRepeat(false));
        return upgradedskeleton;
    }

}
