package me.pvpnik.emmber.core;

import com.gmail.filoghost.holograms.api.HolographicDisplaysAPI;
import com.gmail.filoghost.holographicdisplays.api.Hologram;
import com.gmail.filoghost.holographicdisplays.api.HologramsAPI;
import me.pvpnik.emmber.Emmber;
import org.bukkit.Bukkit;
import org.bukkit.Location;

import java.util.Iterator;
import java.util.List;

public class EmmberHolograms {

    EmmberHolograms() {

    }

    public void spawn(EmmberHologram holo) {
        despawn(holo);
        /*Hologram hologram = HologramsAPI.createHologram(Emmber.getInstance(), holo.location.clone().add(0, 3, 0));
        hologram.appendTextLine(holo.message);
        spawned.put(holo.getName(), hologram);*/
    }


    public void despawn(EmmberHologram holo) {
        //HologramsAPI.getHolograms(Emmber.getInstance()).remove()
    }

    public enum EmmberHologram {
        bakeressver(Emmber.getInstance().localFileManager.messages.getMessage("world.store.clickOnMe"), new Location(Bukkit.getWorlds().get(0), 289.5264, 95, 1232.4021)),
        essverweapon(Emmber.getInstance().localFileManager.messages.getMessage("world.store.clickOnMe"), new Location(Bukkit.getWorlds().get(0), 273.67260425080303, 95, 1231.699999988079)),
        victor(Emmber.getInstance().localFileManager.messages.getMessage("world.store.clickOnMe"), new Location(Bukkit.getWorlds().get(0), 298.9747306094597, 92.5, 1368.699999988079)),
        sheldon(Emmber.getInstance().localFileManager.messages.getMessage("world.store.clickOnMe"), new Location(Bukkit.getWorlds().get(0), 344.51145917559296, 92.0, 1300.4994680636885)),
        gandlaf(Emmber.getInstance().localFileManager.messages.getMessage("world.store.clickOnMe"), new Location(Bukkit.getWorlds().get(0), 248.69999998807907, 100.0, 1204.2369065057296)),
        miki(Emmber.getInstance().localFileManager.messages.getMessage("world.store.clickOnMe"), new Location(Bukkit.getWorlds().get(0), 443.1943276955824, 108.0625, 1346.4792712981812)),
        frogi(Emmber.getInstance().localFileManager.messages.getMessage("world.store.clickOnMe"), new Location(Bukkit.getWorlds().get(0), 685.3266027785185, 84.0, 1347.020876763566)),
        patrisha(Emmber.getInstance().localFileManager.messages.getMessage("world.store.clickOnMe"), new Location(Bukkit.getWorlds().get(0), 211.3223, 11.0, 1343.1756)),
        essverbaker(Emmber.getInstance().localFileManager.messages.getMessage("world.store.clickOnMe"), new Location(Bukkit.getWorlds().get(0), 282.0767, 102.0625, 1219.6884)),
        gary(Emmber.getInstance().localFileManager.messages.getMessage("world.store.clickOnMe"), new Location(Bukkit.getWorlds().get(0), 176.68260095305618, 96.0, 1344.2294181024456)),
        mati(Emmber.getInstance().localFileManager.messages.getMessage("world.store.clickOnMe"), new Location(Bukkit.getWorlds().get(0), 280.24682886159866, 96.0, 1167.5816985102047)),
        charlie(Emmber.getInstance().localFileManager.messages.getMessage("world.store.clickOnMe"), new Location(Bukkit.getWorlds().get(0), 267.1175, 102.0625, 1221.6929)),
        viki(Emmber.getInstance().localFileManager.messages.getMessage("world.store.clickOnMe"), new Location(Bukkit.getWorlds().get(0), 271.4753, 102.0625, 1234.4682)),
        chris(Emmber.getInstance().localFileManager.messages.getMessage("world.store.clickOnMe"), new Location(Bukkit.getWorlds().get(0), 265.6696, 102.0625, 1230.8611)),
        essverblacksmith(Emmber.getInstance().localFileManager.messages.getMessage("world.store.clickOnMe"), new Location(Bukkit.getWorlds().get(0), 284.7544680206244, 102.0, 1244.7047876896347)),
        davis(Emmber.getInstance().localFileManager.messages.getMessage("world.store.clickOnMe"), new Location(Bukkit.getWorlds().get(0), 294.598, 92.0, 1250.124)),
        frina(Emmber.getInstance().localFileManager.messages.getMessage("world.store.clickOnMe"), new Location(Bukkit.getWorlds().get(0), 294.5898, 92.0, 1252.0097)),
        jackie(Emmber.getInstance().localFileManager.messages.getMessage("world.store.clickOnMe"), new Location(Bukkit.getWorlds().get(0), 288.5101, 92.0, 1255.5099));

        private String message;
        private Location location;
        private Hologram hologram;

        EmmberHologram(String message, Location location) {
            this.message = message;
            this.location = location;
            /*hologram = HologramsAPI.createHologram(Emmber.getInstance(), location);
            hologram.getVisibilityManager().setVisibleByDefault(false);
            hologram.appendTextLine(message);*/
        }
    }
}
