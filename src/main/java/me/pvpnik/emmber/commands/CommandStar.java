package me.pvpnik.emmber.commands;

import me.pvpnik.emmber.core.bank.inventory.BankMainInventory;
import me.pvpnik.emmber.core.stars.AnimationStage;
import me.pvpnik.emmber.core.stars.StarInventory;
import me.pvpnik.emmber.core.stars.StarUpgradeAnimation;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.defaults.BukkitCommand;
import org.bukkit.entity.Player;

public class CommandStar extends BukkitCommand {

    public CommandStar() {
        super("star");
        this.description = "Opens star's inventory";
        this.usageMessage = "/star <player name(?)>";
    }

    @Override
    public boolean execute(CommandSender sender, String s, String[] args) {
        /*sender.sendMessage(ChatColor.RED + "Soon");
        return false;*/
        if (args.length != 1) {
            if (!(sender instanceof Player)) {
                return false;
            }

            Player p = (Player) sender;
            StarUpgradeAnimation.pStage.put(p.getUniqueId(), AnimationStage.OFF);
            p.openInventory(StarInventory.get(p, null));
            return true;
        }

        String tName = args[0];
        Player t;
        try {
            t = Bukkit.getPlayer(tName);
        } catch (Exception e) {
            sender.sendMessage(ChatColor.RED + "Invalid player name");
            return false;
        }

        StarUpgradeAnimation.pStage.put(t.getUniqueId(), AnimationStage.OFF);
        t.openInventory(StarInventory.get(t, null));
        return true;
    }

}
