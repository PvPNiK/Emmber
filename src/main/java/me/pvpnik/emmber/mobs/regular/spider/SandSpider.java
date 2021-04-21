package me.pvpnik.emmber.mobs.regular.spider;

import me.pvpnik.emmber.Emmber;
import me.pvpnik.emmber.ServerClock;
import me.pvpnik.emmber.api.entity.CustomEntities;
import me.pvpnik.emmber.api.entity.entities.EmmberSkeleton;
import me.pvpnik.emmber.api.entity.entities.EmmberSpider;
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

public class SandSpider extends EmmberSpider {

    public SandSpider(World world) {
        super(world);
    }

    public SandSpider(Location loc) {
        super(loc);
        setCustomNameVisible(true);
        setLevel(6). // level
        setCustomName(CustomEntities.SandSpider.getCustomName()); // change 'sandspider' to mob's name
        setExp(12). // exp
        setMinDamage(64). // attack
        setMaxDamage(66). // attck
        setArmor(0). // defense
        setHp(1600). // health
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

    public static SandSpider spawn(@Nonnull final Location location) {
        if (!location.getChunk().isLoaded()) {
            location.getChunk().load();
        }
        final SandSpider sandspider = new SandSpider(location);
        Emmber.getInstance().serverClock.addAction(new ServerClock.Action(sandspider.getUniqueID().toString(), 1, o -> {
            Emmber.getInstance().entityManager.add(sandspider);
            ((CraftWorld) location.getWorld()).getHandle().addEntity(sandspider);
        }).setRepeat(false));
        return sandspider;
    }

}
