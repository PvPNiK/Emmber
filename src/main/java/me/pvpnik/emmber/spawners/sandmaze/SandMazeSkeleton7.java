package me.pvpnik.emmber.spawners.sandmaze;

import me.pvpnik.emmber.Emmber;
import me.pvpnik.emmber.api.spawner.EntitySpawner;
import me.pvpnik.emmber.mobs.regular.skeleton.SandSkeleton;
import me.pvpnik.emmber.mobs.regular.skeleton.WeakSkeleton;
import org.bukkit.Location;
import org.bukkit.entity.LivingEntity;

public class SandMazeSkeleton7 extends EntitySpawner {
 
    public SandMazeSkeleton7() {
        super("SandMazeSkeleton7");
        setMaxspawn(3);
        setCooldown(6);
        setSpawning(true);
        addLocation(new Location(Emmber.getInstance().getWorld(), -230.5, 81.2, -119.5));
        addLocation(new Location(Emmber.getInstance().getWorld(), -250.5, 79.2, -120.5));
        addLocation(new Location(Emmber.getInstance().getWorld(), -231.5, 81.2, -145.5));
        addLocation(new Location(Emmber.getInstance().getWorld(), -220.5, 83.2, -164.5));
    }
 
    @Override
    public LivingEntity spawnEntity(Location location) {
        return SandSkeleton.spawn(location).getLivingEntity();
    }
 
}