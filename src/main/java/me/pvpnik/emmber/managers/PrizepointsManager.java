package me.pvpnik.emmber.managers;

import me.pvpnik.emmber.Emmber;
import me.pvpnik.emmber.api.files.Messages;
import me.pvpnik.emmber.api.jobs.EmmberJob;
import me.pvpnik.emmber.api.prizepoints.Prizepoint;
import me.pvpnik.emmber.core.prizepoints.Prizepoints;
import me.pvpnik.emmber.utils.HebrewUtil;
import me.pvpnik.emmber.utils.OUT;
import me.pvpnik.emmber.utils.Title;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.UUID;

public class PrizepointsManager implements Listener {

    public PrizepointsManager() {
        for (Prizepoints prizepoints : Prizepoints.values()) {
            prizepoints.prizepoint.build();
        }
        Bukkit.getPluginManager().registerEvents(this, Emmber.getInstance());
    }

    @Nullable
    public Prizepoint getPrizepoint(Location loc) {
        for (Prizepoints prizepoints : Prizepoints.values()) {
            if (prizepoints.prizepoint.isInside(loc)) {
                return prizepoints.prizepoint;
            }
        }
        return null;
    }

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        Player p = event.getPlayer();
        Location f = event.getFrom(), t = event.getTo();

        // camera move
        if (f.getBlockX() == t.getBlockX() && f.getBlockY() == t.getBlockY() && f.getBlockZ() == t.getBlockZ()) {
            return;
        }
        // no barrier at Y255
        if (!t.clone().add(0, (255 - t.getBlockY()), 0).getBlock().getType().equals(Material.BARRIER)) {
            return;
        }

        Prizepoint pp = getPrizepoint(t);
        if (pp == null) { // if null, remove barrier.. no need for him
            t.clone().add(0, (255 - t.getBlockY()), 0).getBlock().setType(Material.AIR);
            return;
        }

        EmmberJob emmberJob = Emmber.getInstance().playerManager.get(p.getUniqueId());
        if (emmberJob == null) {
            OUT.toConsole("PrizepointsManager#onPlayerMove, player: " + p.getName() + ", " + p.getUniqueId());
            OUT.toConsole("Doesnt have emmberjob, should not be possible");
            return;
        }

        HashMap<UUID, Long> players = pp.getPlayers();
        long curr_time = System.currentTimeMillis();
        if (players.containsKey(p.getUniqueId())) {
            // already cached
            if (curr_time < players.get(p.getUniqueId())) {
                return;
            }
        } else {
            // not cached
            long time = Emmber.getInstance().sqlManager.playerPrizepoints.getTime(p.getUniqueId(), Prizepoints.getEnum(pp));
            pp.getPlayers().put(p.getUniqueId(), time); // caching
            if (curr_time < time) {
                return;
            }
        }

        Messages messages = Emmber.getInstance().localFileManager.messages;
        Title.sendTitle(p, ChatColor.GOLD + messages.getMessage("prizepoints.arrived") + ChatColor.WHITE + "", "", 1, 1, 1);
        p.getWorld().playSound(p.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1, 1);
        p.sendMessage(Messages.PREFIX + ChatColor.GOLD + messages.getMessage("prizepoints.msg.part4")
                + " " + ChatColor.AQUA + pp.getCooldown() + " " + ChatColor.GOLD + messages.getMessage("prizepoints.msg.part3")
                + " " + ChatColor.AQUA + pp.getPrize() + " " + ChatColor.GOLD + messages.getMessage("prizepoints.msg.part2")
                + ChatColor.AQUA + " " + pp.getName() + " " + ChatColor.GOLD + ":" + messages.getMessage("prizepoints.arrived"));

        curr_time = System.currentTimeMillis() + ((long) pp.getCooldown() * 3600000); // current time + ( cooldown * hour[3600000](ms) )
        pp.getPlayers().put(p.getUniqueId(), curr_time);
        Emmber.getInstance().sqlManager.playerPrizepoints.update(p.getUniqueId(), Prizepoints.getEnum(pp));

        emmberJob.setPoints(emmberJob.getPoints() + pp.getPrize());
    }

}
