package me.pvpnik.emmber.sql;

import me.pvpnik.emmber.api.database.SQLEmmber;
import me.pvpnik.emmber.utils.Pair;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class SQLGuildMembers extends SQLEmmber {

    public SQLGuildMembers() {
        super("guildMembers");
    }

    @Override
    public void createTable() {
        executeUpdate("CREATE TABLE `guildMembers` (" +
                "  `member` varchar(36) NOT NULL," + // uuid
                "  `name` varchar(36) NOT NULL," + // guild name
                "  PRIMARY KEY (`member`)" +
                ") ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;");
    }

    public List<UUID> get(String name) {
        List<Object> membersObject = getAll(new Pair<>("name", name), "member");
        List<UUID> members = new ArrayList<>();
        membersObject.forEach(o -> members.add(UUID.fromString((String) o)));
        return members;
    }

    public String getGuildName(UUID uuid) {
        if (!exist(uuid)) {
            return null;
        }
        return (String) get(new Pair<>("member", uuid.toString()), "name");
    }

    public boolean add(String name, UUID uuid) {
        if (exist(uuid)) {
            return false;
        }
        return insert(new Pair<>("member", uuid.toString()), new Pair<>("name", name));
    }

    public boolean updateGuild(UUID uuid, String name) {
        if (!exist(uuid)) {
            return false;
        }
        return update(new Pair<>("member", uuid.toString()), new Pair<>("name", name));
    }

    public boolean remove(UUID uuid) {
        if (!exist(uuid)) {
            return false;
        }
        return delete(new Pair<>("member", uuid.toString()));
    }

    public boolean exist(UUID uuid) {
        return exist(new Pair<>("member", uuid.toString()));
    }

}
