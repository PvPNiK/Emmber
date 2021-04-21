package me.pvpnik.emmber.api.portals;

import me.pvpnik.emmber.api.jobs.EmmberJob;
import net.minecraft.server.v1_12_R1.AxisAlignedBB;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.event.player.PlayerMoveEvent;

public abstract class Portal {

    private final String id;

    private Location pos1, pos2;
    private PortalBoundingBox box;

    public Portal(String id, Location pos1, Location pos2) {
        this.id = id;
        this.pos1 = pos1;
        this.pos2 = pos2;
        box = new PortalBoundingBox(pos1.getX(), pos1.getY(), pos1.getZ(), pos2.getX(), pos2.getY(), pos2.getZ());
    }

    /*
    public boolean a(double x1, double y1, double z1, double x2, double y2, double z2) {
        return this.x1 < x2 && this.x2 > x1 && this.y1 < y2 && this.y2 > y1 && this.z1 < z2 && this.z2 > z1;
    }

    private boolean overlaps(double minX, double minY, double minZ, double maxX, double maxY, double maxZ) {
        return this.minX < maxX && this.maxX > minX && this.minY < maxY && this.maxY > minY && this.minZ < maxZ && this.maxZ > minZ;
    }

     */

    public boolean isInPortal(AxisAlignedBB boundingBox) {
        //return boundingBox.c(box);
        return box.overlaps(boundingBox);
    }

    public abstract void execute(EmmberJob player, PlayerMoveEvent event);

    public String getId() {
        return id;
    }

    public void buildBarriers() {
        int y = 254;
        World world = pos1.getWorld();
        for (double x = box.minX; x <= box.maxX; x++) {
            for (double z = box.minZ; z <= box.maxZ; z++) {
                world.getBlockAt((int) x, y, (int) z).setType(Material.BARRIER);
            }
        }
    }

}
