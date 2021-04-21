package me.pvpnik.emmber.spawners.sandmaze;

import me.pvpnik.emmber.Emmber;
import me.pvpnik.emmber.api.spawner.EntitySpawner;
import me.pvpnik.emmber.mobs.regular.skeleton.SandSkeleton;
import me.pvpnik.emmber.mobs.regular.skeleton.WeakSkeleton;
import org.bukkit.Location;
import org.bukkit.entity.LivingEntity;

public class SandMazeSkeleton1 extends EntitySpawner {
 
    public SandMazeSkeleton1() {
        super("SandMazeSkeleton1");
        setMaxspawn(3);
        setCooldown(6);
        setSpawning(true);
        addLocation(new Location(Emmber.getInstance().getWorld(), -82.5, 78.2, -228.5));
        addLocation(new Location(Emmber.getInstance().getWorld(), -70.5, 80.2, -215.5));
        addLocation(new Location(Emmber.getInstance().getWorld(), -74.5, 80.2, -205.5));
        addLocation(new Location(Emmber.getInstance().getWorld(), -70.5, 80.2, -196.5));
    }
 
    @Override
    public LivingEntity spawnEntity(Location location) {
        return SandSkeleton.spawn(location).getLivingEntity();
    }
 
}