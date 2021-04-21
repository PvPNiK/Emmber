package me.pvpnik.emmber.spawners.continuation;
 
import me.pvpnik.emmber.Emmber;
import me.pvpnik.emmber.api.spawner.EntitySpawner;
import me.pvpnik.emmber.mobs.regular.skeleton.NormalSkeleton;
import org.bukkit.Location;
import org.bukkit.entity.LivingEntity;
 
public class NormalSkeletonContinuation3 extends EntitySpawner {
 
    public NormalSkeletonContinuation3() {
        super("NormalSkeletonContinuation3");
        setMaxspawn(5);
        setCooldown(6);
        setSpawning(true);
        addLocation(new Location(Emmber.getInstance().getWorld(),55.5, 74.2, -66.5));
        addLocation(new Location(Emmber.getInstance().getWorld(),74.5, 75.2, -70.5));
        addLocation(new Location(Emmber.getInstance().getWorld(),77.5, 77.2, -42.5));
		addLocation(new Location(Emmber.getInstance().getWorld(),97.5, 75.2, -54.5));
		addLocation(new Location(Emmber.getInstance().getWorld(),102.5, 76.2, -72.5));
 
    }
 
    @Override
    public LivingEntity spawnEntity(Location location) {
        return NormalSkeleton.spawn(location).getLivingEntity();
    }
 
}