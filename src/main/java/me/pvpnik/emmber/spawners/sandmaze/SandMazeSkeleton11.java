package me.pvpnik.emmber.spawners.sandmaze;

import me.pvpnik.emmber.Emmber;
import me.pvpnik.emmber.api.spawner.EntitySpawner;
import me.pvpnik.emmber.mobs.regular.skeleton.SandSkeleton;
import me.pvpnik.emmber.mobs.regular.skeleton.WeakSkeleton;
import org.bukkit.Location;
import org.bukkit.entity.LivingEntity;

public class SandMazeSkeleton11 extends EntitySpawner {
 
    public SandMazeSkeleton11() {
        super("SandMazeSkeleton11");
        setMaxspawn(3);
        setCooldown(6);
        setSpawning(true);
        addLocation(new Location(Emmber.getInstance().getWorld(), -129.5, 77.2, -208.5));
        addLocation(new Location(Emmber.getInstance().getWorld(), -112.5, 79.2, -192.5));
        addLocation(new Location(Emmber.getInstance().getWorld(), -117.5, 79.2, -178.5));
        addLocation(new Location(Emmber.getInstance().getWorld(), -145.5, 78.2, -163.5));
    }
 
    @Override
    public LivingEntity spawnEntity(Location location) {
        return SandSkeleton.spawn(location).getLivingEntity();
    }
 
}