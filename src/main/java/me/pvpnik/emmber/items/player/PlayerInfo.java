package me.pvpnik.emmber.items.player;

import me.pvpnik.emmber.Emmber;
import me.pvpnik.emmber.api.entity.EntityStats;
import me.pvpnik.emmber.api.files.Messages;
import me.pvpnik.emmber.api.jobs.EmmberJob;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.ArrayList;
import java.util.List;

public class PlayerInfo extends ItemStack {

    public PlayerInfo(EmmberJob player) {
        super(Material.SKULL_ITEM, 1, (byte) 3);
        if (player == null) {
            return;
        }

        Messages messages = Emmber.getInstance().localFileManager.messages;
        Player p = player.getPlayer();
        SkullMeta meta = (SkullMeta) Bukkit.getItemFactory().getItemMeta(Material.SKULL_ITEM);
        meta.setOwningPlayer(player.getPlayer());
        meta.setDisplayName((p.isOnline() ? ChatColor.GREEN : ChatColor.GRAY) + "" + p.getName()); // should be always green

        List<String> lore = new ArrayList<String>();
        lore.add(ChatColor.GRAY + "----------------");
        lore.add("" + ChatColor.GRAY + p.getName() + " " + ChatColor.WHITE + messages.getMessage("player.name"));
        lore.add("" + ChatColor.GRAY + player.getPrefix() + " " + ChatColor.WHITE + messages.getMessage("player.rank"));
        lore.add("" + ChatColor.GRAY + player.getLevel() + " " + ChatColor.WHITE + messages.getMessage("player.level"));
        lore.add("" + ChatColor.GRAY + player.getPoints() + " " + ChatColor.WHITE + ":" + messages.getMessage("items.bank.credits"));
        lore.add("" + ChatColor.GRAY + (player.getGuild() == null ? Emmber.getInstance().localFileManager.messages.getMessage("guild.noguild") : player.getGuild().getName()) + " " + ChatColor.WHITE + messages.getMessage("guild.guild"));
        lore.add((p.isOnline() ? ChatColor.GREEN + messages.getMessage("player.online") : ChatColor.RED + messages.getMessage("player.offline")) + " " + ChatColor.WHITE + messages.getMessage("player.state"));
        /*
        lore.add(ChatColor.GRAY + "----------------");
        lore.add(ChatColor.GREEN + "" + Vote.getInstance().get(p.getUniqueId(), VoteEnum.LIKE) + " " + ChatColor.WHITE + ChatUtil.reverseHebrew("כמות לייקים: "));
        lore.add(ChatColor.RED + "" + Vote.getInstance().get(p.getUniqueId(), VoteEnum.DISLIKE) + " " + ChatColor.WHITE + ChatUtil.reverseHebrew("כמות דיסלייקים: "));
        lore.add(ChatColor.GRAY + "----------------");
         */
        lore.add(ChatColor.GOLD + messages.getMessage("player.stats"));
        EntityStats stats = Emmber.getInstance().entityStatsManager.get(player.getPlayer());
        lore.add(ChatColor.WHITE + "" + stats.getDmg() + " " + ChatColor.AQUA + messages.getMessage("item.lore.dmg"));
        lore.add(ChatColor.WHITE + "" + stats.getArmor() + " " + ChatColor.AQUA + messages.getMessage("item.lore.armor"));
        lore.add(ChatColor.WHITE + "" + stats.getHp() + " " + ChatColor.AQUA + messages.getMessage("item.lore.hp"));
        lore.add(ChatColor.GRAY + "----------------");
        meta.setLore(lore);

        setItemMeta(meta);
    }

}
