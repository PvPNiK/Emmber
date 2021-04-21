package me.pvpnik.emmber.commands;

import me.pvpnik.emmber.core.friends.FriendsInventory;
import org.bukkit.command.CommandSender;
import org.bukkit.command.defaults.BukkitCommand;
import org.bukkit.entity.Player;

public class CommandFriends extends BukkitCommand {

    public CommandFriends() {
        super("friends");
        this.description = "Opens friends menu";
        this.usageMessage = "/friends";
    }

    @Override
    public boolean execute(CommandSender commandSender, String s, String[] strings) {
        if (!(commandSender instanceof Player)) {
            return false;
        }
        FriendsInventory.getFriendsMenu((Player) commandSender);
        return true;
    }

}
