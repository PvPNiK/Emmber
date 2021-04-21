package me.pvpnik.emmber.mobs.regular.zombieVillager;

import me.pvpnik.emmber.Emmber;
import me.pvpnik.emmber.ServerClock;
import me.pvpnik.emmber.api.entity.CustomEntities;
import me.pvpnik.emmber.api.entity.entities.EmmberSkeleton;
import me.pvpnik.emmber.api.entity.entities.EmmberZombieVillager;
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

public class CaveGoblin extends EmmberZombieVillager {

    public CaveGoblin(World world) {
        super(world);
    }

    public CaveGoblin(Location loc) {
        super(loc);
        setCustomNameVisible(true);
        setLevel(10). // level
        setCustomName(CustomEntities.CaveGoblin.getCustomName()); // change 'cavegoblin' to mob's name
        setExp(10). // exp
        setMinDamage(69). // attack
        setMaxDamage(81). // attck
        setArmor(29). // defense
        setHp(310). // health
        setMinMoney(1). // money
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
        return groupdataentity;
    }

    public static CaveGoblin spawn(@Nonnull final Location location) {
        if (!location.getChunk().isLoaded()) {
            location.getChunk().load();
        }
        final CaveGoblin cavegoblin = new CaveGoblin(location);
        Emmber.getInstance().serverClock.addAction(new ServerClock.Action(cavegoblin.getUniqueID().toString(), 1, o -> {
            Emmber.getInstance().entityManager.add(cavegoblin);
            ((CraftWorld) location.getWorld()).getHandle().addEntity(cavegoblin);
        }).setRepeat(false));
        return cavegoblin;
    }

}
