package me.pvpnik.emmber.commands;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.CommandSender;
import org.bukkit.command.defaults.BukkitCommand;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.UUID;

public class CommandR extends BukkitCommand {

    public CommandR() {
        super("r");
        this.description = "Sends private message to the last player who send you a private message";
        this.usageMessage = "/r <message>";
    }

    @Override
    public boolean execute(CommandSender commandSender, String s, String[] args) {
        if (!(commandSender instanceof Player)) {
            return false;
        }
        Player player = (Player) commandSender;
        UUID uuid = CommandPM.getLast(player.getUniqueId());

        if (uuid == null) {
            player.sendMessage("no one sent u msg");
            return false;
        }

        OfflinePlayer target = Bukkit.getOfflinePlayer(uuid);
        if (!target.isOnline()) {
            player.sendMessage(target.getName() + " is not online");
            return false;
        }

        String msg = "";
        for (int i = 0 ; i < args.length ; i++) {
            msg += msg + " " + args[i];
        }

        target.getPlayer().sendMessage(commandSender.getName() + " > " + msg);
        return true;
    }

}
