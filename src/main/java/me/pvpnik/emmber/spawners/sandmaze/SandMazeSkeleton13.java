package me.pvpnik.emmber.spawners.sandmaze;

import me.pvpnik.emmber.Emmber;
import me.pvpnik.emmber.api.spawner.EntitySpawner;
import me.pvpnik.emmber.mobs.regular.skeleton.SandSkeleton;
import me.pvpnik.emmber.mobs.regular.skeleton.WeakSkeleton;
import org.bukkit.Location;
import org.bukkit.entity.LivingEntity;

public class SandMazeSkeleton13 extends EntitySpawner {
 
    public SandMazeSkeleton13() {
        super("SandMazeSkeleton13");
        setMaxspawn(3);
        setCooldown(6);
        setSpawning(true);
        addLocation(new Location(Emmber.getInstance().getWorld(), -196.5, 77.2, -212.5));
        addLocation(new Location(Emmber.getInstance().getWorld(), -201.5, 78.2, -202.5));
        addLocation(new Location(Emmber.getInstance().getWorld(), -199.5, 80.2, -175.5));
        addLocation(new Location(Emmber.getInstance().getWorld(), -183.5, 79.2, -153.5));
    }
 
    @Override
    public LivingEntity spawnEntity(Location location) {
        return SandSkeleton.spawn(location).getLivingEntity();
    }
 
}