package me.pvpnik.emmber.core.portals;

import me.pvpnik.emmber.Emmber;
import me.pvpnik.emmber.api.portals.EmmberPortal;
import org.bukkit.Location;

public class WaterMaze extends EmmberPortal {

    public WaterMaze() {
        super("WaterMaze", new Location(Emmber.getInstance().getWorld(), -1673, 102, 82),
                new Location(Emmber.getInstance().getWorld(), -1676, 98, 82));

        // if player can use portal, it will teleport him to the output
        setOutput(new Location(Emmber.getInstance().getWorld(), 0, 83, 40));
        // if player can not use the portal it will teleport him to the deny location
        setDeny(new Location(Emmber.getInstance().getWorld(), -1674, 98, 79));

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
