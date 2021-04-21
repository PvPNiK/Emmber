package me.pvpnik.emmber.spawners.sandmaze;

import me.pvpnik.emmber.Emmber;
import me.pvpnik.emmber.api.spawner.EntitySpawner;
import me.pvpnik.emmber.mobs.regular.skeleton.SandSkeleton;
import me.pvpnik.emmber.mobs.regular.skeleton.WeakSkeleton;
import org.bukkit.Location;
import org.bukkit.entity.LivingEntity;

public class SandMazeSkeleton12 extends EntitySpawner {
 
    public SandMazeSkeleton12() {
        super("SandMazeSkeleton12");
        setMaxspawn(3);
        setCooldown(6);
        setSpawning(true);
        addLocation(new Location(Emmber.getInstance().getWorld(), -166.5, 78.2, -151.5));
        addLocation(new Location(Emmber.getInstance().getWorld(), -189.5, 81.2, -156.5));
        addLocation(new Location(Emmber.getInstance().getWorld(), -200.5, 78.2, -181.5));
        addLocation(new Location(Emmber.getInstance().getWorld(), -206.5, 79.2, -197.5));
    }
 
    @Override
    public LivingEntity spawnEntity(Location location) {
        return SandSkeleton.spawn(location).getLivingEntity();
    }
 
}