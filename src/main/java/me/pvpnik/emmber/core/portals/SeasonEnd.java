package me.pvpnik.emmber.core.portals;

import me.pvpnik.emmber.Emmber;
import me.pvpnik.emmber.api.portals.EmmberPortal;
import org.bukkit.Location;

public class SeasonEnd extends EmmberPortal {

    public SeasonEnd() {
        super("SeasonEnd", new Location(Emmber.getInstance().getWorld(), -1046, 126, -298),
                new Location(Emmber.getInstance().getWorld(), -1038, 113, -298));

        // if player can use portal, it will teleport him to the output
        setOutput(new Location(Emmber.getInstance().getWorld(), 0, 83, 40));
        // if player can not use the portal it will teleport him to the deny location
        setDeny(new Location(Emmber.getInstance().getWorld(), -1042, 113, -294));

        // Player Must Have:
        setPermission("");
        setFeeInPoints(0);
        setPrizeInPoints(120);
        setMinlevel(0);

        // Extra
        setMessage("");
        setTitle("");
        setSubtitle("");
        setDenyMessage(""); // the only thing fires besides deny location
    }
}
