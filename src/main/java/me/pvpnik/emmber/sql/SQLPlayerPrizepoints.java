package me.pvpnik.emmber.sql;

import me.pvpnik.emmber.api.database.DataSource;
import me.pvpnik.emmber.api.database.SQLEmmber;
import me.pvpnik.emmber.core.prizepoints.Prizepoints;
import me.pvpnik.emmber.utils.OUT;
import me.pvpnik.emmber.utils.Pair;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.UUID;

public class SQLPlayerPrizepoints extends SQLEmmber {
    public SQLPlayerPrizepoints() {
        super("prizepoints_players");
    }

    private String enumString() {
        String str = "";
        for (Prizepoints prizepoints : Prizepoints.values()) {
            str = str + "'" + prizepoints.name() + "',";
        }
        str = str.substring(0, str.length()-1);
        return str;
    }

    @Override
    public void createTable() {
        executeUpdate("CREATE TABLE `prizepoints_players` (" +
                "  `id` int(11) NOT NULL AUTO_INCREMENT," +
                "  `uuid` varchar(36) NOT NULL," +
                "  `prizepoint` enum(" + enumString() + ") NOT NULL," + // PrizepointsManager#Prizepoints
                "  `time` BIGINT NOT NULL," +
                "  PRIMARY KEY (`id`)" +
                ") ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;");
    }
    
    public void insert(UUID uuid, Prizepoints prizepoints) {
        insert(new Pair<>("uuid", uuid.toString()),
                new Pair<>("prizepoint", prizepoints.name()),
                new Pair<>("time", System.currentTimeMillis()));
    }
    
    public void update(UUID uuid, Prizepoints prizepoints) {
        if (!exist(uuid, prizepoints)) {
            insert(uuid, prizepoints);
            return;
        }
        try (Connection connection = DataSource.getConnection();
                PreparedStatement pst = connection.prepareStatement("UPDATE `prizepoints_players` SET `time`=? WHERE `uuid` = ? AND `prizepoint` = ?")) {
            pst.setLong(1, System.currentTimeMillis());
            pst.setString(2, uuid.toString());
            pst.setObject(3, prizepoints.name());
            pst.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public Long getTime(UUID uuid, Prizepoints prizepoints) {
        long time = -1;
        try (Connection connection = DataSource.getConnection();
             PreparedStatement pst = connection.prepareStatement("SELECT `time` FROM `prizepoints_players` WHERE `uuid` = ? AND `prizepoint` = ?")) {
            pst.setString(1, uuid.toString());
            pst.setObject(2, prizepoints.name());
            ResultSet resultSet = pst.executeQuery();
            if (resultSet.next()) {
                time = resultSet.getLong(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return time;
        }
    }
    
    public boolean exist(UUID uuid, Prizepoints prizepoints) {
        return exist(new Pair<>("uuid", uuid.toString()), new Pair<>("prizepoint", prizepoints.name()));
    }
    
}
