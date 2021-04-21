package me.pvpnik.emmber.core.shield;

import me.mattstudios.mfgui.gui.components.ItemBuilder;
import me.mattstudios.mfgui.gui.guis.Gui;
import me.mattstudios.mfgui.gui.guis.GuiItem;
import me.pvpnik.emmber.Emmber;
import me.pvpnik.emmber.items.Shield;
import me.pvpnik.emmber.utils.HebrewUtil;
import me.pvpnik.emmber.utils.ItemUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class ShieldInventory {

	private ShieldInventory() {}

	public static final String invTitle = Emmber.getInstance().localFileManager.messages.getMessage("items.shield.shields");

	public static Inventory getShieldInv(Player p) {
		
		ArrayList<ItemStack> shields = new ArrayList<>();
		for (int i = 1; i <= 30; i++) {
			if (p.hasPermission("perm.shield." + i)) {
				shields.add(new Shield((byte) i));
			}
		}

		Gui gui = new Gui(getInvSizeByItems(shields.size()) / 9, invTitle);

		for (ItemStack is : shields) {
			gui.addItem(ItemBuilder.from(is).asGuiItem(e -> {
				Player player = (Player) e.getWhoClicked();
				player.getInventory().setItemInOffHand(e.getCurrentItem());
				player.updateInventory();
				player.closeInventory();
				Emmber.getInstance().localFileManager.messages.sendMessage(player, ChatColor.GREEN, "player.shieldupdated");
			}));
		}

		gui.update();
		return gui.getInventory();
	}

	private static int getInvSizeByItems(int itemCount) {
		if (itemCount <= 9)
			return 9;
		if (itemCount <= 18)
			return 18;
		if (itemCount <= 27)
			return 27;
		if (itemCount <= 36)
			return 36;
		if (itemCount <= 45)
			return 45;
		if (itemCount <= 54)
			return 54;
		return 54;
	}

}
