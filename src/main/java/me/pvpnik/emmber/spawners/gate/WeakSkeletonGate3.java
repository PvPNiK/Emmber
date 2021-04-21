package me.pvpnik.emmber.spawners.gate;

import me.pvpnik.emmber.Emmber;
import me.pvpnik.emmber.api.spawner.EntitySpawner;
import me.pvpnik.emmber.mobs.regular.skeleton.WeakSkeleton;
import org.bukkit.Location;
import org.bukkit.entity.LivingEntity;

public class WeakSkeletonGate3 extends EntitySpawner {
 
    public WeakSkeletonGate3() {
        super("WeakSkeletonGate3");
        setMaxspawn(5);
        setCooldown(6);
        setSpawning(true);
        addLocation(new Location(Emmber.getInstance().getWorld(),-90.5, 82.2, 12.5));
        addLocation(new Location(Emmber.getInstance().getWorld(),-98.5, 80.2, 6.5));
        addLocation(new Location(Emmber.getInstance().getWorld(),-81.5, 79.2, -11.5));
        addLocation(new Location(Emmber.getInstance().getWorld(),-106.5, 77.2, -11.5));
    }
 
    @Override
    public LivingEntity spawnEntity(Location location) {
        return WeakSkeleton.spawn(location).getLivingEntity();
    }
 
}