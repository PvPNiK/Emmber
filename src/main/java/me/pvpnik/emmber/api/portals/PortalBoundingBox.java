package me.pvpnik.emmber.api.portals;

import net.minecraft.server.v1_12_R1.AxisAlignedBB;

public class PortalBoundingBox {
    public final double minX;
    public final double minY;
    public final double minZ;
    public final double maxX;
    public final double maxY;
    public final double maxZ;

    public PortalBoundingBox(double var1, double var3, double var5, double var7, double var9, double var11) {
        this.minX = Math.min(var1, var7) - 0.5;
        this.minY = Math.min(var3, var9) - 0.5;
        this.minZ = Math.min(var5, var11) - 0.5;
        this.maxX = Math.max(var1, var7) + 0.5;
        this.maxY = Math.max(var3, var9) + 0.5;
        this.maxZ = Math.max(var5, var11) + 0.5;
    }

    public boolean overlaps(AxisAlignedBB var1) {
        return this.overlaps(var1.a, var1.b, var1.c, var1.d, var1.e, var1.f);
    }

    private boolean overlaps(double minX, double minY, double minZ, double maxX, double maxY, double maxZ) {
        return this.minX < maxX && this.maxX > minX && this.minY < maxY && this.maxY > minY && this.minZ < maxZ && this.maxZ > minZ;
    }

}
