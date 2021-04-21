package me.pvpnik.emmber.mobs.regular;

import me.pvpnik.emmber.Emmber;
import me.pvpnik.emmber.ServerClock;
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

public class Example extends EmmberSkeleton {

    public Example(World world) {
        super(world);
    }

    public Example(Location loc) {
        super(loc);
        setCustomNameVisible(true);
        setLevel(1). // level
        setCustomName(Emmber.getInstance().localFileManager.messages.getMessage("mobs.example")); // change 'example' to mob's name
        setExp(3). // exp
        setMinDamage(15). // attack
        setMaxDamage(21). // attack
        setArmor(0). // defense
        setHp(260). // health
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
        //setSlot(EnumItemSlot.MAINHAND, new ItemStack(org.bukkit.Material.WOOD_SWORD));
        return groupdataentity;
    }

    public static Example spawn(@Nonnull final Location location) {
        if (!location.getChunk().isLoaded()) {
            location.getChunk().load();
        }
        final Example example = new Example(location);
        Emmber.getInstance().serverClock.addAction(new ServerClock.Action(example.getUniqueID().toString(), 1, o -> {
            Emmber.getInstance().entityManager.add(example);
            ((CraftWorld) location.getWorld()).getHandle().addEntity(example);
        }).setRepeat(false));
        return example;
    }

}
