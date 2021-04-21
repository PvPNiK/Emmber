package me.pvpnik.emmber.spawners.sandmaze;

import me.pvpnik.emmber.Emmber;
import me.pvpnik.emmber.api.spawner.EntitySpawner;
import me.pvpnik.emmber.mobs.regular.skeleton.SandSkeleton;
import me.pvpnik.emmber.mobs.regular.skeleton.WeakSkeleton;
import org.bukkit.Location;
import org.bukkit.entity.LivingEntity;

public class SandMazeSkeleton14 extends EntitySpawner {
 
    public SandMazeSkeleton14() {
        super("SandMazeSkeleton14");
        setMaxspawn(3);
        setCooldown(6);
        setSpawning(true);
        addLocation(new Location(Emmber.getInstance().getWorld(), -166.5, 83.2, -134.5));
        addLocation(new Location(Emmber.getInstance().getWorld(), -171.5, 82.2, -116.5));
        addLocation(new Location(Emmber.getInstance().getWorld(), -154.5, 78.2, -102.5));
        addLocation(new Location(Emmber.getInstance().getWorld(), -132.5, 75.2, -102.5));
    }
 
    @Override
    public LivingEntity spawnEntity(Location location) {
        return SandSkeleton.spawn(location).getLivingEntity();
    }
 
}