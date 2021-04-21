package me.pvpnik.emmber.mobs.regular.spider;

import me.pvpnik.emmber.Emmber;
import me.pvpnik.emmber.ServerClock;
import me.pvpnik.emmber.api.entity.CustomEntities;
import me.pvpnik.emmber.api.entity.entities.EmmberCaveSpider;
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

public class CaveSpider extends EmmberCaveSpider {

    public CaveSpider(World world) {
        super(world);
    }

    public CaveSpider(Location loc) {
        super(loc);
        setCustomNameVisible(true);
        setLevel(6). // level
        setCustomName(CustomEntities.CaveSpider.getCustomName()); // change 'cavespider' to mob's name
        setExp(11). // exp
        setMinDamage(60). // attack
        setMaxDamage(63). // attck
        setArmor(0). // defense
        setHp(1000). // health
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
        return groupdataentity;
    }

    public static CaveSpider spawn(@Nonnull final Location location) {
        if (!location.getChunk().isLoaded()) {
            location.getChunk().load();
        }
        final CaveSpider cavespider = new CaveSpider(location);
        Emmber.getInstance().serverClock.addAction(new ServerClock.Action(cavespider.getUniqueID().toString(), 1, o -> {
            Emmber.getInstance().entityManager.add(cavespider);
            ((CraftWorld) location.getWorld()).getHandle().addEntity(cavespider);
        }).setRepeat(false));
        return cavespider;
    }

}
