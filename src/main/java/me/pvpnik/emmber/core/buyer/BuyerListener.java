package me.pvpnik.emmber.core.buyer;

import me.arcaniax.hdb.api.HeadDatabaseAPI;
import me.pvpnik.emmber.Emmber;
import me.pvpnik.emmber.api.item.ItemLoreUtils;
import me.pvpnik.emmber.api.jobs.EmmberJob;
import me.pvpnik.emmber.utils.HebrewUtil;
import me.pvpnik.emmber.utils.ItemUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class BuyerListener implements Listener {
	
	public static class BuyerInventoryHolder implements InventoryHolder {
		@Override
		public @NotNull Inventory getInventory() {
			return null;
		}
	}

	public static void openBuyerInv(Player p) {
		Inventory inv = Bukkit.createInventory(new BuyerInventoryHolder(), 9*3, "te");
		
		for (int i = 0 ; i<=18; i = i+9) {
			for (int z = 0; z <= 2; z++)
				inv.setItem(i+z, glass((short) 5));
			for (int z = 3; z <= 5; z++)
				inv.setItem(i+z, glass((short) 15));
			for (int z = 6; z <= 8; z++)
				inv.setItem(i+z, glass((short) 3));
		}
		
		inv.setItem(1+9, new ItemStack(Material.AIR));
		
		inv.setItem(4+0, vi());
		inv.setItem(4+9, x());
		inv.setItem(4+18, qm());
		
		inv.setItem(7+9, gold(inv));
		p.openInventory(inv);
	}
	
	private static ItemStack qm() {
		ItemStack is = new HeadDatabaseAPI().getItemHead("6365");
		ItemMeta im = ItemUtils.hideFlags(is.getItemMeta());
		List<String> lore = new ArrayList<>();
		im.setDisplayName("§7"+HebrewUtil.reverseHebrew("מידע"));
		lore.add(HebrewUtil.reverseHebrew("בצד שמאל הניחו את החפצים שברצונכם למכור לסוחר,"));
		lore.add(HebrewUtil.reverseHebrew("בצד ימין עמדו על מטיל הזהב בכדי לראות את הצעת הסוחר."));
		lore.add(HebrewUtil.reverseHebrew("אישרו בלחיצה על הוי,"));
		lore.add(HebrewUtil.reverseHebrew("ביטלו בלחיצה על האיקס."));
		im.setLore(lore);
		is.setItemMeta(im);
		return is;
	}
	
	private static ItemStack vi() {
		ItemStack is = new HeadDatabaseAPI().getItemHead("845");
		ItemMeta im = ItemUtils.hideFlags(is.getItemMeta());
		im.setDisplayName("§a"+HebrewUtil.reverseHebrew("אישור"));
		List<String> lore = new ArrayList<>();
		im.setLore(lore);
		is.setItemMeta(im);
		return is;
	}
	
	private static ItemStack x() {
		ItemStack is = new HeadDatabaseAPI().getItemHead("844");
		ItemMeta im = ItemUtils.hideFlags(is.getItemMeta());
		im.setDisplayName("§c"+HebrewUtil.reverseHebrew("ביטול"));
		List<String> lore = new ArrayList<>();
		im.setLore(lore);
		is.setItemMeta(im);
		return is;
	}
	
	private static ItemStack glass(short data) {
		ItemStack is = new ItemStack(Material.STAINED_GLASS_PANE, 1 ,data);
		ItemMeta im = ItemUtils.hideFlags(is.getItemMeta());
		im.setDisplayName("§a");
		is.setItemMeta(im);
		return is;
	}
	
	private static ItemStack gold(Inventory inv) {
		ItemStack is = new ItemStack(Material.GOLD_INGOT);
		ItemMeta im = ItemUtils.hideFlags(is.getItemMeta());
		
		double totalPrice = itemPrice(inv.getItem(1+9));
		im.setDisplayName("§a" + ((int)totalPrice) + " " + HebrewUtil.reverseHebrew("הצעת הסוחר:"));
		//im.setDisplayName("§aTotal Money: " + ((int)totalPrice));
		is.setItemMeta(im);
		return is;
	}
	
	private static double itemPrice(ItemStack is) {
		int price = ItemLoreUtils.getPrice(is);
		int multiplie = is == null ? 1 : is.getAmount();
		return ( (((double)25/100)) * price )*multiplie;
	}
	
	@EventHandler
	public void onInvClose(InventoryCloseEvent e) {
		if (e.getInventory().getHolder() instanceof BuyerInventoryHolder) {
			if (e.getInventory().getItem(10) != null)
				e.getPlayer().getInventory().addItem(e.getInventory().getItem(10));
		}
	}
	
	@EventHandler
	public void onInvClickEvent(InventoryClickEvent e) {
		if (!(e.getInventory().getHolder() instanceof BuyerInventoryHolder)) {
			return;
		}
		if (!(e.getClickedInventory() instanceof PlayerInventory)) {
			if (e.getSlot() != 10) {
				e.setCancelled(true);
			}
		}

		final Inventory inv = e.getInventory();
		new BukkitRunnable() {
			@Override
			public void run() {
				inv.setItem(7+9, gold(inv));
			}
		}.runTaskLater(Emmber.getInstance(), 1);
	}
	
	@EventHandler
	public void onClick(InventoryClickEvent e) {
		if (!(e.getInventory().getHolder() instanceof BuyerInventoryHolder)) {
			return;
		}
		if (e.getClickedInventory() instanceof PlayerInventory) {
			return;
		}
		
		ItemStack is = e.getCurrentItem();
		if (is == null) return;
		if (!is.hasItemMeta()) return;
		if (!is.getItemMeta().hasDisplayName()) return;
		
		String dis = is.getItemMeta().getDisplayName();
		
		if (dis.equalsIgnoreCase("§c"+HebrewUtil.reverseHebrew("ביטול"))) {
			e.getWhoClicked().closeInventory();
		} else if (dis.equalsIgnoreCase("§a"+HebrewUtil.reverseHebrew("אישור"))) {
			double totalPrice = itemPrice(e.getClickedInventory().getItem(1+9));
			EmmberJob emmberJob = Emmber.getInstance().playerManager.get(e.getWhoClicked().getUniqueId());
			emmberJob.deposit((int) totalPrice);
			e.getClickedInventory().setItem(10, new ItemStack(Material.AIR));
			e.getClickedInventory().setItem(7+9, gold(e.getClickedInventory()));
			e.getWhoClicked().sendMessage("§a" + HebrewUtil.reverseHebrew("מטבעות") + " " + ChatColor.GOLD + ((int) totalPrice) + " §a" + HebrewUtil.reverseHebrew("מכרת את החפץ בתמורה ל"));
		}
		
	}
	
}
