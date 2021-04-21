package me.pvpnik.emmber.managers;

import me.pvpnik.emmber.Emmber;
import me.pvpnik.emmber.api.jobs.EmmberJob;
import me.pvpnik.emmber.api.portals.EmmberPortal;
import me.pvpnik.emmber.core.portals.*;
import net.minecraft.server.v1_12_R1.AxisAlignedBB;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_12_R1.entity.CraftPlayer;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import java.util.ArrayList;
import java.util.HashMap;

public class PortalsManager implements Listener {

    private HashMap<String, EmmberPortal> portals = new HashMap<>();

    public PortalsManager(Emmber emmber) {
        add(new Arena());
        add(new Earth());
        add(new Hero());
        add(new Level8());
        add(new Level82());
        add(new Level83());
        add(new Level84());
        add(new PvP());
        add(new Royal());
        add(new Sand());
        add(new SandLevel());
        add(new SandLevel1());
        add(new Season());
        add(new SeasonEnd());
        add(new Staff());
        add(new Staff2());
        add(new ToCastle());
        add(new ToChallenge());
        add(new WaterMaze());
        add(new WaterMaze1());

        Bukkit.getPluginManager().registerEvents(this, emmber);
    }

    public EmmberPortal add(EmmberPortal portal) {
        return portals.put(portal.getId(), portal);
    }

    public EmmberPortal remove(String id) {
        return portals.remove(id);
    }

    public EmmberPortal getPortal(AxisAlignedBB boundingBox) {
        for (EmmberPortal portal : new ArrayList<>(portals.values())) {
            if (portal.isInPortal(boundingBox)) {
                return portal;
            }
        }
        return null;
    }

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        Location from = event.getFrom();
        Location to = event.getTo();
        if (from.getBlockX() == to.getBlockX() && from.getBlockY() == to.getBlockY() && from.getBlockZ() == to.getBlockZ()) {
            return;
        }
        if (to.clone().add(0, 254 - to.getBlockY(), 0).getBlock().getType() != Material.BARRIER) {
            return;
        }
        EmmberPortal portal = getPortal(((CraftPlayer) event.getPlayer()).getHandle().getBoundingBox());
        if (portal == null) {
            return;
        }
        EmmberJob emmberJob = Emmber.getInstance().playerManager.get(event.getPlayer().getUniqueId());
        portal.execute(emmberJob, event);
    }

}
