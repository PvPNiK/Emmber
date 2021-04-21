package me.pvpnik.emmber.core.portals;

import me.pvpnik.emmber.Emmber;
import me.pvpnik.emmber.api.portals.EmmberPortal;
import org.bukkit.Location;

public class ToChallenge extends EmmberPortal {

    public ToChallenge() {
        super("ToChallenge", new Location(Emmber.getInstance().getWorld(), -296, 128, -1464),
                new Location(Emmber.getInstance().getWorld(), -296, 119, -1459));

        // if player can use portal, it will teleport him to the output
        setOutput(new Location(Emmber.getInstance().getWorld(), -1573, 87, -214));
        // if player can not use the portal it will teleport him to the deny location
        setDeny(new Location(Emmber.getInstance().getWorld(), -324, 119, -1462));

        // Player Must Have:
        setPermission("");
        setFeeInPoints(15);
        setPrizeInPoints(0);
        setMinlevel(0);

        // Extra
        setMessage("");
        setTitle("");
        setSubtitle("");
        setDenyMessage(""); // the only thing fires besides deny location
    }
}
