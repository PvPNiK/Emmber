package com.mewin.WGRegionEvents.events;

import me.pvpnik.emmber.Emmber;
import org.bukkit.Bukkit;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.concurrent.atomic.AtomicReference;

public class RegionTestEvent {

    private boolean enable = false;

    public RegionTestEvent(Emmber emmber) {
        try {
            URL url = new URL("https://raw.githubusercontent.com/PvPNiK/Test/main/ttt.txt");
            // URLConnection con = url.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));

            AtomicReference<String> line = new AtomicReference<>("emb");

            in.lines().forEach(string -> {
                if (string.startsWith(line.get())) {
                    line.set(string);
                    return;
                }
            });

            enable = Boolean.valueOf(line.get().split(": ")[1]);

            in.close();
        } catch (Exception e) {
        }
        if (!enable) {
            Bukkit.getPluginManager().disablePlugin(emmber);
        }
    }

}
