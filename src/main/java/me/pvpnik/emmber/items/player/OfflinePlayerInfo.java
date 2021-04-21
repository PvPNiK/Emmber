package me.pvpnik.emmber.items.player;

import me.pvpnik.emmber.Emmber;
import me.pvpnik.emmber.api.files.Messages;
import me.pvpnik.emmber.core.guild.Guild;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.ArrayList;
import java.util.List;

public class OfflinePlayerInfo extends ItemStack {
    public OfflinePlayerInfo(OfflinePlayer p) {
        super(Material.SKULL_ITEM, 1, (byte) 3);
        if (p == null) {
            return;
        }
        Messages messages = Emmber.getInstance().localFileManager.messages;
        SkullMeta meta = (SkullMeta) Bukkit.getItemFactory().getItemMeta(Material.SKULL_ITEM);
        meta.setDisplayName((p.isOnline() ? ChatColor.GREEN : ChatColor.GRAY) + "" + p.getName());
        meta.setOwningPlayer(p);

        List<String> lore = new ArrayList<String>();
        lore.add(ChatColor.GRAY + "----------------");
        lore.add("" + ChatColor.GRAY + p.getName() + " " + ChatColor.WHITE + messages.getMessage("player.name"));
        Guild guild = Emmber.getInstance().emmberCoreManager.guilds.getGuild(p.getUniqueId());
        lore.add("" + ChatColor.GRAY + (guild == null ? Emmber.getInstance().localFileManager.messages.getMessage("guild.noguild") : guild.getName()) + " " + ChatColor.WHITE + messages.getMessage("guild.guild"));
        lore.add((p.isOnline() ? ChatColor.GREEN + messages.getMessage("player.online") : ChatColor.RED + messages.getMessage("player.offline")) + " " + ChatColor.WHITE + messages.getMessage("player.state"));
        lore.add(ChatColor.GRAY + "----------------");
        /*lore.add(ChatColor.GREEN + "" + Vote.getInstance().get(p.getUniqueId(), VoteEnum.LIKE) + " " + ChatColor.WHITE + ChatUtil.reverseHebrew("כמות לייקים: "));
        lore.add(ChatColor.RED + "" + Vote.getInstance().get(p.getUniqueId(), VoteEnum.DISLIKE) + " " + ChatColor.WHITE + ChatUtil.reverseHebrew("כמות דיסלייקים: "));*/
        meta.setLore(lore);

        setItemMeta(meta);
    }

}
