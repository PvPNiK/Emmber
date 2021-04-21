package me.pvpnik.emmber.core.stars;

import me.pvpnik.emmber.Emmber;
import me.pvpnik.emmber.api.jobs.EmmberJob;
import me.pvpnik.emmber.utils.HebrewUtil;
import me.pvpnik.emmber.utils.OUT;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ClickEvent.Action;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryMoveItemEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Random;

public class StarUpgrade implements Listener {
	// if (!e.getInventory().getTitle().equals(StarInventory.invTitle))
	@EventHandler
	public void onMoveItem(InventoryMoveItemEvent e) {
		if (e.getDestination().getHolder() instanceof StarsInventoryHolder)
			e.setCancelled(true);
	}
	
	@EventHandler
	public void onInvClose(InventoryCloseEvent e) {
		if (!(e.getPlayer() instanceof Player)) return;
		if (!(e.getInventory().getHolder() instanceof StarsInventoryHolder)) return;
		Player p = (Player) e.getPlayer();
		
		if (StarUpgradeAnimation.pStage.containsKey(p.getUniqueId())) {
			
			if (StarUpgradeAnimation.pStage.get(p.getUniqueId()) != AnimationStage.OFF) {
				
				new BukkitRunnable() {
					@Override
					public void run() {
						if (StarUpgradeAnimation.pInv.containsKey(p.getUniqueId()))
							p.openInventory(StarUpgradeAnimation.pInv.get(p.getUniqueId()));
					}
				}.runTaskLater(Emmber.getInstance(), 1);
				
				return;
			}
			
		}
		
		if (StarUpgradeAnimation.hasItem(p.getUniqueId())) return;
		if (e.getInventory().getItem(10) == null) return;
		p.getInventory().addItem(e.getInventory().getItem(10));
		p.updateInventory();
	}
	
	@EventHandler
	public void onAnimationInvClick(InventoryClickEvent e) {
		
	}
	
	@EventHandler
	public void onItemClick(InventoryClickEvent e) {
		if (!(e.getWhoClicked() instanceof Player)) return;
		if (!(e.getInventory().getHolder() instanceof StarsInventoryHolder)) return;
		
		e.setCancelled(true);
		
		if (StarUpgradeAnimation.hasItem(e.getWhoClicked().getUniqueId())) return;
		if (e.getClickedInventory() == null) return;
		if (e.getCurrentItem() == null) return;
		if (e.getCurrentItem().getType() == Material.AIR) return;

		final int slot = e.getSlot();

		if (e.getClickedInventory().getType() == InventoryType.PLAYER) {

			if (e.getInventory().getItem(10) != null)
				return;

			ItemStack is = e.getCurrentItem();
			
			/*if (!UsableWeapons.getInstance().has(is) && !ItemFactory.isArmor(is))
				return;*/

			e.getClickedInventory().setItem(slot, new ItemStack(Material.AIR));
			((Player) e.getWhoClicked()).updateInventory();

			e.getWhoClicked().openInventory(StarInventory.get((Player) e.getWhoClicked(), is));
			if (e.getWhoClicked().getInventory().firstEmpty() == -1) {
				e.getWhoClicked().closeInventory();
				e.getWhoClicked().sendMessage("please free up space");
			}
			return;
		} else {

			if (slot != 10)
				return;

			e.getWhoClicked().openInventory(StarInventory.get((Player) e.getWhoClicked(), null));
			return;

		}
	}
	
	@EventHandler
	public void onOptionsClick(InventoryClickEvent e) {
		if (!(e.getWhoClicked() instanceof Player)) return;
		if (!(e.getInventory().getHolder() instanceof StarsInventoryHolder)) return;
		if (e.getClickedInventory() == null) return;
		if (e.getCurrentItem() == null) return;
		if (e.getCurrentItem().getType() == Material.AIR) return;
		if (e.getClickedInventory().getType() == InventoryType.PLAYER) return;
		
		e.setCancelled(true);
		final int slot = e.getSlot();
		Player p = (Player) e.getWhoClicked();
		EmmberJob emmberJob = Emmber.getInstance().playerManager.get(p.getUniqueId());
		if (emmberJob == null) {
			OUT.toConsole("StarUpgrade, no cached emmber job! player:");
			OUT.toConsole(p.getDisplayName() + ", " + p.getUniqueId().toString());
			return;
		}
		
		if (slot == 16) { // doom inv
			//DoomItemInventory.openInv(p);
			p.sendMessage(ChatColor.RED + "Soon");
			return;
		}
		
		if (slot == 15) { // help
			p.closeInventory();
			
			TextComponent second = new TextComponent(HebrewUtil.reverseHebrew("למידע והדרכה אודות המערכת"));
			second.setClickEvent(new ClickEvent(Action.OPEN_URL, "https://www.castleofessver.com/"));
			
			TextComponent first = new TextComponent(HebrewUtil.reverseHebrew("לחצו כאן"));
			first.setClickEvent(new ClickEvent(Action.OPEN_URL, "https://www.castleofessver.com/"));
			
			p.spigot().sendMessage(first, second);
			return;
		}
		
		ItemStack is = e.getInventory().getItem(10);
		if (is == null) return;
		
		if (slot == 13) { // upgrade
			
			if (Stars.getLevel(is) == 7) return;
			if (StarUpgradeAnimation.hasItem(p.getUniqueId())) return;
			
			int upgradeCost = Stars.getUpgradeCost(is);
			
			if (emmberJob.getMoney() < upgradeCost) {
				p.closeInventory();
				p.sendMessage("§cnot enought money to UPGRADE the item.");
				return;
			}

			emmberJob.withdraw(upgradeCost);
			
			int chance = new Random().nextInt(99);
			int upgradeChance = Stars.getUpgradeChance(is);
			//Bukkit.broadcastMessage("chance: " + chance + ", upgradeChance: " + upgradeChance);
			if (chance < upgradeChance) { // upgraded
				StarUpgradeAnimation.addItem(p.getUniqueId(), is);
			} else { // not upgraded
				StarUpgradeAnimation.addItem(p.getUniqueId(), new ItemStack(Material.AIR));
				if (Stars.getLevel(is) >= 3) {
					if (new Random().nextInt(100) <= 50) {
						StarUpgradeAnimation.addItem(p.getUniqueId(), null);
						long currentTimeMillis = System.currentTimeMillis();
						//DoomItemManager.getInstance().add(p.getUniqueId(), is, currentTimeMillis, p.hasPermission("avadon.chest.perm"));
					}
				}
			}
			
			Inventory sInv = StarInventory.get(p, is);
			p.openInventory(sInv);
			StarUpgradeAnimation.playFisrtAnimation(sInv, p);
			
			return;
		}else if (slot == 14) { // reset
			if (Stars.getLevel(is) == 0) return;
			
			int resetCost = Stars.getResetCost(is);
			
			if (emmberJob.getMoney() < resetCost) {
				p.closeInventory();
				p.sendMessage("§cnot enought money to RESET the item.");
				return;
			}
			
			ItemStack original = Stars.reset(is);
			if (original == null) return;

			emmberJob.withdraw(resetCost);
			
			e.getInventory().setItem(10, original);
			
			for (int i = 0; i < 7; i++)
				e.getInventory().setItem(37+i, new ItemStack(Material.AIR));
			
			p.updateInventory();
			//p.openInventory(StarInventory.get(p, original));
			return;
		}
	}
	
}
