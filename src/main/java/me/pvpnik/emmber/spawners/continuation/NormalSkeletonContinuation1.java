package me.pvpnik.emmber.spawners.continuation;
 
import me.pvpnik.emmber.Emmber;
import me.pvpnik.emmber.api.spawner.EntitySpawner;
import me.pvpnik.emmber.mobs.regular.skeleton.NormalSkeleton;
import org.bukkit.Location;
import org.bukkit.entity.LivingEntity;

public class NormalSkeletonContinuation1 extends EntitySpawner {
 
    public NormalSkeletonContinuation1() {
        super("NormalSkeletonContinuation1");
        setMaxspawn(5);
        setCooldown(6);
        setSpawning(true);
        addLocation(new Location(Emmber.getInstance().getWorld(),3.5, 82.2, -96.5));
        addLocation(new Location(Emmber.getInstance().getWorld(),-9.5, 81.2, -103.5));
        addLocation(new Location(Emmber.getInstance().getWorld(),-21.5, 79.2, -107.5));
        addLocation(new Location(Emmber.getInstance().getWorld(),-14.5, 77.2, -95.5));
		addLocation(new Location(Emmber.getInstance().getWorld(),-15.5, 74.2, -76.5));
    }
 
    @Override
    public LivingEntity spawnEntity(Location location) {
        return NormalSkeleton.spawn(location).getLivingEntity();
    }
 
}