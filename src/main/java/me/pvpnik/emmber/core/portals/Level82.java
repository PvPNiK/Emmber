package me.pvpnik.emmber.core.portals;

import me.pvpnik.emmber.Emmber;
import me.pvpnik.emmber.api.portals.EmmberPortal;
import org.bukkit.Location;

public class Level82 extends EmmberPortal {

    public Level82() {
        super("Level82", new Location(Emmber.getInstance().getWorld(), 36, 88, 73).add(-0.5,0.5, 0.5),
                new Location(Emmber.getInstance().getWorld(), 36, 76, 63).add(0.5, -0.5, -0.5));

        // if player can not use the portal it will teleport him to the deny location
        setDeny(new Location(Emmber.getInstance().getWorld(), 39, 76, 68));

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
