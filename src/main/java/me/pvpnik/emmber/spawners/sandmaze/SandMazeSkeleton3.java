package me.pvpnik.emmber.spawners.sandmaze;

import me.pvpnik.emmber.Emmber;
import me.pvpnik.emmber.api.spawner.EntitySpawner;
import me.pvpnik.emmber.mobs.regular.skeleton.SandSkeleton;
import me.pvpnik.emmber.mobs.regular.skeleton.WeakSkeleton;
import org.bukkit.Location;
import org.bukkit.entity.LivingEntity;

public class SandMazeSkeleton3 extends EntitySpawner {
 
    public SandMazeSkeleton3() {
        super("SandMazeSkeleton3");
        setMaxspawn(3);
        setCooldown(6);
        setSpawning(true);
        addLocation(new Location(Emmber.getInstance().getWorld(), -92.5, 75.2, -91.5));
        addLocation(new Location(Emmber.getInstance().getWorld(), -93.5, 79.2, -65.5));
        addLocation(new Location(Emmber.getInstance().getWorld(), -105.5, 75.2, -92.5));
        addLocation(new Location(Emmber.getInstance().getWorld(), -119.5, 82.2, -58.5));
    }
 
    @Override
    public LivingEntity spawnEntity(Location location) {
        return SandSkeleton.spawn(location).getLivingEntity();
    }
 
}