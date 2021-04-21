package me.pvpnik.emmber.spawners.gate;

import me.pvpnik.emmber.Emmber;
import me.pvpnik.emmber.api.spawner.EntitySpawner;
import me.pvpnik.emmber.mobs.regular.skeleton.WeakSkeleton;
import org.bukkit.Location;
import org.bukkit.entity.LivingEntity;

public class WeakSkeletonGate2 extends EntitySpawner {
 
    public WeakSkeletonGate2() {
        super("WeakSkeletonGate2");
        setMaxspawn(5);
        setCooldown(6);
        setSpawning(true);
        addLocation(new Location(Emmber.getInstance().getWorld(),82.5, 79.2, 19.5));
        addLocation(new Location(Emmber.getInstance().getWorld(),75.5, 76.2, 12.5));
        addLocation(new Location(Emmber.getInstance().getWorld(),87.5, 81.2, 28.5));
        addLocation(new Location(Emmber.getInstance().getWorld(),103.5, 84.2, 24.5));
    }
 
    @Override
    public LivingEntity spawnEntity(Location location) {
        return WeakSkeleton.spawn(location).getLivingEntity();
    }
 
}