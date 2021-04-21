package me.pvpnik.emmber.spawners.continuation;
 
import me.pvpnik.emmber.Emmber;
import me.pvpnik.emmber.api.spawner.EntitySpawner;
import me.pvpnik.emmber.mobs.regular.skeleton.NormalSkeleton;
import org.bukkit.Location;
import org.bukkit.entity.LivingEntity;
 
public class NormalSkeletonContinuation5 extends EntitySpawner {
 
    public NormalSkeletonContinuation5() {
        super("NormalSkeletonContinuation5");
        setMaxspawn(5);
        setCooldown(6);
        setSpawning(true);
        addLocation(new Location(Emmber.getInstance().getWorld(),88.5, 77.2, -25.5));
        addLocation(new Location(Emmber.getInstance().getWorld(),74.5, 76.2, -14.5));
        addLocation(new Location(Emmber.getInstance().getWorld(),70.5, 77.2, 2.5));
        addLocation(new Location(Emmber.getInstance().getWorld(),88.5, 76.2, -2.5));
 
    }
 
    @Override
    public LivingEntity spawnEntity(Location location) {
        return NormalSkeleton.spawn(location).getLivingEntity();
    }
 
}