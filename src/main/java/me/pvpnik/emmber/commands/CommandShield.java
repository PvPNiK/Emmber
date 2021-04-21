package me.pvpnik.emmber.commands;

import me.pvpnik.emmber.core.shield.ShieldInventory;
import org.bukkit.command.CommandSender;
import org.bukkit.command.defaults.BukkitCommand;
import org.bukkit.entity.Player;

public class CommandShield extends BukkitCommand {

    public CommandShield() {
        super("shield");
        this.description = "Opens shields menu";
        this.usageMessage = "/shield";
    }

    @Override
    public boolean execute(CommandSender commandSender, String s, String[] strings) {
        if (!(commandSender instanceof Player)) {
            return false;
        }
        ShieldInventory.getShieldInv((Player) commandSender);
        return true;
    }

}
