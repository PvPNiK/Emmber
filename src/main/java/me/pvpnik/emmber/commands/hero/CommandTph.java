package me.pvpnik.emmber.commands.hero;

import org.bukkit.command.CommandSender;
import org.bukkit.command.defaults.BukkitCommand;

public class CommandTph extends BukkitCommand {

    public CommandTph() {
        super("tph");
        this.description = "Teleports a player to you";
        this.usageMessage = "/tph <name> <amount>";
        this.setPermission("emmber.hero");
    }

    @Override
    public boolean execute(CommandSender commandSender, String s, String[] strings) {
        return false;
    }
}
