package me.pvpnik.emmber.core.portals;

import me.pvpnik.emmber.Emmber;
import me.pvpnik.emmber.api.portals.EmmberPortal;
import org.bukkit.Location;

public class Level84 extends EmmberPortal {

    public Level84() {
        super("Level84", new Location(Emmber.getInstance().getWorld(), 3, 87, 94),
                new Location(Emmber.getInstance().getWorld(), -3, 76, 94));

        // if player can not use the portal it will teleport him to the deny location
        setDeny(new Location(Emmber.getInstance().getWorld(), 0, 79, 104));

        // Player Must Have:
        setPermission("");
        setFeeInPoints(0);
        setPrizeInPoints(0);
        setMinlevel(8);

        // Extra
        setMessage("");
        setTitle("");
        setSubtitle("");
        setDenyMessage(""); // the only thing fires besides deny location
    }
}
