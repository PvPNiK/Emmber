package me.pvpnik.emmber.core.portals;

import me.pvpnik.emmber.Emmber;
import me.pvpnik.emmber.api.portals.EmmberPortal;
import org.bukkit.Location;

public class Staff2 extends EmmberPortal {

    public Staff2() {
        super("Staff2", new Location(Emmber.getInstance().getWorld(), -6, 94, 33),
                new Location(Emmber.getInstance().getWorld(), -6, 100, 33));

        // if player can not use the portal it will teleport him to the deny location
        setDeny(new Location(Emmber.getInstance().getWorld(), -4, 95, 36));

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
