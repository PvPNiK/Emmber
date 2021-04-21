package me.pvpnik.emmber.spawners.sandmaze;

import me.pvpnik.emmber.Emmber;
import me.pvpnik.emmber.api.spawner.EntitySpawner;
import me.pvpnik.emmber.mobs.regular.skeleton.SandSkeleton;
import me.pvpnik.emmber.mobs.regular.skeleton.WeakSkeleton;
import org.bukkit.Location;
import org.bukkit.entity.LivingEntity;

public class SandMazeSkeleton6 extends EntitySpawner {
 
    public SandMazeSkeleton6() {
        super("SandMazeSkeleton6");
        setMaxspawn(3);
        setCooldown(6);
        setSpawning(true);
        addLocation(new Location(Emmber.getInstance().getWorld(), -212.5, 77.2, -67.5));
        addLocation(new Location(Emmber.getInstance().getWorld(), -225.5, 79.2, -90.5));
        addLocation(new Location(Emmber.getInstance().getWorld(), -231.5, 78.2, -83.5));
        addLocation(new Location(Emmber.getInstance().getWorld(), -231.5, 78.2, -99.5));
    }
 
    @Override
    public LivingEntity spawnEntity(Location location) {
        return SandSkeleton.spawn(location).getLivingEntity();
    }
 
}