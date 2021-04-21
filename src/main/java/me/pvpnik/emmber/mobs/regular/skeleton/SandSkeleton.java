package me.pvpnik.emmber.mobs.regular.skeleton;

import me.pvpnik.emmber.Emmber;
import me.pvpnik.emmber.ServerClock;
import me.pvpnik.emmber.api.entity.CustomEntities;
import me.pvpnik.emmber.api.entity.entities.EmmberSkeleton;
import me.pvpnik.emmber.items.EmmberSkull;
import me.pvpnik.emmber.items.weapon.weaponregularlv1normal;
import me.pvpnik.emmber.utils.NMSUtils;
import net.minecraft.server.v1_12_R1.*;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_12_R1.CraftWorld;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class SandSkeleton extends EmmberSkeleton {

    public SandSkeleton(World world) {
        super(world);
        setCustomNameVisible(true);
        setLevel(5). // level
        setCustomName(CustomEntities.SandSkeleton.getCustomName()); // change 'sandSkeleton' to mob's name
                setExp(11). // exp
                setMinDamage(53). // attack
                setMaxDamage(54). // attck
                setArmor(0). // defense
                setHp(1500). // health
                setMinMoney(2). // money
                setMaxMoney(3); // money
        Emmber.getInstance().entityManager.add(this);

        setSlot(EnumItemSlot.MAINHAND, new weaponregularlv1normal());
    }

    public SandSkeleton(Location loc) {
        super(loc);
        setCustomNameVisible(true);
        setLevel(5). // level
        setCustomName(CustomEntities.SandSkeleton.getCustomName()); // change 'sandSkeleton' to mob's name
        setExp(11). // exp
        setMinDamage(53). // attack
        setMaxDamage(54). // attck
        setArmor(0). // defense
        setHp(1500). // health
        setMinMoney(2). // money
        setMaxMoney(3); // money

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
        setSlot(EnumItemSlot.MAINHAND, new ItemStack(org.bukkit.Material.DIAMOND_SWORD));
        setSlot(EnumItemSlot.HEAD, EmmberSkull.getSkull(EmmberSkull.Skulls.SandSkeletonHelmet));
        setSlot(EnumItemSlot.CHEST, new ItemStack(org.bukkit.Material.LEATHER_CHESTPLATE));
        return groupdataentity;
    }

    public static SandSkeleton spawn(@Nonnull final Location location) {
        if (!location.getChunk().isLoaded()) {
            location.getChunk().load();
        }
        final SandSkeleton sandskeleton = new SandSkeleton(location);
        sandskeleton.getLivingEntity().setRemoveWhenFarAway(false);
        Emmber.getInstance().serverClock.addAction(new ServerClock.Action(sandskeleton.getUniqueID().toString(), 1, o -> {
            Emmber.getInstance().entityManager.add(sandskeleton);
            ((CraftWorld) location.getWorld()).getHandle().addEntity(sandskeleton);
        }).setRepeat(false));
        return sandskeleton;
    }

}
