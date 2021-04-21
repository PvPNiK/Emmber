package me.pvpnik.emmber.managers;

import com.mewin.WGRegionEvents.events.RegionEnterEvent;
import com.mewin.WGRegionEvents.events.RegionLeaveEvent;
import com.sk89q.worldguard.bukkit.WGBukkit;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;
import me.pvpnik.emmber.Emmber;
import me.pvpnik.emmber.sql.SQLRegionBossBar;
import org.bukkit.Bukkit;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;

public class RegionBossBarManager implements Listener {

	private HashMap<String, BossBar> regions = new HashMap<String, BossBar>();
	private SQLRegionBossBar sql = Emmber.getInstance().sqlManager.regionBossBar;

	public RegionBossBarManager() {
		Bukkit.getPluginManager().registerEvents(this, Emmber.getInstance());
	}

	public void load() {
		regions.clear();
		for (String region : sql.getRegions()) {
			add(region, sql.get(region));
		}
	}
	
	public void show(Player p, String region) {
		if (!regionHasBossBar(region)) return;
		// if (!regions.get(region).getPlayers().contains(p))
		regions.get(region).addPlayer(p);
	}

	public void unShow(Player p, String region) {
		if (!regionHasBossBar(region))
			return;
		// if (regions.get(region).getPlayers().contains(p))
		regions.get(region).removePlayer(p);
	}
	
	public void unShow(Player p) {
		for (String region : regions.keySet())
			unShow(p, region);
	}
	
	public BossBar get(String region) {
		return regions.get(region);
	}
	
	public void showAll(String region) {
		for (Player p : Bukkit.getOnlinePlayers()) {
			for (ProtectedRegion r : WGBukkit.getRegionManager(p.getWorld()).getApplicableRegions(p.getLocation())) {
				if (r.getId().equalsIgnoreCase(region)) {
					show(p, region);
				}
			}
		}
	}
	
	public void unShowAll(String region) {
		regions.get(region).removeAll();
	}
	
	public void unShowAll() {
		for (String region : regions.keySet())
			unShowAll(region);
	}
	
	public boolean add(String region, BossBar bb) {
		if (regions.containsKey(region)) return false;
		
		regions.put(region, bb);
		showAll(region);
		return true;
	}
	
	
	public boolean remove(String region) {
		if (!regionHasBossBar(region)) return false;
		
		unShowAll(region);

		sql.delete(region);
		regions.remove(region);
		
		return true;
	}
	
	public boolean regionHasBossBar(String region) {
		if (regions.containsKey(region)) // region in hashmap
			return true;
		
		if (!sql.exist(region)) // region not in hashmap and not in sql
			return false;
		
		// region not in hashmap, but in sql, so add region to hashmap;
		add(region, sql.get(region));
		return true;
	}

	@EventHandler
	public void onRegionEnter(RegionEnterEvent e) {
		// p.sendMessage("You just entered " + e.getRegion().getId());
		Player p = e.getPlayer();

		RegionBossBarManager manager = Emmber.getInstance().emmberCoreManager.regionBossBar;

		manager.unShow(p);
		manager.show(p, e.getRegion().getId());
	}

	@EventHandler
	public void onRegionLeave(RegionLeaveEvent e) {
		// p.sendMessage("You just leaved " + e.getRegion().getId());
		final Player p = e.getPlayer();
		RegionBossBarManager manager = Emmber.getInstance().emmberCoreManager.regionBossBar;

		manager.unShow(p);

		new BukkitRunnable() {
			@Override
			public void run() {
				for (ProtectedRegion r : WGBukkit.getRegionManager(p.getWorld()).getApplicableRegions(p.getLocation())) {
					Emmber.getInstance().emmberCoreManager.regionBossBar.show(p, r.getId());
				}
			}
		}.runTaskLater(Emmber.getInstance(), 1);

	}

}
