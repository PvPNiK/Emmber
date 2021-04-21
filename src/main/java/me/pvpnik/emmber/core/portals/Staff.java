package me.pvpnik.emmber.core.portals;

import me.pvpnik.emmber.Emmber;
import me.pvpnik.emmber.api.portals.EmmberPortal;
import org.bukkit.Location;

public class Staff extends EmmberPortal {

    public Staff() {
        super("Staff", new Location(Emmber.getInstance().getWorld(), -10, 88, 41),
                new Location(Emmber.getInstance().getWorld(), -10, 111, 38));

        // if player can not use the portal it will teleport him to the deny location
        setDeny(new Location(Emmber.getInstance().getWorld(), -20, 82, 40));

        // Player Must Have:
        setPermission("portal.staff");
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
