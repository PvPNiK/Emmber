package me.pvpnik.emmber.core;

import me.pvpnik.emmber.Emmber;
import org.bukkit.Location;

public enum Checkpoint {

    EssverHospital(new Location(Emmber.getInstance().getWorld(), 0.5, 83.2, 40.5, 180, 0));

    private Location location;

    Checkpoint(Location location) {
        this.location = location;
    }

    public Location getLocation() {
        return location;
    }
}
