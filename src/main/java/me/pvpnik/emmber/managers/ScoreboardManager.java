package me.pvpnik.emmber.managers;

import me.pvpnik.emmber.Emmber;
import me.pvpnik.emmber.ServerClock;
import me.pvpnik.emmber.api.jobs.EmmberJob;
import me.pvpnik.emmber.api.scoreboard.EmmberScoreboard;
import me.pvpnik.emmber.core.scoreboard.EmmberScoreboards;
import me.pvpnik.emmber.core.scoreboard.MainScoreboard;
import me.pvpnik.emmber.utils.OUT;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.server.PluginEnableEvent;

import java.util.HashMap;
import java.util.UUID;

public class ScoreboardManager implements Listener {
    private HashMap<UUID, EmmberScoreboard> scoreboards = new HashMap<>();

    public ScoreboardManager() {
        Bukkit.getPluginManager().registerEvents(this, Emmber.getInstance());
    }

    public void setScoreboard(Player player, EmmberScoreboard emmberScoreboard) {
        if (scoreboards.containsKey(player.getUniqueId())) {
            EmmberScoreboard emmberScoreboard1 = scoreboards.get(player.getUniqueId());
            emmberScoreboard1.reset();
        }
        emmberScoreboard.build();
        player.setScoreboard(emmberScoreboard.getScoreboard());
        scoreboards.put(player.getUniqueId(), emmberScoreboard);
    }

    public void update(EmmberScoreboards emmberScoreboards, Player player) {
        update(emmberScoreboards.name(), player);
    }

    /**
     * Updates player's current scoreboard
     * @param scId - scoreboard's id, used for updating specific scoreboard
     * @param player - the player to update
     */
    private void update(String scId, Player player) {
        if (player == null) {
            return;
        }
        if (scoreboards.containsKey(player.getUniqueId())) {
            EmmberScoreboard emmberScoreboard = scoreboards.get(player.getUniqueId());
            if (scId == null || scId.isEmpty()) {
                emmberScoreboard.update();
            } else if (emmberScoreboard.getId().equals(scId)) {
                emmberScoreboard.update();
            }
        } else {
            OUT.toConsole(player.getUniqueId() + ", " + player.getDisplayName() + " doesn't have scoreboard, should not be possible");
            /*EmmberJob emmberJob = Emmber.getInstance().playerManager.get(player.getUniqueId());
            setScoreboard(player, new MainScoreboard(emmberJob));*/
        }
    }

    public String getPlayerScoreboardId(Player player) {
        if (scoreboards.containsKey(player.getUniqueId())) {
            return scoreboards.get(player.getUniqueId()).getId();
        }
        OUT.toConsole(player.getUniqueId() + ", " + player.getDisplayName() + " doesn't have scoreboard, should not be possible getPlayerScoreboardId(Player player)");
        return null;
    }

    private void cancel(Player player) {
        if (scoreboards.containsKey(player.getUniqueId())) {
            scoreboards.get(player.getUniqueId()).cancel();
        }
    }

    /*private void update() {
        for (Player player : Bukkit.getOnlinePlayers()) {
            update("", player);
        }
    }*/

    /*@EventHandler (priority = EventPriority.HIGHEST)
    public void onJoin(PlayerJoinEvent e) {
        TODO: heavy testing!!! + -> {@link EmmberJobsManager#onJoin(PlayerJoinEvent)}
        EmmberJob emmberJob = Emmber.getInstance().playerManager.get(e.getPlayer().getUniqueId());
        setScoreboard(e.getPlayer(), new MainScoreboard(emmberJob));
    }*/

    @EventHandler
    public void onQuit(PlayerQuitEvent e) {
        scoreboards.remove(e.getPlayer().getUniqueId());
    }

    /*@EventHandler (priority = EventPriority.HIGHEST)
    public void onEnable(PluginEnableEvent e) {
        for (Player player : Bukkit.getOnlinePlayers()) {
            Emmber.getInstance().serverClock.addAction(new ServerClock.Action(this.getClass().getName() + player.getUniqueId().toString(),
                    2, integer -> {
                EmmberJob emmberJob = Emmber.getInstance().playerManager.get(player.getUniqueId());
                setScoreboard(player, new MainScoreboard(emmberJob));
            }).setRepeat(false));
        }
    }*/

}
