package me.pvpnik.emmber.core.portals;

import me.pvpnik.emmber.Emmber;
import me.pvpnik.emmber.api.portals.EmmberPortal;
import org.bukkit.Location;

public class ToCastle extends EmmberPortal {

    public ToCastle() {
        super("ToCastle", new Location(Emmber.getInstance().getWorld(), -352, 128, -1459),
                new Location(Emmber.getInstance().getWorld(), -352, 119, -1464));

        // if player can use portal, it will teleport him to the output
        setOutput(new Location(Emmber.getInstance().getWorld(), 0, 83, 40));
        // if player can not use the portal it will teleport him to the deny location
        setDeny(new Location(Emmber.getInstance().getWorld(), 0, 83, 40));

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
