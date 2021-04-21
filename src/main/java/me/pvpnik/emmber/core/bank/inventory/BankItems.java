package me.pvpnik.emmber.core.bank.inventory;

import me.pvpnik.emmber.Emmber;
import me.pvpnik.emmber.api.jobs.EmmberJob;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class BankItems {

	private static String removeLetters(String str) {
		for (int i = 0; i < str.length(); i++)
			if (!Character.isDigit(str.charAt(i)))
				return removeLetters(str.replace(str.charAt(i) + "", ""));
		return str;
	}

	public static int getAmount(ItemStack is) {
		return Integer.parseInt(removeLetters(ChatColor.stripColor(is.getItemMeta().getDisplayName())));
	}

	public static void give(EmmberJob player, ItemStack is) {
		int amount;

		try {
			amount = getAmount(is);
		} catch (Exception e) {
			return;
		}

		switch (is.getType()) {
		case GOLD_INGOT:
			player.deposit(amount);
			break;

		case DIAMOND:
			player.setDiamonds(player.getDiamonds() + amount);
			break;

		case EMERALD:
			player.setCrystal(player.getCrystal() + amount);
			break;

		default:
			break;
		}

	}

	public static void take(EmmberJob player, ItemStack is) {
		int amount;

		try {
			amount = getAmount(is);
		} catch (Exception e) {
			return;
		}

		switch (is.getType()) {
		case GOLD_INGOT:
			player.withdraw(amount);
			break;

		case DIAMOND:
			player.setDiamonds(player.getDiamonds() - amount < 0 ? 0 : player.getDiamonds() - amount);
			break;

		case EMERALD:
			player.setCrystal(player.getCrystal() - amount < 0 ? 0 : player.getCrystal() - amount);
			break;

		case REDSTONE:
			player.setPoints(player.getPoints() - amount < 0 ? 0 : player.getPoints() - amount);
			break;

		default:
			break;
		}

	}

	public static boolean hasAmount(EmmberJob player, ItemStack is) {
		int amount;

		try {
			amount = getAmount(is);
		} catch (Exception e) {
			return false;
		}

		switch (is.getType()) {
		case GOLD_INGOT:
			return player.getMoney() >= amount;

		case DIAMOND:
			return player.getDiamonds() >= amount;

		case EMERALD:
			return player.getCrystal() >= amount;

		case REDSTONE:
			return player.getPoints() >= amount;

		default:
			return false;
		}

	}

	public static String getDenyMessage(ItemStack is) {
		String var;

		switch (is.getType()) {
		case GOLD_INGOT:
			var = Emmber.getInstance().localFileManager.messages.getMessage("items.bank.gold");
			break;

		case DIAMOND:
			var = Emmber.getInstance().localFileManager.messages.getMessage("items.bank.diamond");
			break;

		case EMERALD:
			var = Emmber.getInstance().localFileManager.messages.getMessage("items.bank.crystal");
			break;

		case REDSTONE:
			var = Emmber.getInstance().localFileManager.messages.getMessage("items.bank.credits");
			break;

		default:
			return "";
		}
		String part1 = Emmber.getInstance().localFileManager.messages.getMessage("world.bank.conversion.denymsg.part1");
		String part2 = Emmber.getInstance().localFileManager.messages.getMessage("world.bank.conversion.denymsg.part2");
		return ChatColor.RED + part1 + var + part2;
	}

	public static ChatColor getColor(ItemStack is) {

		switch (is.getType()) {
		case GOLD_INGOT:
			return ChatColor.YELLOW;

		case DIAMOND:
			return ChatColor.AQUA;

		case EMERALD:
			return ChatColor.GREEN;

		case REDSTONE:
			return ChatColor.RED;

		default:
			return ChatColor.WHITE;
		}

	}

	public static ItemStack to(ItemStack is) {
		ItemMeta im = is.getItemMeta();
		im.setDisplayName(im.getDisplayName() + " " + getColor(is) + Emmber.getInstance().localFileManager.messages.getMessage("world.bank.conversion.clickto"));
		is.setItemMeta(im);
		return is;
	}

	public static ItemStack from(ItemStack is) {
		ItemMeta im = is.getItemMeta();
		im.setDisplayName(im.getDisplayName() + " " + getColor(is) + Emmber.getInstance().localFileManager.messages.getMessage("item.lore.price"));
		is.setItemMeta(im);
		return is;
	}

	public static ItemStack gold(int amount) {
		ItemStack is = new ItemStack(Material.GOLD_INGOT);
		ItemMeta im = is.getItemMeta();
		im.setDisplayName(getColor(is) + Emmber.getInstance().localFileManager.messages.getMessage("items.bank.goldcoins") + " " + ChatColor.WHITE + amount);
		is.setItemMeta(im);
		return is;
	}

	public static ItemStack diamond(int amount) {
		ItemStack is = new ItemStack(Material.DIAMOND);
		ItemMeta im = is.getItemMeta();

		im.setDisplayName(getColor(is) + Emmber.getInstance().localFileManager.messages.getMessage("items.bank.diamond") + " " + ChatColor.WHITE + amount);

		is.setItemMeta(im);

		return is;
	}

	public static ItemStack credits(int amount) {
		ItemStack is = new ItemStack(Material.REDSTONE);
		ItemMeta im = is.getItemMeta();

		im.setDisplayName(getColor(is) + Emmber.getInstance().localFileManager.messages.getMessage("items.bank.credits") + " " + ChatColor.WHITE + amount);

		is.setItemMeta(im);

		return is;
	}

	public static ItemStack crystal(int amount) {
		ItemStack is = new ItemStack(Material.EMERALD);
		ItemMeta im = is.getItemMeta();

		im.setDisplayName(getColor(is) + Emmber.getInstance().localFileManager.messages.getMessage("items.bank.crystal") + " " + ChatColor.WHITE + amount);

		is.setItemMeta(im);

		return is;
	}

	public static ItemStack arrow() {
		ItemStack is = new ItemStack(Material.DIAMOND_SPADE, 1, (short) 132);
		ItemMeta im = is.getItemMeta();

		//im = ItemUtils.hideFlags(im);

		im.setDisplayName(" ");

		is.setItemMeta(im);

		return is;
	}

	public static ItemStack help() {
		ItemStack is = new ItemStack(Material.DIAMOND_SPADE, 1, (short) 130);
		ItemMeta im = is.getItemMeta();

		//im = ItemUtils.hideFlags(im);

		im.setDisplayName(ChatColor.DARK_AQUA + Emmber.getInstance().localFileManager.messages.getMessage("items.bank.help"));

		is.setItemMeta(im);

		return is;
	}

	public static ItemStack exit() {
		ItemStack is = new ItemStack(Material.DIAMOND_SPADE, 1, (short) 134);
		ItemMeta im = is.getItemMeta();

		//im = ItemUtils.hideFlags(im);

		im.setDisplayName(ChatColor.RED + Emmber.getInstance().localFileManager.messages.getMessage("items.bank.exit"));

		is.setItemMeta(im);

		return is;
	}

}
