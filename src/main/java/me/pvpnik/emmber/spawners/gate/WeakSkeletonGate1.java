package me.pvpnik.emmber.spawners.gate;

import me.pvpnik.emmber.Emmber;
import me.pvpnik.emmber.api.spawner.EntitySpawner;
import me.pvpnik.emmber.mobs.regular.skeleton.WeakSkeleton;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.LivingEntity;

public class WeakSkeletonGate1 extends EntitySpawner {
 
    public WeakSkeletonGate1() {
        super("WeakSkeletonGate1");
        setMaxspawn(5);
        setCooldown(6);
        setSpawning(true);
        addLocation(new Location(Emmber.getInstance().getWorld(),-16.5, 76.2, -65.5));
        addLocation(new Location(Emmber.getInstance().getWorld(),-8.5, 75.2, -60.5));
        addLocation(new Location(Emmber.getInstance().getWorld(),9.5, 77.2, -71));
		addLocation(new Location(Emmber.getInstance().getWorld(),15.5, 80.2, -55.5));
        
    }
 
    @Override
    public LivingEntity spawnEntity(Location location) {
        return WeakSkeleton.spawn(location).getLivingEntity();
    }
 
}