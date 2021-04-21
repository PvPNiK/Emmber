package me.pvpnik.emmber.core.stars;

import me.pvpnik.emmber.utils.HebrewUtil;
import me.pvpnik.emmber.utils.ItemUtils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class StarInventory {
	
	public static final String invTitle = HebrewUtil.reverseHebrew("שדרוג כוכבים");
	
	public static Inventory get(Player p, ItemStack is) {
		Inventory inv = (Inventory) Bukkit.createInventory(new StarsInventoryHolder(), 9 * 6, invTitle);

		for (int i = 0; i < 21; i++) {
			for (int z = 0; z < 3; z++)
				if (z + i != 10)
					inv.setItem(z + i, glass_plane(14));
			i += 8;
		}

		for (int i = 3; i < 9 * 3; i++) {
			for (int z = 0; z < 6; z++)
				inv.setItem(z + i, glass_plane(4));
			i += 8;
		}

		for (int i = 3 * 9; i < 9 * 6; i++)
			if (!(i >= 4 * 9 + 1 && i <= 5 * 9 - 2))
				inv.setItem(i, glass_plane(15));
		
		if (is != null)
			if (Stars.getLevel(is) != 0)
				for (int i = 0; i < Stars.getLevel(is); i++)
					inv.setItem(37+i, firework_charge());
		
		if (is != null) {
			inv.setItem(10, is);
		}
		
		inv.setItem(13, upgrade(is));
		inv.setItem(14, reset(is));
		inv.setItem(15, help());
		inv.setItem(16, doomInv());
		
		return inv;
	}
	
	private static ItemStack upgrade(ItemStack is) {
		ItemStack upgrade = ItemUtils.hideFlags(new ItemStack (Material.DIAMOND_SPADE, 1, (short) 133));
		ItemMeta upgradeIm = upgrade.getItemMeta();
		upgradeIm.setDisplayName("§a" + HebrewUtil.reverseHebrew("שדרוג"));
		if (is != null) {
			if (Stars.getLevel(is) != 7) {
				List<String> lore = new ArrayList<String>();
				lore.add(HebrewUtil.reverseHebrew("מחיר שדרוג: " + Stars.getUpgradeCost(is)));
				lore.add(HebrewUtil.reverseHebrew("סיכויי הצלחה: " + Stars.getUpgradeChance(is) + "%"));
				upgradeIm.setLore(lore);
			}
		}
		upgrade.setItemMeta(upgradeIm);
		return upgrade;
	}
	
	private static ItemStack reset(ItemStack is) {
		ItemStack reset = ItemUtils.hideFlags(new ItemStack (Material.DIAMOND_SPADE, 1, (short) 131));
		ItemMeta resetIm = reset.getItemMeta();
		resetIm.setDisplayName("§c" + HebrewUtil.reverseHebrew("איפוס"));
		if (is != null) {
			if (Stars.getLevel(is) != 0) {
				List<String> lore = new ArrayList<String>();
				lore.add(HebrewUtil.reverseHebrew("מחיר איפוס: " + Stars.getResetCost(is)));
				lore.add(HebrewUtil.reverseHebrew("יאפס את החפץ ל0 כוכבים ויאפשר שדרוג מחדש שלו."));
				resetIm.setLore(lore);
			}
		}
		reset.setItemMeta(resetIm);
		return reset;
	}
	
	private static ItemStack help() {
		ItemStack is = ItemUtils.hideFlags(new ItemStack (Material.DIAMOND_SPADE, 1, (short) 130));
		ItemMeta im = is.getItemMeta();
		im.setDisplayName("§fAbout");
		is.setItemMeta(im);
		return is;
	}
	
	private static ItemStack doomInv() {
		ItemStack is = new ItemStack(Material.SAND);/*ItemUtils.hideFlags(CastleOfEssver.settings.getHeadDatabaseAPI().getItemHead("1647"))*/;
		ItemMeta im = is.getItemMeta();
		im.setDisplayName("§5" + HebrewUtil.reverseHebrew("תיבת האבדון"));
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
	
	private static ItemStack firework_charge() {
		ItemStack is = new ItemStack(Material.FIREWORK_CHARGE);
		ItemMeta im = is.getItemMeta();
		im.setDisplayName(" ");
		im = ItemUtils.hideFlags(im);
		is.setItemMeta(im);
		return is;
	}
	
}
