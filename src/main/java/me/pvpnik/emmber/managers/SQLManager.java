package me.pvpnik.emmber.managers;

import me.pvpnik.emmber.sql.*;
import org.bukkit.plugin.Plugin;

/**
 * Holds all the sql classes in one place
 */
public class SQLManager {

    public SQLSpawner spawner = new SQLSpawner();
    public SQLPlayers players = new SQLPlayers();
    public SQLFriends friends = new SQLFriends();
    public SQLRegionBossBar regionBossBar = new SQLRegionBossBar();
    public SQLGuilds guilds = new SQLGuilds();
    public SQLGuildMembers guildMembers = new SQLGuildMembers();
    public SQLPlayerPrizepoints playerPrizepoints = new SQLPlayerPrizepoints();

    public SQLManager(Plugin plugin) {

    }

}
