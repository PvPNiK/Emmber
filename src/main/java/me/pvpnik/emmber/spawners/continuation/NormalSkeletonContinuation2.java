package me.pvpnik.emmber.spawners.continuation;
 
import me.pvpnik.emmber.Emmber;
import me.pvpnik.emmber.api.spawner.EntitySpawner;
import me.pvpnik.emmber.mobs.regular.skeleton.NormalSkeleton;
import org.bukkit.Location;
import org.bukkit.entity.LivingEntity;
 
public class NormalSkeletonContinuation2 extends EntitySpawner {
 
    public NormalSkeletonContinuation2() {
        super("NormalSkeletonContinuation2");
        setMaxspawn(5);
        setCooldown(6);
        setSpawning(true);
        addLocation(new Location(Emmber.getInstance().getWorld(),-32.5, 76.2, -69.5));
        addLocation(new Location(Emmber.getInstance().getWorld(),-51.5, 77.2, -65.5));
        addLocation(new Location(Emmber.getInstance().getWorld(),-28.5, 77.2, -54.5));
 
    }
 
    @Override
    public LivingEntity spawnEntity(Location location) {
        return NormalSkeleton.spawn(location).getLivingEntity();
    }
 
}