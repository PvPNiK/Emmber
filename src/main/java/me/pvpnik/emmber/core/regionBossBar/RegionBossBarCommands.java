package me.pvpnik.emmber.core.regionBossBar;

import me.pvpnik.emmber.Emmber;
import me.pvpnik.emmber.managers.RegionBossBarManager;
import me.pvpnik.emmber.sql.SQLRegionBossBar;
import me.pvpnik.emmber.utils.HebrewUtil;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarFlag;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.concurrent.atomic.AtomicBoolean;

public class RegionBossBarCommands implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

		if (!cmd.getName().equalsIgnoreCase("bossbar"))
			return false;

		if (sender instanceof Player)
			if (!sender.isOp())
				return false;

		RegionBossBarManager manager = Emmber.getInstance().emmberCoreManager.regionBossBar;
		
		if (args.length == 2) {
			
			if (!args[0].equalsIgnoreCase("delete")) {
				showHelp(sender);
				return false;
			}
			
			String region = args[1];
			
			if (!manager.regionHasBossBar(region)) {
				sender.sendMessage("The region: " + region + " does not have a bossbar.");
				return false;
			}
			
			manager.remove(region);
			sender.sendMessage("BossBar for region: " + region + " habe been deleted!");
			return true;
		}
		
		if (args.length >= 3 && args[0].equalsIgnoreCase("create")) {

			String region = args[1];

			if (manager.regionHasBossBar(region)) {
				sender.sendMessage("The region: " + region + " has a bossbar.");
				return false;
			}

			AtomicBoolean regionExist = new AtomicBoolean(false);
			Emmber.getInstance().worldGuard.ifPresent(worldGuardPlugin -> {
				for (World world : Bukkit.getWorlds()) {
					if (worldGuardPlugin.getRegionManager(world).getRegion(region) != null) {
						if (sender instanceof Player) {
							if (!((Player) sender).getWorld().getName().equals(world.getName())) {
								continue; // if found region, but its not in the plaeyr's world
							}
						}
						regionExist.set(true);
						break;
					}
				}
			});

			if (!regionExist.get()) {
				sender.sendMessage("There is no region by the name: " + region);
				return false;
			}

			String title = "";

			for (int i = 2; i < args.length; i++)
				title = title + args[i] + " ";
			title = title.substring(0, title.length() - 1);
			title = HebrewUtil.reverseHebrew(title);

			BossBar bar = Bukkit.createBossBar(title, BarColor.PINK, BarStyle.SOLID);

			manager.add(region, bar);
			Emmber.getInstance().sqlManager.regionBossBar.insert(region, bar);
			sender.sendMessage("Added boss bar to the region: " + region);
			return true;
		}
		
		if (args.length != 4) {
			showHelp(sender);
			return false;
		}
		
		String region = args[0];

		if (!manager.regionHasBossBar(region)) {
			sender.sendMessage("The region: " + region + " does not have a bossbar.");
			return false;
		}
		
		BossBar bar = manager.get(region);
		
		if (args[1].equalsIgnoreCase("set")) {
			
			switch (args[2]) {
			case "color":
				String color = args[3];
				
				boolean legitColor = false;
				for (BarColor bc : BarColor.values())
					if (bc.name().equalsIgnoreCase(color)) {
						legitColor = true;
						break;
					}
				
				if (!legitColor) {
					sender.sendMessage("The Colors Are:");
					for (BarColor bc : BarColor.values())
						sender.sendMessage(bc.name());
					return false;
				}
				
				bar.setColor(BarColor.valueOf(color.toUpperCase()));
				Emmber.getInstance().sqlManager.regionBossBar.update(region, bar);
				sender.sendMessage("BossBar Color of the region: " + region + " has been changed to: " + color);
				
				return true;
			case "style":
				String style = args[3];
				
				boolean legitStyle = false;
				for (BarStyle bs : BarStyle.values())
					if (bs.name().equalsIgnoreCase(style)) {
						legitStyle = true;
						break;
					}
				
				if (!legitStyle) {
					sender.sendMessage("The Styles Are:");
					for (BarStyle bs : BarStyle.values())
						sender.sendMessage(bs.name());
					return false;
				}
				
				bar.setStyle(BarStyle.valueOf(style.toUpperCase()));
				Emmber.getInstance().sqlManager.regionBossBar.update(region, bar);
				sender.sendMessage("BossBar Style of the region: " + region + " has been changed to: " + style);
				
				return true;
			default:
				showHelp(sender);
				return false;
			}
			
		}
		
		if (!args[2].equalsIgnoreCase("flag")) {
			showHelp(sender);
			return false;
		}
		
		String flag = args[3];
		
		boolean legitFlag = false;
		for (BarFlag bf : BarFlag.values())
			if (bf.name().equalsIgnoreCase(flag)) {
				legitFlag = true;
				break;
			}
		
		if (!legitFlag) {
			sender.sendMessage("The Flags Are:");
			for (BarFlag bf : BarFlag.values())
				sender.sendMessage(bf.name());
			return false;
		}
		
		switch (args[1]) {
		case "add":
			bar.addFlag(BarFlag.valueOf(flag.toUpperCase()));
			Emmber.getInstance().sqlManager.regionBossBar.update(region, bar);
			sender.sendMessage("The flag: " + flag + " has been ADDED to region: " + region);
			
			return true;
		case "remove":
			bar.removeFlag(BarFlag.valueOf(flag.toUpperCase()));
			Emmber.getInstance().sqlManager.regionBossBar.update(region, bar);
			sender.sendMessage("The flag: " + flag + " has been REMOVED to region: " + region);
			
			return true;
		default:
			showHelp(sender);
			return false;
		}
		
	}

	private void showHelp(CommandSender sender) {
		sender.sendMessage("/bossbar create [region] [title]");
		sender.sendMessage("/bossbar delete [region]");
		sender.sendMessage("/bossbar [region] set color [color]");
		sender.sendMessage("/bossbar [region] set style [style]");
		sender.sendMessage("/bossbar [region] add flag [flag]");
		sender.sendMessage("/bossbar [region] remove flag [flag]");
	}

}
