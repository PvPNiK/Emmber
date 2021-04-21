package me.pvpnik.emmber.mobs.regular.skeleton;

import me.pvpnik.emmber.Emmber;
import me.pvpnik.emmber.ServerClock;
import me.pvpnik.emmber.api.entity.CustomEntities;
import me.pvpnik.emmber.api.entity.entities.EmmberSkeleton;
import me.pvpnik.emmber.items.EmmberSkull;
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

public class RavSkeleton extends EmmberSkeleton {

    public RavSkeleton(World world) {
        super(world);
    }

    public RavSkeleton(Location loc) {
        super(loc);
        setCustomNameVisible(true);
        setLevel(4). // level
        setCustomName(CustomEntities.RavSkeleton.getCustomName());
        setExp(8). // exp
        setMinDamage(47). // attack
        setMaxDamage(50). // attack
        setArmor(0). // defense
        setHp(800). // health
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
        setSlot(EnumItemSlot.HEAD, EmmberSkull.getSkull(EmmberSkull.Skulls.RavSkeletonHelmet));
        setSlot(EnumItemSlot.CHEST, new ItemStack(Material.LEATHER_CHESTPLATE));
        setSlot(EnumItemSlot.LEGS, new ItemStack(Material.LEATHER_LEGGINGS));
        return groupdataentity;
    }

    public static RavSkeleton spawn(@Nonnull final Location location) {
        if (!location.getChunk().isLoaded()) {
            location.getChunk().load();
        }
        final RavSkeleton ravskeleton = new RavSkeleton(location);
        Emmber.getInstance().serverClock.addAction(new ServerClock.Action(ravskeleton.getUniqueID().toString(), 1, o -> {
            Emmber.getInstance().entityManager.add(ravskeleton);
            ((CraftWorld) location.getWorld()).getHandle().addEntity(ravskeleton);
        }).setRepeat(false));
        return ravskeleton;
    }

}
