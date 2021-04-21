package me.pvpnik.emmber.commands.hero;

import me.pvpnik.emmber.Emmber;
import me.pvpnik.emmber.api.jobs.EmmberJob;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.command.defaults.BukkitCommand;
import org.bukkit.entity.Player;

public class CommandPay extends BukkitCommand {

    public CommandPay() {
        super("pay");
        this.description = "Sends money from player to player";
        this.usageMessage = "/pay <name> <amount>";
        this.setPermission("emmber.hero");
    }

    @Override
    public boolean execute(CommandSender commandSender, String s, String[] args) {
        if (!(commandSender instanceof Player)) {
            return false;
        }
        Player player = (Player) commandSender;

        if (args.length != 3) {
            player.sendMessage(this.usageMessage);
            return false;
        }

        String targetName = args[0];
        Player target = Bukkit.getPlayer(targetName);

        if (target == null) {
            player.sendMessage(targetName + " not found");
            return false;
        }

        String amountStr = args[1];
        int amount = 0;
        try {
            amount = Integer.parseInt(amountStr);
        } catch (Exception e) {
            player.sendMessage(amountStr + ", amount has to be a number");
            return false;
        }

        EmmberJob tycon = Emmber.getInstance().playerManager.get(player.getUniqueId());

        if (tycon.getMoney() < amount) {
            player.sendMessage("you do not have enough mmoney");
            return false;
        }

        EmmberJob homeless = Emmber.getInstance().playerManager.get(target.getUniqueId());

        tycon.withdraw(amount);
        homeless.deposit(amount);
        player.sendMessage("sent " + amountStr + " gold to " + target.getCustomName());
        target.sendMessage(player.getCustomName() + " sent you " + amountStr + " gold");
        return true;
    }

}
