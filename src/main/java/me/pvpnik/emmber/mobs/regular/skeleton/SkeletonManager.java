package me.pvpnik.emmber.mobs.regular.skeleton;

import me.pvpnik.emmber.Emmber;
import me.pvpnik.emmber.ServerClock;
import me.pvpnik.emmber.api.entity.CustomEntities;
import me.pvpnik.emmber.api.entity.entities.EmmberSkeleton;
import me.pvpnik.emmber.api.entity.entities.EmmberStray;
import me.pvpnik.emmber.items.weapon.weaponregularlv1normal;
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

public class SkeletonManager extends EmmberStray {

    public SkeletonManager(World world) {
        super(world);
    }

    public SkeletonManager(Location loc) {
        super(loc);
        setCustomNameVisible(true);
        setLevel(12). // level
        setCustomName(CustomEntities.SkeletonManager.getCustomName()); // change 'skeletonmanager' to mob's name
        setExp(11). // exp
        setMinDamage(73). // attack
        setMaxDamage(84). // attck
        setArmor(33). // defense
        setHp(350). // health
        setMinMoney(2). // money
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
        return groupdataentity;
    }

    public static SkeletonManager spawn(@Nonnull final Location location) {
        if (!location.getChunk().isLoaded()) {
            location.getChunk().load();
        }
        final SkeletonManager skeletonmanager = new SkeletonManager(location);
        Emmber.getInstance().serverClock.addAction(new ServerClock.Action(skeletonmanager.getUniqueID().toString(), 1, o -> {
            Emmber.getInstance().entityManager.add(skeletonmanager);
            ((CraftWorld) location.getWorld()).getHandle().addEntity(skeletonmanager);
        }).setRepeat(false));
        return skeletonmanager;
    }

}
