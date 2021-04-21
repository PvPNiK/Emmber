package me.pvpnik.emmber.core.stars.doomItems;

import me.pvpnik.emmber.Emmber;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.List;

public class DoomItemTimer extends BukkitRunnable {

	private Inventory inv;
	private int day;
	private int hour;
	private int min;
	private int sec;
	private int overall;
	private Player p;
	
	public DoomItemTimer(Inventory inv, Player p) {
		this.inv = inv;
		this.p = p;
		this.runTaskTimer(Emmber.getInstance(), 20, 20);
	}

	@Override
	public void run() {
		
		if (p.getOpenInventory() == null || !p.isOnline() || p == null) {
			cancel();
			return;
		}
		
		// d:hh:mm:ss
		for (int i = 0; i < inv.getSize(); i++) {
			
			ItemStack is = inv.getItem(i);
			
			if (is == null || is.getType() == Material.AIR)
				continue;
			
			if (!DoomItemInventory.isDoomItem(is))
				continue;
			
			ItemMeta im = is.getItemMeta();
			List<String> lore = im.getLore();
			
			String time = lore.get(0);
			
			getTime(time);
			
			overall = sec + 60*min + 3600*hour + 86400*day;
			
			overall--;
			if (overall <= -1) {
				DoomItemInventory.openInv(p);
				continue;
			}
			
			setTime();
			
			lore.set(0, day + ":" + hour + ":" + min + ":" + sec);
			im.setLore(lore);
			
			is.setItemMeta(im);
			inv.setItem(i, is);
		}
	}
	
	private void getTime(String time) {
		sec = Integer.parseInt(time.split(":")[3]);
		min = Integer.parseInt(time.split(":")[2]);
		hour = Integer.parseInt(time.split(":")[1]);
		day = Integer.parseInt(time.split(":")[0]);
	}
	
	private void setTime() {
		day = overall / 86400;
		hour = (overall % 86400) / 3600;
		min = (overall % 3600) / 60;
		sec = (overall % 3600) % 60;
	}

}
