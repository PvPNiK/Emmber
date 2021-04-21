package me.pvpnik.emmber.managers;

import me.pvpnik.emmber.Emmber;
import me.pvpnik.emmber.api.jobs.EmmberJob;
import me.pvpnik.emmber.core.scoreboard.MainScoreboard;
import me.pvpnik.emmber.utils.OUT;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.server.PluginDisableEvent;
import org.bukkit.event.server.PluginEnableEvent;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.UUID;

public class EmmberJobsManager implements Listener {

    private HashMap<UUID, EmmberJob> players = new HashMap<>();

    public EmmberJobsManager() {
        Bukkit.getPluginManager().registerEvents(this, Emmber.getInstance());
    }

    @Nullable
    public EmmberJob get(@Nonnull UUID uuid) {
        exist(uuid);
        return players.get(uuid);
    }

    @Nullable
    public EmmberJob get(@Nonnull Player player) {
        return get(player.getUniqueId());
    }

    public EmmberJob put(@Nonnull EmmberJob emmberJob) {
        Emmber.getInstance().emmberCoreManager.scoreboardManager.setScoreboard(emmberJob.getPlayer(), new MainScoreboard(emmberJob));
        return players.put(emmberJob.getUuid(), emmberJob);
    }

    @Nullable
    public EmmberJob remove(@Nonnull UUID uuid) {
        return players.remove(uuid);
    }

    /**
     * Checks if the player is already loaded to the cache,
     * If not, checking if he exist in sql, if so add the player to the cache
     *
     * @param uuid - player's uuid
     * @return true if player exist in the cache or sql
     */
    public boolean exist(@Nonnull UUID uuid) {
        if (players.containsKey(uuid)) {
            return true;
        }
        if (Emmber.getInstance().sqlManager.players.exist(uuid)) {
            EmmberJob emmberJob = Emmber.getInstance().sqlManager.players.get(uuid);
            put(emmberJob);
            return true;
        }
        return false;
    }

    /**
     * Executes every update method which connected somehow to the player.
     * Updates every things about the player:
     * Entity Stats - {@link EntityStatsManager}
     * Scoreboard - {}
     * Exp Bar if needed, etc...
     *
     * @param
     */
    /*public void update(@Nonnull Player player) {
        Emmber.getInstance().entityStatsManager.update(player.getUniqueId());
        Emmber.getInstance().emmberCoreManager.scoreboardManager.update(player);
    }*/

    @EventHandler (priority = EventPriority.LOWEST)
    public void onEnable(PluginEnableEvent e) {
        for (Player player : Bukkit.getOnlinePlayers()) {
            if (!exist(player.getUniqueId())) {
                put(new EmmberJob(player.getUniqueId()));
            }
        }
    }

    @EventHandler
    public void onDisable(PluginDisableEvent e) {
        for (UUID uuid : players.keySet()) {
            Emmber.getInstance().sqlManager.players.update(players.get(uuid));
        }
        players.clear();
    }

    @EventHandler (priority = EventPriority.LOWEST)
    public void onJoin(PlayerJoinEvent e) {
        if (!exist(e.getPlayer().getUniqueId())) {
            put(new EmmberJob(e.getPlayer().getUniqueId()));
        }
    }

    @EventHandler
    public void onLeave(PlayerQuitEvent e) {
        if (!players.containsKey(e.getPlayer().getUniqueId())) {
            OUT.toConsole("Error while saving: " + e.getPlayer().getName() + "," + e.getPlayer().getUniqueId());
            OUT.toConsole("!players.containsKey(e.getPlayer().getUniqueId()), player's EmmberJob is not cached");
            return;
        }
        Emmber.getInstance().sqlManager.players.update(players.get(e.getPlayer().getUniqueId()));
        players.remove(e.getPlayer().getUniqueId()); // just to save some cache memory, in the end if the player's info will be needed it will load anyway cause if #exist
    }

}
