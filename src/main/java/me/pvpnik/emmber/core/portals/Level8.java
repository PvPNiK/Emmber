package me.pvpnik.emmber.core.portals;

import me.pvpnik.emmber.Emmber;
import me.pvpnik.emmber.api.portals.EmmberPortal;
import org.bukkit.Location;

public class Level8 extends EmmberPortal {

    public Level8() {
        super("Level8", new Location(Emmber.getInstance().getWorld(), -25, 83, 71),
                new Location(Emmber.getInstance().getWorld(), -25, 77, 66));

        // if player can not use the portal it will teleport him to the deny location
        setDeny(new Location(Emmber.getInstance().getWorld(), -31, 78, 69));

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
