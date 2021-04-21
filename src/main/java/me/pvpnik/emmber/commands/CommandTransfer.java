package me.pvpnik.emmber.commands;

import me.pvpnik.emmber.core.bank.inventory.BankMainInventory;
import org.bukkit.command.CommandSender;
import org.bukkit.command.defaults.BukkitCommand;
import org.bukkit.entity.Player;

public class CommandTransfer extends BukkitCommand {

    public CommandTransfer() {
        super("transfer");
        this.description = "Opens bank's inventory";
        this.usageMessage = "/transfer";
    }

    @Override
    public boolean execute(CommandSender commandSender, String s, String[] strings) {
        if (!(commandSender instanceof Player)) {
            return false;
        }
        Player player = (Player) commandSender;
        player.openInventory(BankMainInventory.getBankOptionInv());
        return true;
    }

}
