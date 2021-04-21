package me.pvpnik.emmber.core.stars.doomItems;

import me.pvpnik.emmber.api.database.DataSource;
import me.pvpnik.emmber.utils.ItemFactory;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class DoomItemManager {

	private static final DoomItemManager INSTANCE = new DoomItemManager();

	//public static DoomItemManager getInstance() {
	//	return INSTANCE;
	//}

	private String table = "doom";

	private HashMap<UUID, HashMap<Integer, ItemStack>> playerItems = new HashMap<UUID, HashMap<Integer, ItemStack>>();

	public void load() {
		playerItems.clear();

		for (UUID uuid : getAllUUIDS()) {
			HashMap<Integer, ItemStack> ids = new HashMap<Integer, ItemStack>();

			for (int i : getAllIds(uuid))
				ids.put(i, getItem(i));

			playerItems.put(uuid, ids);

		}
	}

	public void load(UUID uuid) {
		playerItems.remove(uuid);

		HashMap<Integer, ItemStack> ids = new HashMap<Integer, ItemStack>();

		for (int i : getAllIds(uuid))
			ids.put(i, getItem(i));

		playerItems.put(uuid, ids);
	}

	public void add(UUID uuid, ItemStack is, long currentTimeMillis, boolean perm) {
		try (Connection connection = DataSource.getConnection();){
			PreparedStatement pre = connection
					.prepareStatement("INSERT INTO " + table + "(`uuid`, `item`, `date`) VALUES (?,?,?)");
			pre.setString(1, uuid.toString());
			pre.setString(2, ItemFactory.serializeItemStack(is));
			pre.setLong(3, perm ? (currentTimeMillis + 1209600000) : (currentTimeMillis + 604800000));
			pre.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void update(ItemStack is, int id) {
		try (Connection connection = DataSource.getConnection();
		PreparedStatement ps = connection.prepareStatement("UPDATE " + table + " SET `item`= ? WHERE `id` = ?")) {
			ps.setString(1, ItemFactory.serializeItemStack(is));
			ps.setInt(2, id);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void remove(UUID uuid, ItemStack is) {
		try (Connection connection = DataSource.getConnection();
		PreparedStatement ps = connection
					.prepareStatement("DELETE FROM " + table + " WHERE `uuid` = ? AND `item` = ?")) {
			ps.setString(1, uuid.toString());
			ps.setString(2, ItemFactory.serializeItemStack(is));
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void remove(UUID uuid, int id) {
		try (Connection connection = DataSource.getConnection();
		PreparedStatement ps = connection
					.prepareStatement("DELETE FROM " + table + " WHERE `uuid` = ? AND `id` = ?")) {
			ps.setString(1, uuid.toString());
			ps.setInt(2, id);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void removeAll(UUID uuid) {
		try (Connection connection = DataSource.getConnection();
		PreparedStatement ps = connection.prepareStatement("DELETE FROM " + table + " WHERE `uuid` = ?")) {
			ps.setString(1, uuid.toString());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void update(Player p) {
		update();
		int chestSize = p.hasPermission("avadon.chest.perm") ? 14 : 7;
		if (getItems(p.getUniqueId()).size() <= chestSize)
			return;
		removeOldestItem(p.getUniqueId());
		update(p);
	}

	public void update() {
		long curentTime = System.currentTimeMillis();
		try (Connection connection = DataSource.getConnection();
		PreparedStatement ps = connection.prepareStatement("DELETE FROM " + table + " WHERE `date` < ?")) {
			ps.setLong(1, curentTime);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void removeOldestItem(UUID uuid) {
		try (Connection connection = DataSource.getConnection();
		PreparedStatement ps = connection
					.prepareStatement("DELETE FROM " + table + " WHERE `uuid` = ? ORDER BY `date` LIMIT 1")) {
			ps.setString(1, uuid.toString());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public long getDate(int id) {
		try (Connection connection = DataSource.getConnection();
		PreparedStatement ps = connection.prepareStatement("SELECT date FROM " + table + " WHERE id = ?")) {
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			while (rs.next())
				return rs.getLong("date");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return -1;
	}

	public ItemStack getItem(int id) {
		try (Connection connection = DataSource.getConnection();
		PreparedStatement ps = connection.prepareStatement("SELECT item FROM " + table + " WHERE id = ?")) {
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			while (rs.next())
				return ItemFactory.deserializeItemStack(rs.getString("item"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public Set<String> getItems(UUID uuid) {
		Set<String> items = new HashSet<String>();
		try (Connection connection = DataSource.getConnection();
		PreparedStatement ps = connection.prepareStatement("SELECT * FROM " + table + " WHERE uuid = ?")) {
			ps.setString(1, uuid.toString());
			ResultSet rs = ps.executeQuery();
			while (rs.next())
				items.add(rs.getString("item") + ";" + rs.getInt("id"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return items;
	}

	public Set<UUID> getAllUUIDS() {
		Set<UUID> uuids = new HashSet<UUID>();
		try (Connection connection = DataSource.getConnection();
		PreparedStatement ps = connection.prepareStatement("SELECT uuid FROM " + table)) {
			ResultSet rs = ps.executeQuery();
			while (rs.next())
				uuids.add(UUID.fromString(rs.getString("name")));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return uuids;
	}

	public Set<Integer> getAllIds(UUID uuid) {
		Set<Integer> ids = new HashSet<Integer>();
		try (Connection connection = DataSource.getConnection();
		PreparedStatement ps = connection.prepareStatement("SELECT id FROM " + table + " WHERE `uuid` = ?")) {
			ps.setString(1, uuid.toString());
			ResultSet rs = ps.executeQuery();
			while (rs.next())
				ids.add(rs.getInt("id"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ids;
	}

}
