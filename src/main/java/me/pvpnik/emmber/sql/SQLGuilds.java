package me.pvpnik.emmber.sql;

import me.pvpnik.emmber.api.database.SQLEmmber;
import me.pvpnik.emmber.core.guild.Guild;
import me.pvpnik.emmber.utils.Pair;

import java.util.UUID;

public class SQLGuilds extends SQLEmmber {

    public SQLGuilds() {
        super("guilds");
    }

    @Override
    public void createTable() {
        executeUpdate("CREATE TABLE `guilds` (" +
                "  `name` varchar(36) NOT NULL," + // guild name
                "  `leader` varchar(36) NOT NULL," + // uuid
                "  `points` int(11) NOT NULL DEFAULT '0'," + // made for 3rd party use(website)
                "  PRIMARY KEY (`name`)" +
                ") ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;");
    }

    public boolean add(String name, UUID leader) {
        if (!exist(name)) {
            insert(new Pair<>("name", name), new Pair<>("leader", leader.toString()));
            return true;
        }
        return false;
    }

    public boolean delete(String name) {
        if (!exist(name)) {
            return false;
        }
        return delete(new Pair<>("name", name));
    }

    public boolean exist(String name) {
        return exist(new Pair<>("name", name));
    }
    public boolean existLeader(UUID uuid) {
        return exist(new Pair<>("leader", uuid.toString()));
    }

    public Guild getGuild(String name) {
        Object uuidObject = get(new Pair<>("name", name), "leader");
        if (uuidObject == null) {
            return null;
        }
        UUID uuid = UUID.fromString((String) uuidObject);
        //int points = (int) get(new Pair<>("name", name), "points");
        return new Guild(name, uuid);
    }

    public String getGuildName(UUID leader) {
        if (!existLeader(leader)) {
            return null;
        }
        return (String) get(new Pair<>("leader", leader.toString()), "name");
    }

}
