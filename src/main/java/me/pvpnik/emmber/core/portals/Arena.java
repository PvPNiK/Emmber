package me.pvpnik.emmber.core.portals;

import me.pvpnik.emmber.Emmber;
import me.pvpnik.emmber.api.portals.EmmberPortal;
import org.bukkit.Location;

public class Arena extends EmmberPortal {

    public Arena() {
        super("Arena", new Location(Emmber.getInstance().getWorld(), 638, 107, -115),
                new Location(Emmber.getInstance().getWorld(), 643, 97, -115));

        // if player can use portal, it will teleport him to the output
        setOutput(new Location(Emmber.getInstance().getWorld(), -19, 82, 28));
        // if player can not use the portal it will teleport him to the deny location
        setDeny(new Location(Emmber.getInstance().getWorld(), 640, 98, -117));

        // Player Must Have:
        setPermission("");
        setFeeInPoints(0);
        setPrizeInPoints(0);
        setMinlevel(0);

        // Extra
        setMessage("");
        setTitle("");
        setSubtitle("");
        setDenyMessage(""); // the only thing fires besides deny location
    }
}
