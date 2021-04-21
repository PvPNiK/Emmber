package me.pvpnik.emmber.spawners.sandmaze;

import me.pvpnik.emmber.Emmber;
import me.pvpnik.emmber.api.spawner.EntitySpawner;
import me.pvpnik.emmber.mobs.regular.skeleton.SandSkeleton;
import me.pvpnik.emmber.mobs.regular.skeleton.WeakSkeleton;
import org.bukkit.Location;
import org.bukkit.entity.LivingEntity;

public class SandMazeSkeleton8 extends EntitySpawner {
 
    public SandMazeSkeleton8() {
        super("SandMazeSkeleton8");
        setMaxspawn(3);
        setCooldown(6);
        setSpawning(true);
        addLocation(new Location(Emmber.getInstance().getWorld(), -238.5, 78.2, -177.5));
        addLocation(new Location(Emmber.getInstance().getWorld(), -226.5, 76.2, -193.5));
        addLocation(new Location(Emmber.getInstance().getWorld(), -240.5, 76.2, -204.5));
        addLocation(new Location(Emmber.getInstance().getWorld(), -231.5, 78.2, -215.5));
    }
 
    @Override
    public LivingEntity spawnEntity(Location location) {
        return SandSkeleton.spawn(location).getLivingEntity();
    }
 
}