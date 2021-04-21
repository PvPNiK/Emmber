package me.pvpnik.emmber.sql;

import me.pvpnik.emmber.api.database.DataSource;
import me.pvpnik.emmber.api.database.SQLEmmber;
import me.pvpnik.emmber.utils.Pair;
import net.minecraft.server.v1_12_R1.DamageSource;
import org.bukkit.Bukkit;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarFlag;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

public class SQLRegionBossBar extends SQLEmmber {
	
	public SQLRegionBossBar() {
		super("region_boss_bar");
	}

	@Override
	public void createTable() {
		executeUpdate("CREATE TABLE `region_boss_bar` (" +
				"  `region` varchar(36) NOT NULL," +
				"  `title` text NOT NULL," +
				"  `barColor` enum('BLUE','GREEN','PINK','PURPLE','RED','WHITE','YELLOW') NOT NULL DEFAULT 'PINK'," +
				"  `barStyle` enum('SEGMENTED_10','SEGMENTED_12','SEGMENTED_20','SEGMENTED_6','SOLID') NOT NULL DEFAULT 'SOLID'," +
				"  `create_fog` tinyint(4) NOT NULL DEFAULT '0'," +
				"  `darken_sky` tinyint(4) NOT NULL DEFAULT '0'," +
				"  `play_boss_music` tinyint(4) NOT NULL DEFAULT '0'," +
				"  PRIMARY KEY (`region`)" +
				") ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;");
	}

	public BossBar get(String region) {
		String title = (String) get(region, "title");
		BarColor color = BarColor.valueOf((String) get(region, "barColor"));
		BarStyle style = BarStyle.valueOf((String) get(region, "barStyle"));

		BossBar bar = Bukkit.createBossBar(title, color, style);

		if ((int) get(region, "create_fog") == 1) {
			bar.addFlag(BarFlag.CREATE_FOG);
		}
		
		if ((int) get(region, "darken_sky") == 1) {
			bar.addFlag(BarFlag.CREATE_FOG);
		}
		
		if ((int) get(region, "play_boss_music") == 1) {
			bar.addFlag(BarFlag.CREATE_FOG);
		}
		
		return bar;
	}
	
	public boolean delete(String region) {
		return delete(new Pair<>("region", region));
	}

	public void insert(String region, BossBar bb) {
		if (exist(region)) {
			return;
		}
		insert(new Pair<>("region", region),
				new Pair<>("title", bb.getTitle()),
				new Pair<>("barColor", bb.getColor().name()),
				new Pair<>("barStyle", bb.getStyle().name()),
				new Pair<>("create_fog", bb.hasFlag(BarFlag.CREATE_FOG) ? 1 : 0),
				new Pair<>("darken_sky", bb.hasFlag(BarFlag.DARKEN_SKY) ? 1 : 0),
				new Pair<>("play_boss_music", bb.hasFlag(BarFlag.PLAY_BOSS_MUSIC) ? 1 : 0));
	}
	
	public void update(String region, BossBar bar) {
		if (delete(region)) {
			insert(region, bar);
		}
	}
	
	public boolean exist(String region) {
		return exist(new Pair<>("region", region));
	}
	
	public Set<String> getRegions() {
		Set<String> regions = new HashSet<String>();
		try {
			PreparedStatement ps = DataSource.getConnection().prepareStatement("SELECT region FROM " + getTable());
			ResultSet rs = ps.executeQuery();
			while(rs.next())
				regions.add(rs.getString("region"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return regions;
	}
	
	private Object get(String region, String field){
		return get(new Pair<>("region", region), field);
	}
	
}
