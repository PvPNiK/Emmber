package me.pvpnik.emmber.spawners.sandmaze;

import me.pvpnik.emmber.Emmber;
import me.pvpnik.emmber.api.spawner.EntitySpawner;
import me.pvpnik.emmber.mobs.regular.skeleton.SandSkeleton;
import me.pvpnik.emmber.mobs.regular.skeleton.WeakSkeleton;
import org.bukkit.Location;
import org.bukkit.entity.LivingEntity;

public class SandMazeSkeleton9 extends EntitySpawner {
 
    public SandMazeSkeleton9() {
        super("SandMazeSkeleton9");
        setMaxspawn(3);
        setCooldown(6);
        setSpawning(true);
        addLocation(new Location(Emmber.getInstance().getWorld(), -201.5, 77.2, -217.5));
        addLocation(new Location(Emmber.getInstance().getWorld(), -190.5, 77.2, -220.5));
        addLocation(new Location(Emmber.getInstance().getWorld(), -174.5, 77.2, -216.5));
        addLocation(new Location(Emmber.getInstance().getWorld(), -188.5, 78.2, -200.5));
    }
 
    @Override
    public LivingEntity spawnEntity(Location location) {
        return SandSkeleton.spawn(location).getLivingEntity();
    }
 
}