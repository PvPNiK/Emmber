package me.pvpnik.emmber.spawners.continuation;
 
import me.pvpnik.emmber.Emmber;
import me.pvpnik.emmber.api.spawner.EntitySpawner;
import me.pvpnik.emmber.mobs.regular.skeleton.NormalSkeleton;
import org.bukkit.Location;
import org.bukkit.entity.LivingEntity;
 
public class NormalSkeletonContinuation4 extends EntitySpawner {
 
    public NormalSkeletonContinuation4() {
        super("NormalSkeletonContinuation4");
        setMaxspawn(5);
        setCooldown(6);
        setSpawning(true);
        addLocation(new Location(Emmber.getInstance().getWorld(),106.5, 78.2, -86.5));
        addLocation(new Location(Emmber.getInstance().getWorld(),106.5, 75.2, -58.5));
        addLocation(new Location(Emmber.getInstance().getWorld(),99.5, 77.2, -43.5));
        addLocation(new Location(Emmber.getInstance().getWorld(),123.5, 80.2, -62.5));
 
    }
 
    @Override
    public LivingEntity spawnEntity(Location location) {
        return NormalSkeleton.spawn(location).getLivingEntity();
    }
 
}