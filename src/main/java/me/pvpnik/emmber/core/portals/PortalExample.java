package me.pvpnik.emmber.core.portals;

import me.pvpnik.emmber.Emmber;
import me.pvpnik.emmber.api.portals.EmmberPortal;
import org.bukkit.Location;

public class PortalExample extends EmmberPortal {

    public PortalExample() {
        super("PortalExample", new Location(Emmber.getInstance().getWorld(), 0, 0, 0),
                new Location(Emmber.getInstance().getWorld(), 0, 0, 0));

        // if player can use portal, it will teleport him to the output
        setOutput(new Location(Emmber.getInstance().getWorld(), 0, 0, 0));
        // if player can not use the portal it will teleport him to the deny location
        setDeny(new Location(Emmber.getInstance().getWorld(), 0, 0, 0));

        // Player Must Have:
        setPermission("");
        setFeeInPoints(0);
        setMinlevel(0);

        // Extra
        setMessage("");
        setTitle("");
        setSubtitle("");
        setDenyMessage(""); // the only thing fires besides deny location
    }
}
