package me.pvpnik.emmber.core.portals;

import me.pvpnik.emmber.Emmber;
import me.pvpnik.emmber.api.portals.EmmberPortal;
import org.bukkit.Location;

public class SandLevel extends EmmberPortal {

    public SandLevel() {
        super("SandLevel", new Location(Emmber.getInstance().getWorld(), -1575, 97, -265),
                new Location(Emmber.getInstance().getWorld(), -1575, 93, -267));

        // if player can use portal, it will teleport him to the output
        setOutput(new Location(Emmber.getInstance().getWorld(), -1612, 93, 32));
        // if player can not use the portal it will teleport him to the deny location
        setDeny(new Location(Emmber.getInstance().getWorld(), -1570, 93, -266));

        // Player Must Have:
        setPermission("");
        setFeeInPoints(0);
        setPrizeInPoints(30);
        setMinlevel(0);

        // Extra
        setMessage("");
        setTitle("");
        setSubtitle("");
        setDenyMessage(""); // the only thing fires besides deny location
    }
}
