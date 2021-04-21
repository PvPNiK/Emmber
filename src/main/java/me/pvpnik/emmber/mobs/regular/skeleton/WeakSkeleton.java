package me.pvpnik.emmber.mobs.regular.skeleton;

import me.pvpnik.emmber.Emmber;
import me.pvpnik.emmber.ServerClock;
import me.pvpnik.emmber.api.entity.CustomEntities;
import me.pvpnik.emmber.api.entity.entities.EmmberSkeleton;
import me.pvpnik.emmber.items.weapon.weaponregularlv1normal;
import me.pvpnik.emmber.utils.NMSUtils;
import me.pvpnik.emmber.utils.OUT;
import net.minecraft.server.v1_12_R1.*;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_12_R1.CraftWorld;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class WeakSkeleton extends EmmberSkeleton {

    public WeakSkeleton(World world) {
        super(world);
    }

    public WeakSkeleton(Location loc) {
        super(loc);
        setCustomNameVisible(true);
        setLevel(1).
        setCustomName(CustomEntities.WeakSkeleton.getCustomName());
        setExp(3).setMinDamage(15).setMaxDamage(21).setArmor(0).setHp(260).setMinMoney(1).setMaxMoney(2);

        setSlot(EnumItemSlot.MAINHAND, new weaponregularlv1normal());
    }

    @Override
    protected void initAttributes() {
        super.initAttributes();
        getAttributeInstance(NMSUtils.Attributes.MOVEMENT_SPEED.asIAttribute()).setValue(0.23f);
    }

    @Override
    public GroupDataEntity prepare(DifficultyDamageScaler difficultydamagescaler, @Nullable GroupDataEntity groupdataentity) {
        groupdataentity = super.prepare(difficultydamagescaler, groupdataentity);
        setSlot(EnumItemSlot.HEAD, new ItemStack(Material.BOAT_ACACIA));
        return groupdataentity;
    }

    @Override
    protected void dropDeathLoot(boolean flag, int i) {
        super.dropDeathLoot(flag, i);
    }

    @Override
    protected Item getLoot() {
        return super.getLoot();
    }

    public static WeakSkeleton spawn(@Nonnull final Location location) {
        if (!location.getChunk().isLoaded()) {
            location.getChunk().load();
        }
        final WeakSkeleton weakskeleton = new WeakSkeleton(location);
        Emmber.getInstance().serverClock.addAction(new ServerClock.Action(weakskeleton.getUniqueID().toString(), 1, o -> {
            Emmber.getInstance().entityManager.add(weakskeleton);
            ((CraftWorld) location.getWorld()).getHandle().addEntity(weakskeleton);
        }).setRepeat(false));
        return weakskeleton;
    }

}
