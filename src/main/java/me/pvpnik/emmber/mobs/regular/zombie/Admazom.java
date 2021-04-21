package me.pvpnik.emmber.mobs.regular.zombie;

import me.pvpnik.emmber.Emmber;
import me.pvpnik.emmber.ServerClock;
import me.pvpnik.emmber.api.entity.CustomEntities;
import me.pvpnik.emmber.api.entity.entities.EmmberSkeleton;
import me.pvpnik.emmber.api.entity.entities.EmmberZombie;
import me.pvpnik.emmber.items.EmmberSkull;
import me.pvpnik.emmber.utils.NMSUtils;
import net.minecraft.server.v1_12_R1.DifficultyDamageScaler;
import net.minecraft.server.v1_12_R1.EnumItemSlot;
import net.minecraft.server.v1_12_R1.GroupDataEntity;
import net.minecraft.server.v1_12_R1.World;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_12_R1.CraftWorld;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class Admazom extends EmmberZombie {

    public Admazom(World world) {
        super(world);
    }

    public Admazom(Location loc) {
        super(loc);
        setCustomNameVisible(true);
        setLevel(4). // level
        setCustomName(CustomEntities.Admazom.getCustomName()); // change 'admazom' to mob's name
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
        setSlot(EnumItemSlot.CHEST, new ItemStack(Material.LEATHER_CHESTPLATE));
        setSlot(EnumItemSlot.HEAD, EmmberSkull.getSkull(EmmberSkull.Skulls.AdmazomHelmet));
        return groupdataentity;
    }

    public static Admazom spawn(@Nonnull final Location location) {
        if (!location.getChunk().isLoaded()) {
            location.getChunk().load();
        }
        final Admazom admazom = new Admazom(location);
        Emmber.getInstance().serverClock.addAction(new ServerClock.Action(admazom.getUniqueID().toString(), 1, o -> {
            Emmber.getInstance().entityManager.add(admazom);
            ((CraftWorld) location.getWorld()).getHandle().addEntity(admazom);
        }).setRepeat(false));
        return admazom;
    }

}
