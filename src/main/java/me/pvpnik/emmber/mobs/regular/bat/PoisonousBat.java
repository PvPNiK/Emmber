package me.pvpnik.emmber.mobs.regular.bat;

import me.pvpnik.emmber.Emmber;
import me.pvpnik.emmber.ServerClock;
import me.pvpnik.emmber.api.entity.CustomEntities;
import me.pvpnik.emmber.api.entity.entities.EmmberBat;
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

public class PoisonousBat extends EmmberBat {

    public PoisonousBat(World world) {
        super(world);
    }

    public PoisonousBat(Location loc) {
        super(loc);
        setCustomNameVisible(true);
        setLevel(7). // level
        setCustomName(CustomEntities.PoisonousBat.getCustomName()); // change 'poisonousbat' to mob's name
        setExp(7). // exp
        setMinDamage(36). // attack
        setMaxDamage(49). // attck
        setArmor(0). // defense
        setHp(220). // health
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
        setSlot(EnumItemSlot.MAINHAND, new ItemStack(org.bukkit.Material.WOOD_SWORD));
        return groupdataentity;
    }

    public static PoisonousBat spawn(@Nonnull final Location location) {
        if (!location.getChunk().isLoaded()) {
            location.getChunk().load();
        }
        final PoisonousBat poisonousbat = new PoisonousBat(location);
        Emmber.getInstance().serverClock.addAction(new ServerClock.Action(poisonousbat.getUniqueID().toString(), 1, o -> {
            Emmber.getInstance().entityManager.add(poisonousbat);
            ((CraftWorld) location.getWorld()).getHandle().addEntity(poisonousbat);
        }).setRepeat(false));
        return poisonousbat;
    }

}
