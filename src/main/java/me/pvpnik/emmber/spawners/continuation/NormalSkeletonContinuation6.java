package me.pvpnik.emmber.spawners.continuation;
 
import me.pvpnik.emmber.Emmber;
import me.pvpnik.emmber.api.spawner.EntitySpawner;
import me.pvpnik.emmber.mobs.regular.skeleton.NormalSkeleton;
import org.bukkit.Location;
import org.bukkit.entity.LivingEntity;
 
public class NormalSkeletonContinuation6 extends EntitySpawner {
 
    public NormalSkeletonContinuation6() {
        super("NormalSkeletonContinuation6");
        setMaxspawn(5);
        setCooldown(6);
        setSpawning(true);
        addLocation(new Location(Emmber.getInstance().getWorld(),104.5, 84.2, 24.5));
        addLocation(new Location(Emmber.getInstance().getWorld(),104.5, 82.2, 8.5));
        addLocation(new Location(Emmber.getInstance().getWorld(),106.5, 78.2, -25.5));
        addLocation(new Location(Emmber.getInstance().getWorld(),116.5, 77.2, -47.5));
 
    }
 
    @Override
    public LivingEntity spawnEntity(Location location) {
        return NormalSkeleton.spawn(location).getLivingEntity();
    }
 
}