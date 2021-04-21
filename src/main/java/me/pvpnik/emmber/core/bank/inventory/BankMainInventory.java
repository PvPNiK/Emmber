package me.pvpnik.emmber.core.bank.inventory;

import me.pvpnik.emmber.Emmber;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class BankMainInventory {

	private BankMainInventory() {}

	public static final String invTitle = Emmber.getInstance().localFileManager.messages.getMessage("world.bank.conversion.menu");
	
	public static Inventory getBankOptionInv() {
		Inventory inv = Bukkit.createInventory(null, 9, invTitle);
		
		inv.setItem(2, gold());
		inv.setItem(4, crystal());
		inv.setItem(6, diamond());
		
		return inv;
	}
	
	public static ItemStack gold() {
		ItemStack is = new ItemStack(Material.GOLD_INGOT);
		ItemMeta im = is.getItemMeta();
		
		im.setDisplayName(ChatColor.YELLOW + Emmber.getInstance().localFileManager.messages.getMessage("world.bank.conversion.gold"));
		
		is.setItemMeta(im);
		
		return is;
	}
	
	public static ItemStack crystal() {
		ItemStack is = new ItemStack(Material.EMERALD);
		ItemMeta im = is.getItemMeta();
		
		im.setDisplayName(ChatColor.GREEN + Emmber.getInstance().localFileManager.messages.getMessage("world.bank.conversion.crystal"));
		
		is.setItemMeta(im);
		
		return is;
	}
	
	public static ItemStack diamond() {
		ItemStack is = new ItemStack(Material.DIAMOND);
		ItemMeta im = is.getItemMeta();
		
		im.setDisplayName(ChatColor.AQUA + Emmber.getInstance().localFileManager.messages.getMessage("world.bank.conversion.diamond"));
		
		is.setItemMeta(im);
		
		return is;
	}
	
}
