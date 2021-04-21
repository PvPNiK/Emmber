package me.pvpnik.emmber.core.portals;

import me.pvpnik.emmber.Emmber;
import me.pvpnik.emmber.api.portals.EmmberPortal;
import org.bukkit.Location;

public class Hero extends EmmberPortal {

    public Hero() {
        super("Hero", new Location(Emmber.getInstance().getWorld(), -42, 75, -19),
                new Location(Emmber.getInstance().getWorld(), -42, 80, -25));

        // if player can not use the portal it will teleport him to the deny location
        setDeny(new Location(Emmber.getInstance().getWorld(), -37, 75, -22));

        // Player Must Have:
        setPermission("portal.hero");
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
