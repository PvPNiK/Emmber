package me.pvpnik.emmber.spawners.sandmaze;

import me.pvpnik.emmber.Emmber;
import me.pvpnik.emmber.api.spawner.EntitySpawner;
import me.pvpnik.emmber.mobs.regular.skeleton.SandSkeleton;
import me.pvpnik.emmber.mobs.regular.skeleton.WeakSkeleton;
import org.bukkit.Location;
import org.bukkit.entity.LivingEntity;

public class SandMazeSkeleton4 extends EntitySpawner {
 
    public SandMazeSkeleton4() {
        super("SandMazeSkeleton4");
        setMaxspawn(3);
        setCooldown(6);
        setSpawning(true);
        addLocation(new Location(Emmber.getInstance().getWorld(), -136.5, 82.2, -45.5));
        addLocation(new Location(Emmber.getInstance().getWorld(), -153.5, 79.2, -46.5));
        addLocation(new Location(Emmber.getInstance().getWorld(), -165.5, 76.2, -49.5));
        addLocation(new Location(Emmber.getInstance().getWorld(), -155.5, 76.2, -59.5));
    }
 
    @Override
    public LivingEntity spawnEntity(Location location) {
        return SandSkeleton.spawn(location).getLivingEntity();
    }
 
}