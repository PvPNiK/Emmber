package me.pvpnik.emmber.core.bank;

import me.pvpnik.emmber.Emmber;
import me.pvpnik.emmber.api.jobs.EmmberJob;
import me.pvpnik.emmber.core.bank.inventory.*;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ClickEvent.Action;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryMoveItemEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

public class BankListener implements Listener {

	@EventHandler
	public void onItemMoveToBankInv(InventoryMoveItemEvent e) {
		if (e.getDestination().getTitle().equals(BankMainInventory.invTitle)) {
			e.setCancelled(true);
		}
		
		if (isBankInv(e.getDestination())) {
			e.setCancelled(true);
		}
	}

	@EventHandler
	public void onMainInvClick(InventoryClickEvent e) {
		if (!e.getInventory().getTitle().equals(BankMainInventory.invTitle))
			return;

		if (!(e.getWhoClicked() instanceof Player))
			return;

		e.setCancelled(true);

		ItemStack is = e.getCurrentItem();

		if (is == null || is.getType() == Material.AIR)
			return;

		Player p = (Player) e.getWhoClicked();

		String displayName = is.getItemMeta().getDisplayName();

		if (displayName.equals(BankMainInventory.gold().getItemMeta().getDisplayName())) {
			p.openInventory(BankGoldInventory.getInv());
			return;
		}

		if (displayName.equals(BankMainInventory.crystal().getItemMeta().getDisplayName())) {
			p.openInventory(BankCrystalInventory.getInv());
			return;
		}

		if (displayName.equals(BankMainInventory.diamond().getItemMeta().getDisplayName())) {
			p.openInventory(BankDiamondInventory.getInv());
			return;
		}

	}
	
	@EventHandler
	public void onBackOptionInvClick(InventoryClickEvent e) {
		if (!isBankInv(e.getInventory()))
			return;
		
		ItemStack is = e.getCurrentItem();
		
		if (is == null || is.getType() == Material.AIR)
			return;
		
		if (is.isSimilar(BankItems.exit()))
			e.getWhoClicked().closeInventory();
		
		/*if (is.isSimilar(BankItems.help())) {
			e.getWhoClicked().closeInventory();
			
			e.getWhoClicked().sendMessage("--------------------");
			
			TextComponent text = new TextComponent(Emmber.getInstance().localFileManager.messages.getMessage("world.bank.conversion.tourl"));
			text.setColor(net.md_5.bungee.api.ChatColor.AQUA);
			text.setClickEvent(new ClickEvent(Action.OPEN_URL, "https://www.google.com"));
			e.getWhoClicked().spigot().sendMessage(text);
			
			e.getWhoClicked().sendMessage("--------------------");
		}*/
	}
	
	@EventHandler
	public void onBankInvClick(InventoryClickEvent e) {
		if (!isBankInv(e.getInventory())) {
			return;
		}
		if (!(e.getWhoClicked() instanceof Player)) {
			return;
		}
		if (e.getClickedInventory() == null) {
			return;
		}
		if (e.getClickedInventory() instanceof PlayerInventory) {
			return;
		}
		
		e.setCancelled(true);
		
		int slot = e.getSlot();
		
		if (slot < 18 || slot > 24) {
			return;
		}
		
		ItemStack to = e.getCurrentItem();
		
		if (to == null || to.getType() == Material.AIR) {
			return;
		}
		
		Inventory inv = e.getClickedInventory();
		ItemStack from = inv.getItem(slot-18);
		
		Player p = (Player) e.getWhoClicked();
		EmmberJob player = Emmber.getInstance().playerManager.get(p.getUniqueId());
		
		if (player == null) {
			p.closeInventory();
			return;
		}
		
		if (!BankItems.hasAmount(player, from)) {
			p.sendMessage(BankItems.getDenyMessage(from));
			return;
		}
		
		BankItems.give(player, to);
		BankItems.take(player, from);
		
		p.sendMessage(ChatColor.GREEN + Emmber.getInstance().localFileManager.messages.getMessage("world.bank.conversion.succ"));
	}

	private boolean isBankInv(Inventory inv) {
		if (inv.getTitle().equals(BankGoldInventory.invTitle))
			return true;
		
		if (inv.getTitle().equals(BankCrystalInventory.invTitle))
			return true;
		
		if (inv.getTitle().equals(BankDiamondInventory.invTitle))
			return true;

		return false;
	}

}
