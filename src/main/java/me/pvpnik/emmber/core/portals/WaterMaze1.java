package me.pvpnik.emmber.core.portals;

import me.pvpnik.emmber.Emmber;
import me.pvpnik.emmber.api.portals.EmmberPortal;
import org.bukkit.Location;

public class WaterMaze1 extends EmmberPortal {

    public WaterMaze1() {
        super("WaterMaze1", new Location(Emmber.getInstance().getWorld(), -1673, 103, 68),
                new Location(Emmber.getInstance().getWorld(), -1676, 98, 68));

        // if player can use portal, it will teleport him to the output
        setOutput(new Location(Emmber.getInstance().getWorld(), -1042, 113, -121));
        // if player can not use the portal it will teleport him to the deny location
        setDeny(new Location(Emmber.getInstance().getWorld(), -1675, 98, 71));

        // Player Must Have:
        setPermission("");
        setFeeInPoints(0);
        setPrizeInPoints(60);
        setMinlevel(0);

        // Extra
        setMessage("");
        setTitle("");
        setSubtitle("");
        setDenyMessage(""); // the only thing fires besides deny location
    }
}
