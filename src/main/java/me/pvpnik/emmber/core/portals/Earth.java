package me.pvpnik.emmber.core.portals;

import me.pvpnik.emmber.Emmber;
import me.pvpnik.emmber.api.portals.EmmberPortal;
import org.bukkit.Location;

public class Earth extends EmmberPortal {

    public Earth() {
        super("Earth", new Location(Emmber.getInstance().getWorld(), 251, 85, 146),
                new Location(Emmber.getInstance().getWorld(), 251, 94, 148));

        // if player can not use the portal it will teleport him to the deny location
        setDeny(new Location(Emmber.getInstance().getWorld(), 245, 86, 147));

        // Player Must Have:
        setPermission("portal.earth");
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
