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

public class FighterGoblin extends EmmberZombieVillager {

    public FighterGoblin(World world) {
        super(world);
    }

    public FighterGoblin(Location loc) {
        super(loc);
        setCustomNameVisible(true);
        setLevel(9). // level
        setCustomName(CustomEntities.FighterGoblin.getCustomName()); // change 'fightergoblin' to mob's name
        setExp(8). // exp
        setMinDamage(60). // attack
        setMaxDamage(74). // attck
        setArmor(0). // defense
        setHp(258). // health
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

    public static FighterGoblin spawn(@Nonnull final Location location) {
        if (!location.getChunk().isLoaded()) {
            location.getChunk().load();
        }
        final FighterGoblin fightergoblin = new FighterGoblin(location);
        Emmber.getInstance().serverClock.addAction(new ServerClock.Action(fightergoblin.getUniqueID().toString(), 1, o -> {
            Emmber.getInstance().entityManager.add(fightergoblin);
            ((CraftWorld) location.getWorld()).getHandle().addEntity(fightergoblin);
        }).setRepeat(false));
        return fightergoblin;
    }

}
