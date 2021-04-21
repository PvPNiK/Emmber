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

public class GoblinManager extends EmmberZombieVillager {

    public GoblinManager(World world) {
        super(world);
    }

    public GoblinManager(Location loc) {
        super(loc);
        setCustomNameVisible(true);
        setLevel(11). // level
        setCustomName(CustomEntities.GoblinManager.getCustomName()); // change 'goblinmanager' to mob's name
        setExp(11). // exp
        setMinDamage(70). // attack
        setMaxDamage(82). // attck
        setArmor(31). // defense
        setHp(330). // health
        setMinMoney(2). // money
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

    public static GoblinManager spawn(@Nonnull final Location location) {
        if (!location.getChunk().isLoaded()) {
            location.getChunk().load();
        }
        final GoblinManager goblinmanager = new GoblinManager(location);
        Emmber.getInstance().serverClock.addAction(new ServerClock.Action(goblinmanager.getUniqueID().toString(), 1, o -> {
            Emmber.getInstance().entityManager.add(goblinmanager);
            ((CraftWorld) location.getWorld()).getHandle().addEntity(goblinmanager);
        }).setRepeat(false));
        return goblinmanager;
    }

}
