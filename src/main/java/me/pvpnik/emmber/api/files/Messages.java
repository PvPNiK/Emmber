package me.pvpnik.emmber.api.files;

import me.pvpnik.emmber.utils.HebrewUtil;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class Messages extends LocalFile {
    public static final String PREFIX = ChatColor.GOLD + "[" + ChatColor.AQUA + "Emmber" + ChatColor.GOLD + "] " + ChatColor.RESET;

    public Messages() {
        super("messages.yml");
    }

    /**
     * @param str - message key
     * @return message's value
     */
    public String getMessage(String str) {
        return HebrewUtil.reverseHebrew(getValue(str));
    }

    public void sendMessage(Player player, String value) {
        player.sendMessage(PREFIX + getMessage(value));
    }

    public void sendMessage(Player player, ChatColor chatColor, String value) {
        player.sendMessage(PREFIX + chatColor + getMessage(value));
    }

}
