package me.pvpnik.emmber.core.prizepoints;

import me.pvpnik.emmber.Emmber;
import me.pvpnik.emmber.api.prizepoints.Prizepoint;
import org.bukkit.Location;

import javax.annotation.Nullable;

public enum Prizepoints {
    hospital(new Prizepoint("hospital", new Location(Emmber.getInstance().getWorld(), 47, 81, 77), 20, 10)),
    bank(new Prizepoint("bank", new Location(Emmber.getInstance().getWorld(), 48, 78, -18), 30, 12)),
    sahar(new Prizepoint("sahar", new Location(Emmber.getInstance().getWorld(), 0, 77, -52), 60, 24)),
    royal(new Prizepoint("royal", new Location(Emmber.getInstance().getWorld(), 9, 89, 40), 60, 12)),
    staff(new Prizepoint("staff", new Location(Emmber.getInstance().getWorld(), -9, 89, 39), 60, 12)),
    season(new Prizepoint("season", new Location(Emmber.getInstance().getWorld(), 36, 82, -112), 50, 24)),
    sand(new Prizepoint("sand", new Location(Emmber.getInstance().getWorld(), -285, 76, -43), 120, 36)),
    earth(new Prizepoint("earth", new Location(Emmber.getInstance().getWorld(), 251, 86, 147), 120, 36)),
    mikdash(new Prizepoint("mikdash", new Location(Emmber.getInstance().getWorld(), 398, 26, 253), 120, 36));

    public Prizepoint prizepoint;

    Prizepoints(Prizepoint prizepoint) {
        this.prizepoint = prizepoint;
    }

    @Nullable
    public static Prizepoints getEnum(Prizepoint prizepoint) {
        for (Prizepoints prizepoints : values()) {
            if (prizepoints.prizepoint.equals(prizepoint)) {
                return prizepoints;
            }
        }
        return null;
    }

}
