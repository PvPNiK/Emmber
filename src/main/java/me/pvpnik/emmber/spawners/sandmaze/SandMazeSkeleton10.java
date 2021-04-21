package me.pvpnik.emmber.spawners.sandmaze;

import me.pvpnik.emmber.Emmber;
import me.pvpnik.emmber.api.spawner.EntitySpawner;
import me.pvpnik.emmber.mobs.regular.skeleton.SandSkeleton;
import me.pvpnik.emmber.mobs.regular.skeleton.WeakSkeleton;
import org.bukkit.Location;
import org.bukkit.entity.LivingEntity;

public class SandMazeSkeleton10 extends EntitySpawner {
 
    public SandMazeSkeleton10() {
        super("SandMazeSkeleton10");
        setMaxspawn(3);
        setCooldown(6);
        setSpawning(true);
        addLocation(new Location(Emmber.getInstance().getWorld(), -207.5, 82.2, -157.5));
        addLocation(new Location(Emmber.getInstance().getWorld(), -189.5, 81.2, -150.5));
        addLocation(new Location(Emmber.getInstance().getWorld(), -165.5, 77.2, -158.5));
        addLocation(new Location(Emmber.getInstance().getWorld(), -175.5, 77.2, -162.5));
    }
 
    @Override
    public LivingEntity spawnEntity(Location location) {
        return SandSkeleton.spawn(location).getLivingEntity();
    }
 
}