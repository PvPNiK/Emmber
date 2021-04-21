package me.pvpnik.emmber.utils;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class OUT {

    private OUT() {}

    public static final String PREFIX = ChatColor.GOLD + "[" + ChatColor.AQUA + "Server" + ChatColor.GOLD + "] " + ChatColor.RESET;

    public static void toConsole(String str) {
        toConsole(str, true);
    }

    public static void toConsole(String str, boolean prefix) {
        Bukkit.getConsoleSender().sendMessage(prefix ? PREFIX + str : str);
    }

    public static void toServer(String str) {
        toServer(str, true);
    }

    public static void toServer(String str, boolean prefix) {
        Bukkit.broadcastMessage(prefix ? PREFIX + str : str);
    }

    public static void toPlayer(Player player, String str) {
        toPlayer(player, str, true);
    }

    public static void toPlayer(Player player, String str, boolean prefix) {
        if (player.isValid()) {
            player.sendMessage(prefix ? PREFIX + str : str);
        }
    }

}