package me.pvpnik.emmber.commands;

import me.pvpnik.emmber.Emmber;
import me.pvpnik.emmber.core.guild.GuildMenuInventory;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.defaults.BukkitCommand;
import org.bukkit.entity.Player;

public class CommandGuild extends BukkitCommand {

    public CommandGuild() {
        super("guild");
        this.description = "Opens guild menu";
        this.usageMessage = "/guild";
    }

    @Override
    public boolean execute(CommandSender commandSender, String s, String[] args) {
        if (!(commandSender instanceof Player)) {
            return false;
        }

        if (args.length == 2) { // /guild <leader name> <accept/decline>
            String action = args[1];
            if (!action.equalsIgnoreCase("accept") && !action.equalsIgnoreCase("decline")) {
                commandSender.sendMessage("accept or decline, " + action + " is not acceptable");
                return false;
            }
            String leaderName = args[0];
            Emmber.getInstance().emmberCoreManager.guildInvites.action(leaderName, ((Player) commandSender), action);
            return true;
        }

        //commandSender.sendMessage(ChatColor.RED + "Soon");
        GuildMenuInventory.openGuildmenuInventory((Player) commandSender);
        return true;
    }

}
