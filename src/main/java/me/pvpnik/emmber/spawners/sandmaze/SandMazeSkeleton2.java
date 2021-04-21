package me.pvpnik.emmber.spawners.sandmaze;

import me.pvpnik.emmber.Emmber;
import me.pvpnik.emmber.api.spawner.EntitySpawner;
import me.pvpnik.emmber.mobs.regular.skeleton.SandSkeleton;
import me.pvpnik.emmber.mobs.regular.skeleton.WeakSkeleton;
import org.bukkit.Location;
import org.bukkit.entity.LivingEntity;

public class SandMazeSkeleton2 extends EntitySpawner {
 
    public SandMazeSkeleton2() {
        super("SandMazeSkeleton2");
        setMaxspawn(3);
        setCooldown(6);
        setSpawning(true);
        addLocation(new Location(Emmber.getInstance().getWorld(), -82.5, 79.2, -170.5));
        addLocation(new Location(Emmber.getInstance().getWorld(), -87.5, 78.2, -147.5));
        addLocation(new Location(Emmber.getInstance().getWorld(), -83.5, 77.2, -133.5));
        addLocation(new Location(Emmber.getInstance().getWorld(), -88.5, 77.2, -113.5));
    }
 
    @Override
    public LivingEntity spawnEntity(Location location) {
        return SandSkeleton.spawn(location).getLivingEntity();
    }
 
}