package me.pvpnik.emmber.core.portals;

import me.pvpnik.emmber.Emmber;
import me.pvpnik.emmber.api.portals.EmmberPortal;
import org.bukkit.Location;

public class SandLevel1 extends EmmberPortal {

    public SandLevel1() {
        super("SandLevel1", new Location(Emmber.getInstance().getWorld(), -1565, 97, -265),
                new Location(Emmber.getInstance().getWorld(), -1565, 93, -267));

        // if player can use portal, it will teleport him to the output
        setOutput(new Location(Emmber.getInstance().getWorld(), 0, 83, 40));
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
