package me.pvpnik.emmber.spawners.sandmaze;

import me.pvpnik.emmber.Emmber;
import me.pvpnik.emmber.api.spawner.EntitySpawner;
import me.pvpnik.emmber.mobs.regular.skeleton.SandSkeleton;
import me.pvpnik.emmber.mobs.regular.skeleton.WeakSkeleton;
import org.bukkit.Location;
import org.bukkit.entity.LivingEntity;

public class SandMazeSkeleton5 extends EntitySpawner {
 
    public SandMazeSkeleton5() {
        super("SandMazeSkeleton5");
        setMaxspawn(3);
        setCooldown(6);
        setSpawning(true);
        addLocation(new Location(Emmber.getInstance().getWorld(), -171.5, 74.2, -75.5));
        addLocation(new Location(Emmber.getInstance().getWorld(), -160.5, 78.2, -96.5));
        addLocation(new Location(Emmber.getInstance().getWorld(), -178.5, 79.2, -95.5));
        addLocation(new Location(Emmber.getInstance().getWorld(), -193.5, 76.2, -73.5));
    }
 
    @Override
    public LivingEntity spawnEntity(Location location) {
        return SandSkeleton.spawn(location).getLivingEntity();
    }
 
}