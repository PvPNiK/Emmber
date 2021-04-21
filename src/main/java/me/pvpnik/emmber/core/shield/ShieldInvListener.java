package me.pvpnik.emmber.core.shield;

import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.ItemStack;

public class ShieldInvListener implements Listener {
	
	@EventHandler
	public void onShieldClick(InventoryClickEvent e) {
		if (!(e.getWhoClicked() instanceof Player))
			return;
		
		if (e.getInventory().getType() != InventoryType.CRAFTING)
			return;
		
		Player p = (Player) e.getWhoClicked();
		
		if (p.getGameMode() == GameMode.CREATIVE || p.getGameMode() == GameMode.SPECTATOR)
			return;
		
		if (e.getSlot() != 40)
			return;
		
		if (e.getCurrentItem() == null)
			return;
		
		if (e.getCurrentItem().getType() != Material.SHIELD)
			return;
		
		p.openInventory(ShieldInventory.getShieldInv(p));
	}
	
}
