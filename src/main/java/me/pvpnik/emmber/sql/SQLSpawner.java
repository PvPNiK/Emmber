package me.pvpnik.emmber.sql;

import me.pvpnik.emmber.api.database.SQLEmmber;
import me.pvpnik.emmber.api.spawner.EntitySpawner;
import me.pvpnik.emmber.utils.Pair;
import org.bukkit.Location;

public class SQLSpawner extends SQLEmmber {
    public SQLSpawner() {
        super("spawners");
    }

    @Override
    public void createTable() {
        executeUpdate("CREATE TABLE `spawners` (" +
                "  `id` VARCHAR(32) NOT NULL," +
                "  `mob` int NOT NULL," +
                "  `maxspawn` int NOT NULL," +
                "  `cooldown` int NOT NULL," +
                "  `locations` longtext NOT NULL," +
                "  PRIMARY KEY (`id`)" +
                ") ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin");
    }

    public void insert(EntitySpawner spawner) {
        if (exist(spawner.getId())) {
            return;
        }
        insert(new Pair<>("id", spawner.getId()),
                new Pair<>("mob", 1),
                new Pair<>("maxspawn", spawner.getMaxspawn()),
                new Pair<>("cooldown", spawner.getCooldown()),
                new Pair<>("locations", locationsToString(spawner)));
    }


    public void update(EntitySpawner spawner) {
        if (!exist(spawner.getId())) {
            insert(spawner);
            return;
        }
        update(new Pair<>("id", spawner.getId()),
                new Pair<>("mob", 1),
                new Pair<>("maxspawn", spawner.getMaxspawn()),
                new Pair<>("cooldown", spawner.getCooldown()),
                new Pair<>("locations", locationsToString(spawner)));
    }

    public boolean exist(String id) {
        return exist(new Pair<>("id", id));
    }

    public void delete(String id) {
        delete(new Pair<>("id", id));
    }

    private String locationsToString(EntitySpawner spawner) {
        String locs = "";
        for (Location l : spawner.getLocations()) {
            locs = locs + l.getWorld().getName() + "," + l.getBlockX() + "," + l.getBlockY() + "," + l.getBlockZ() + ";";
        }
        if (locs.endsWith(";")) {
            locs = locs.substring(0, locs.length() - 1);
        }
        return locs.isEmpty() ? "none" : locs;
    }

}
