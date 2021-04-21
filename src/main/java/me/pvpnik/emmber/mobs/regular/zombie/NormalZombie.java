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
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_12_R1.CraftWorld;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class NormalZombie extends EmmberZombie {

    public NormalZombie(World world) {
        super(world);
    }

    public NormalZombie(Location loc) {
        super(loc);
        setCustomNameVisible(true);
        setLevel(6). // level
        setCustomName(CustomEntities.NormalZombie.getCustomName()); // change 'zombie' to mob's name
        setExp(12). // exp
        setMinDamage(63). // attack
        setMaxDamage(65). // attck
        setArmor(0). // defense
        setHp(1800). // health
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
        setSlot(EnumItemSlot.LEGS, new ItemStack(Material.LEATHER_LEGGINGS));
        setSlot(EnumItemSlot.MAINHAND, new ItemStack(Material.STONE_PICKAXE));
        return groupdataentity;
    }

    public static NormalZombie spawn(@Nonnull final Location location) {
        if (!location.getChunk().isLoaded()) {
            location.getChunk().load();
        }
        final NormalZombie normalZombie = new NormalZombie(location);
        Emmber.getInstance().serverClock.addAction(new ServerClock.Action(normalZombie.getUniqueID().toString(), 1, o -> {
            Emmber.getInstance().entityManager.add(normalZombie);
            ((CraftWorld) location.getWorld()).getHandle().addEntity(normalZombie);
        }).setRepeat(false));
        return normalZombie;
    }

}
