package me.pvpnik.emmber.sql;

import me.pvpnik.emmber.api.database.SQLEmmber;
import me.pvpnik.emmber.api.jobs.EmmberJob;
import me.pvpnik.emmber.core.jobs.BaseStats;
import me.pvpnik.emmber.core.jobs.PlayerPath;
import me.pvpnik.emmber.utils.OUT;
import me.pvpnik.emmber.utils.Pair;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.UUID;

public class SQLPlayers extends SQLEmmber {
    public SQLPlayers() {
        super("players");
    }

    @Override
    public void createTable() {
        executeUpdate("CREATE TABLE `players` (" +
                "  `uuid` varchar(36) NOT NULL," +
                "  `name` varchar(16) NOT NULL," +
                "  `path` enum('NONE','SAND','MOUNTAIN','EARTH') NOT NULL DEFAULT 'NONE'," +
                "  `spawn` enum('EssverHospital') NOT NULL DEFAULT 'EssverHospital'," +
                "  `level` int(11) NOT NULL DEFAULT '1'," +
                "  `exp` float NOT NULL DEFAULT '0'," +
                "  `points` int(11) NOT NULL DEFAULT '0'," +
                "  `diamonds` int(11) NOT NULL DEFAULT '50'," +
                "  `money` int(11) NOT NULL DEFAULT '0'," +
                "  `crystal` int(11) NOT NULL DEFAULT '0'," +
                "  PRIMARY KEY (`uuid`)" +
                ") ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;");
    }

    public EmmberJob get(UUID uuid) {
        EmmberJob emmberJob = new EmmberJob(uuid);
        if (!exist(uuid)) {
            return emmberJob;
        }
        emmberJob.setLevel(BaseStats.getLevel((Integer) getValue(uuid, "level")), true);
        emmberJob.setPath(PlayerPath.valueOf((String) getValue(uuid, "path")));
        //emmberJob.setSpawn();
        emmberJob.setExp((Float) getValue(uuid, "exp"));
        emmberJob.setPoints((Integer) getValue(uuid, "points"));
        emmberJob.setDiamonds((Integer) getValue(uuid, "diamonds"));
        emmberJob.setMoney((Integer) getValue(uuid, "money"));
        emmberJob.setCrystal((Integer) getValue(uuid, "crystal"));
        return emmberJob;
    }

    @Nullable
    public Object getValue(UUID uuid, String columnLabel) {
        if (!exist(uuid)) {
            return null;
        }
        return get(new Pair<>("uuid", uuid.toString()), columnLabel);
    }

    public void insert(@Nonnull EmmberJob player) {
        if (exist(player.getUuid())) {
            return;
        }
        insert(new Pair<>("uuid", player.getUuid().toString()),
                new Pair<>("name", player.getPlayer() == null ? "null" : player.getPlayer().getName()),
                new Pair<>("path", player.getPath().name()),
                new Pair<>("spawn", player.getSpawn().name()),
                new Pair<>("level", player.getLevel()),
                new Pair<>("exp", player.getExp()),
                new Pair<>("points", player.getPoints()),
                new Pair<>("diamonds", player.getDiamonds()),
                new Pair<>("money", player.getMoney()),
                new Pair<>("crystal", player.getCrystal()));
    }

    public void update(@Nonnull EmmberJob player) {
        if (!exist(player.getUuid())) {
            insert(player);
            return;
        }
        update(new Pair<>("uuid", player.getUuid().toString()),
                new Pair<>("name", player.getPlayer() == null ? "null" : player.getPlayer().getName()),
                new Pair<>("path", player.getPath().name()),
                new Pair<>("spawn", player.getSpawn().name()),
                new Pair<>("level", player.getLevel()),
                new Pair<>("exp", player.getExp()),
                new Pair<>("points", player.getPoints()),
                new Pair<>("diamonds", player.getDiamonds()),
                new Pair<>("money", player.getMoney()),
                new Pair<>("crystal", player.getCrystal()));
    }

    public void delete(UUID uuid) {
        delete(new Pair<>("uuid", uuid.toString()));
    }

    public boolean exist(UUID uuid) {
        return exist(new Pair<>("uuid", uuid.toString()));
    }

}
