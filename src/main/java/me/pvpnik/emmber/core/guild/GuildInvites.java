package me.pvpnik.emmber.core.guild;

import me.pvpnik.emmber.Emmber;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Set;
import java.util.UUID;

public class GuildInvites {

    // Key - leader's name, Value - all the players he invited
    public HashMap<String, Set<UUID>> invites = new HashMap<>();

    public void action(String leaderName, Player player, String acceptOrDecline) {
        if (!invites.containsKey(leaderName)) {
            player.sendMessage("could not find " + leaderName);
            return;
        }
        OfflinePlayer leader = Bukkit.getOfflinePlayer(leaderName);
        if (leader == null) {
            player.sendMessage("could not find " + leaderName);
            return;
        }
        if (!invites.get(leaderName).remove(player.getUniqueId())) {
            player.sendMessage(leaderName + " not invited you");
            return;
        }
        if (acceptOrDecline.equalsIgnoreCase("accept")) {
            accepted(leader, player);
        } else {
            declined(leader, player);
        }
    }

    private void accepted(OfflinePlayer leader, Player player) {
        Guild playerGuild = Emmber.getInstance().emmberCoreManager.guilds.getGuild(player.getUniqueId());
        if (playerGuild != null) {
            player.sendMessage("you already in a guild");
            return;
        }

        Guild guild = Emmber.getInstance().emmberCoreManager.guilds.getGuild(leader.getUniqueId());
        if (guild == null) {
            player.sendMessage("somehow " + leader.getName() + " doesnt have a guild");
            return;
        }

        player.sendMessage("you have joined the guild!");
        if (leader.isOnline()) {
            leader.getPlayer().sendMessage(player.getName() + " joined your guild!");
        }

        guild.addMember(player.getUniqueId());
    }

    private void declined(OfflinePlayer leader, Player player) {
        player.sendMessage("you declined " + leader.getName() + "'s invitation");
        if (leader.isOnline()) {
            leader.getPlayer().sendMessage(player.getName() + " declined your invitation");
        }
    }

    public HashMap<String, Set<UUID>> getInvites() {
        return invites;
    }
}
