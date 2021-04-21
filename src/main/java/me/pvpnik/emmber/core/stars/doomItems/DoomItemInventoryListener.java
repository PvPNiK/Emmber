package me.pvpnik.emmber.core.stars.doomItems;

import me.pvpnik.emmber.Emmber;
import me.pvpnik.emmber.api.jobs.EmmberJob;
import me.pvpnik.emmber.core.stars.StarInventory;
import me.pvpnik.emmber.utils.HebrewUtil;
import me.pvpnik.emmber.utils.HiddenStringUtils;
import me.pvpnik.emmber.utils.OUT;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ClickEvent.Action;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.*;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class DoomItemInventoryListener implements Listener {
	
	@EventHandler
	public void onMoveItem(InventoryMoveItemEvent e) {
		if (e.getDestination().getTitle().startsWith(DoomItemInventory.invTitle))
			e.setCancelled(true);
	}
	
	private HashMap<UUID, DoomItemTimer> timer = new HashMap<UUID, DoomItemTimer>();
	
	@EventHandler
	public void onInvOpen(InventoryOpenEvent e) {
		if (!e.getInventory().getTitle().startsWith(DoomItemInventory.invTitle))
			return;
		
		if (!(e.getPlayer() instanceof Player))
			return;
		
		Player p = (Player) e.getPlayer();
		
		if (timer.containsKey(p.getUniqueId())) {
			p.closeInventory();
			return;
		}
		
		timer.put(p.getUniqueId(), new DoomItemTimer(e.getInventory(), p));
	}
	
	@EventHandler
	public void onInvClose(InventoryCloseEvent e) {
		if (!e.getInventory().getTitle().startsWith(DoomItemInventory.invTitle))
			return;
		
		if (!(e.getPlayer() instanceof Player))
			return;
		
		Player p = (Player) e.getPlayer();
		
		if (!timer.containsKey(p.getUniqueId())) 
			return;
		
		timer.get(p.getUniqueId()).cancel();
		timer.remove(p.getUniqueId());
	}
	
	@EventHandler
	public void onItemClick(InventoryClickEvent e) {
		if (!(e.getWhoClicked() instanceof Player)) return;
		if (!e.getInventory().getTitle().startsWith(DoomItemInventory.invTitle)) return;
		if (e.getInventory().getType() == InventoryType.PLAYER) return;
		if (e.getClickedInventory() == null) return;
		if (e.getCurrentItem() == null) return;
		if (e.getCurrentItem().getType() == Material.AIR) return;
		e.setCancelled(true);
		
		ItemStack is = e.getCurrentItem();

		if (is.getType() == Material.STAINED_GLASS_PANE)
			return;
		Player p = (Player) e.getWhoClicked();
		if (is.getType() == Material.FIREWORK_CHARGE) {
			p.openInventory(StarInventory.get(p, null));
			return;
		}

		if (is.getType() == Material.DIAMOND_SPADE) {
			p.closeInventory();

			switch (is.getDurability()) {
			case 130: // info
				sendJSON(p, "click me(info)", "https://www.castleofessver.com/");
				return;
			case 133: // upgrade
				sendJSON(p, "click me(upgrade)", "https://www.castleofessver.com/");
				return;
			case 134:
				p.closeInventory();
				return;
			default:
				return;
			}
		}
		
		if (p.getInventory().firstEmpty() == -1) {
			p.closeInventory();
			p.sendMessage("please free up space");
			return;
		}
		
		ItemMeta im = is.getItemMeta();
		String name = im.getDisplayName();
		String vavSplit = "ו ";
		if (!(name.contains(vavSplit) && name.contains(":")))
			return;

		EmmberJob emmberJob = Emmber.getInstance().playerManager.get(p.getUniqueId());
		if (emmberJob == null) {
			OUT.toConsole("DoomItemInventoryListener, no cached emmber job! player:");
			OUT.toConsole(p.getDisplayName() + ", " + p.getUniqueId().toString());
			return;
		}
		
		String Smoney = ChatColor.stripColor(HebrewUtil.removeHebrewLetters(name.split(":")[0].split(vavSplit)[1])).trim();
		String Sdiamonds = ChatColor.stripColor(HebrewUtil.removeHebrewLetters(name.split(":")[0].split(vavSplit)[0])).trim();
		
		try {
			Integer.parseInt(Smoney);
			Integer.parseInt(Sdiamonds);
		} catch (Exception e2) {
			p.sendMessage("§4ERROR, please screen shot your doom inv, and the chat. and send it to one of the mods/admins. thx");
			p.sendMessage(Smoney);
			p.sendMessage(Sdiamonds);
		}
		
		int money = Integer.parseInt(Smoney);
		int diamonds = Integer.parseInt(Sdiamonds);
		
		if (!canRestoreItem(p, (int) (emmberJob.getMoney() - money), emmberJob.getDiamonds() - diamonds))
			return;
		
		name = name.split(",")[1];
		im.setDisplayName(name);
		
		List<String> lore = im.getLore();
		
		int id = Integer.parseInt(HiddenStringUtils.extractHiddenString(lore.get(lore.size()-1)));
		
		lore.remove(lore.size()-1);
		lore.remove(0);
		im.setLore(lore);
		
		is.setItemMeta(im);
		
		p.getInventory().addItem(is);
		p.updateInventory();
		p.closeInventory();
		//DoomItemManager.getInstance().remove(p.getUniqueId(), id);
		emmberJob.withdraw(money);
		emmberJob.setDiamonds(emmberJob.getDiamonds() - diamonds);
		p.sendMessage("item has been restored!");
	}
	
	private boolean canRestoreItem(Player p, int needMoney, int needDiamonds) {
		if (needDiamonds >= 0 && needMoney >= 0)
			return true;

		if (needMoney < 0 && needDiamonds >= 0) {
			p.sendMessage("you need more " + needMoney + " money");
		} else if (needMoney >= 0 && needDiamonds < 0) {
			p.sendMessage("you need more " + needDiamonds + " diamonds");
		} else {
			p.sendMessage("you need more " + needDiamonds + " diamonds and " + needMoney + " money");
		}

		return false;
	}

	private void sendJSON(Player p, String msg, String url){
		TextComponent text = new TextComponent(msg);
		text.setClickEvent(new ClickEvent(Action.OPEN_URL, url));
		p.spigot().sendMessage(text);
	}
	
}
