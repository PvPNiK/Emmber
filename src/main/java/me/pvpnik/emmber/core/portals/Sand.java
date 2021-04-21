package me.pvpnik.emmber.core.portals;

import me.pvpnik.emmber.Emmber;
import me.pvpnik.emmber.api.portals.EmmberPortal;
import org.bukkit.Location;

public class Sand extends EmmberPortal {

    public Sand() {
        super("Sand", new Location(Emmber.getInstance().getWorld(), -251, 80, -51),
                new Location(Emmber.getInstance().getWorld(), -251, 86, -55));

        // if player can not use the portal it will teleport him to the deny location
        setDeny(new Location(Emmber.getInstance().getWorld(), -246, 79, -54));

        // Player Must Have:
        setPermission("portal.sand");
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
