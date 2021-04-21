package me.pvpnik.emmber.core.shield;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;

public class ShieldCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (!cmd.getName().equalsIgnoreCase("shield")) {
			return false;
		}
		
		if (!(sender instanceof Player)) {
			return false;
		}
		
		((HumanEntity) sender).openInventory(ShieldInventory.getShieldInv((Player) sender));
		return false;
	}

}
