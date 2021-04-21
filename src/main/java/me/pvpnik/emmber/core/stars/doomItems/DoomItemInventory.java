package me.pvpnik.emmber.core.stars.doomItems;

import me.pvpnik.emmber.api.item.ItemLoreUtils;
import me.pvpnik.emmber.core.stars.Stars;
import me.pvpnik.emmber.utils.HebrewUtil;
import me.pvpnik.emmber.utils.HiddenStringUtils;
import me.pvpnik.emmber.utils.ItemFactory;
import me.pvpnik.emmber.utils.ItemUtils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.*;

public class DoomItemInventory {

	public static void openInv(Player p) {
		//DoomItemManager.getInstance().update(p);
		if (p.hasPermission("avadon.chest.perm")) {
			p.openInventory(premiumInv(p));
		} else {
			p.openInventory(regularInv(p));
		}
	}

	public static final String invTitle = "Doom Items";

	private static Inventory premiumInv(Player p) {
		Inventory inv = Bukkit.createInventory(p, 4 * 9, invTitle + " premium");

		for (int i = 0; i < 4 * 9; i++)
			if (!(i >= 10 && i <= 16) && !(i >= 19 && i <= 25))
				inv.setItem(i, glass_plane(15));

		inv.setItem(27, backToStarInv());

		inv.setItem(34, info());
		inv.setItem(35, exit());

		Set<String> items = new HashSet<>();/*DoomItemManager.getInstance().getItems(p.getUniqueId());*/
		Iterator<String> it = items.iterator();
		int c = 9;
		while (it.hasNext()) {
			if (c == 16)
				c = 19;
			else
				c++;
			try {
				String[] s = it.next().split(";");
				ItemStack is = ItemFactory.deserializeItemStack(s[0]);
				is = changeName(is, Integer.parseInt(s[1]));
				inv.setItem(c, setDate(is));
			} catch (Exception e) {
			}
		}

		return inv;
	}

	private static Inventory regularInv(Player p) {
		Inventory inv = Bukkit.createInventory(p, 3 * 9, invTitle + " regular");

		for (int i = 0; i < 3 * 9; i++)
			if (i < 10 || i > 16)
				inv.setItem(i, glass_plane(15));

		inv.setItem(18, backToStarInv());

		inv.setItem(24, info());
		inv.setItem(25, upgrade());
		inv.setItem(26, exit());

		Set<String> items = new HashSet<>();/*DoomItemManager.getInstance().getItems(p.getUniqueId());*/
		Iterator<String> it = items.iterator();
		int c = 9;
		while (it.hasNext()) {
			c++;
			try {
				String[] s = it.next().split(";");
				ItemStack is = ItemFactory.deserializeItemStack(s[0]);
				is = changeName(is, Integer.parseInt(s[1]));
				inv.setItem(c, setDate(is));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return inv;
	}

	private static ItemStack setDate(ItemStack is) {

		ItemMeta im = is.getItemMeta();
		List<String> lore = im.getLore();

		int id = Integer.parseInt(HiddenStringUtils.extractHiddenString(lore.get(lore.size() - 1)));

		int time = 0;/*(int) (DoomItemManager.getInstance().getDate(id) - System.currentTimeMillis()) / 1000;*/

		int day = time / 86400;
		int hour = (time % 86400) / 3600;
		int min = (time % 3600) / 60;
		int sec = (time % 3600) % 60;

		lore.add(0, day + ":" + hour + ":" + min + ":" + sec);

		im.setLore(lore);
		is.setItemMeta(im);
		return is;
	}

	private static ItemStack changeName(ItemStack is, int id) {
		if (isDoomItem(is))
			return is;

		ItemMeta im = is.getItemMeta();
		String name = im.getDisplayName();
		int itemLevel = ItemLoreUtils.getMinLvl(is);
		double DBase = 2 / ((double) 1 / itemLevel);
		int DiamondCost = (int) (DBase * Stars.getLevel(is));
		int MoneyCost = Stars.getUpgradeCost(is);
		if (DiamondCost == 0 || MoneyCost == Integer.MAX_VALUE)
			return is;

		name = "§b" + HebrewUtil.reverseHebrew("יהלומים") + " " + DiamondCost + "§f" + "ו" + " " + "§2"
				+ HebrewUtil.reverseHebrew("מטבעות") + " " + MoneyCost + "§f" + HebrewUtil.reverseHebrew("לשחזור:") + " ,"
				+ name;

		im.setDisplayName(name);

		List<String> lore = im.hasLore() ? im.getLore() : new ArrayList<String>();
		lore.add(HiddenStringUtils.encodeString("" + id));
		im.setLore(lore);

		is.setItemMeta(im);
		return is;
	}

	public static boolean isDoomItem(ItemStack is) {

		if (is == null || is.getType() == Material.AIR)
			return false;

		if (!is.hasItemMeta())
			return false;

		if (!is.getItemMeta().hasDisplayName())
			return false;

		String dName = is.getItemMeta().getDisplayName();

		return (dName.contains(HebrewUtil.reverseHebrew("יהלומים")) && dName.contains(HebrewUtil.reverseHebrew("מטבעות"))
				&& dName.contains(HebrewUtil.reverseHebrew("לשחזור:")));
	}

	private static ItemStack info() {
		ItemStack is = new ItemStack(Material.DIAMOND_SPADE, 1, (short) 130);
		ItemMeta im = is.getItemMeta();
		im.setDisplayName("info");
		im = ItemUtils.hideFlags(im);
		is.setItemMeta(im);
		return is;
	}

	private static ItemStack upgrade() {
		ItemStack is = new ItemStack(Material.DIAMOND_SPADE, 1, (short) 133);
		ItemMeta im = is.getItemMeta();
		im.setDisplayName("upgrade");
		im = ItemUtils.hideFlags(im);
		is.setItemMeta(im);
		return is;
	}

	private static ItemStack exit() {
		ItemStack is = new ItemStack(Material.DIAMOND_SPADE, 1, (short) 134);
		ItemMeta im = is.getItemMeta();
		im.setDisplayName("exit");
		im = ItemUtils.hideFlags(im);
		is.setItemMeta(im);
		return is;
	}

	private static ItemStack backToStarInv() {
		ItemStack is = new ItemStack(Material.FIREWORK_CHARGE);
		ItemMeta im = is.getItemMeta();
		im.setDisplayName("back to star");
		im = ItemUtils.hideFlags(im);
		is.setItemMeta(im);
		return is;
	}

	private static ItemStack glass_plane(int s) {
		ItemStack is = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) s);
		ItemMeta im = is.getItemMeta();
		im.setDisplayName(" ");
		im = ItemUtils.hideFlags(im);
		is.setItemMeta(im);
		return is;
	}

}
