package me.pvpnik.emmber.mobs.regular.skeleton;

import me.pvpnik.emmber.Emmber;
import me.pvpnik.emmber.ServerClock;
import me.pvpnik.emmber.api.entity.CustomEntities;
import me.pvpnik.emmber.api.entity.entities.EmmberSkeleton;
import me.pvpnik.emmber.items.weapon.weaponregularlv1normal;
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

public class NormalSkeleton extends EmmberSkeleton {

    public NormalSkeleton(World world) {
        super(world);
    }

    public NormalSkeleton(Location loc) {
        super(loc);
        setCustomNameVisible(true);
        setLevel(2). // level
        setCustomName(CustomEntities.NormalSkeleton.getCustomName()); // change 'skeleton' to mob's name
        setExp(6). // exp
        setMinDamage(27). // attack
        setMaxDamage(30). // attack
        setArmor(0). // defense
        setHp(400). // health
        setMinMoney(1). // money
        setMaxMoney(2); // money

        setSlot(EnumItemSlot.MAINHAND, new weaponregularlv1normal());
    }

    @Override
    protected void initAttributes() {
        super.initAttributes();
        getAttributeInstance(NMSUtils.Attributes.MOVEMENT_SPEED.asIAttribute()).setValue(0.23f); // speed
    }

    @Override
    public GroupDataEntity prepare(DifficultyDamageScaler difficultydamagescaler, @Nullable GroupDataEntity groupdataentity) {
        groupdataentity = super.prepare(difficultydamagescaler, groupdataentity);
        setSlot(EnumItemSlot.HEAD, new ItemStack(Material.BOAT_ACACIA));
        return groupdataentity;
    }

    public static NormalSkeleton spawn(@Nonnull final Location location) {
        if (!location.getChunk().isLoaded()) {
            location.getChunk().load();
        }
        final NormalSkeleton normalSkeleton = new NormalSkeleton(location);
        Emmber.getInstance().serverClock.addAction(new ServerClock.Action(normalSkeleton.getUniqueID().toString(), 1, o -> {
            Emmber.getInstance().entityManager.add(normalSkeleton);
            ((CraftWorld) location.getWorld()).getHandle().addEntity(normalSkeleton);
        }).setRepeat(false));
        return normalSkeleton;
    }

}
