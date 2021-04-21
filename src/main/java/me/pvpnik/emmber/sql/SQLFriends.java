package me.pvpnik.emmber.sql;

import me.pvpnik.emmber.api.database.SQLEmmber;
import me.pvpnik.emmber.utils.Pair;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class SQLFriends extends SQLEmmber {

    public SQLFriends() {
        super("friends");
    }

    @Override
    public void createTable() {
        executeUpdate("CREATE TABLE `friends` (" +
                "  `id` int(11) NOT NULL AUTO_INCREMENT," +
                "  `uuid` varchar(36) NOT NULL," +
                "  `friend` varchar(36) NOT NULL," +
                "  PRIMARY KEY (`id`)" +
                ") ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;");
    }

    public void addFriend(UUID player, UUID friend) {
        if (friends(player, friend)) {
            return;
        }
        insert(new Pair<>("uuid", player.toString()), new Pair<>("friend", friend.toString()));
    }

    public void removeFriend(UUID player, UUID friend) {
        if (!friends(player, friend)) {
            return;
        }
        delete(new Pair<>("uuid", player.toString()), new Pair<>("friend", friend.toString()));
    }

    public List<UUID> getFriends(UUID player) {
        List<Object> friendsObject = getAll(new Pair<>("uuid", player.toString()), "friend");
        List<UUID> friends = new ArrayList<>();
        friendsObject.forEach(o -> friends.add(UUID.fromString((String) o)));
        return friends;
    }

    public boolean friends(UUID player, UUID friend) {
        return exist(new Pair<>("uuid", player.toString()), new Pair<>("friend", friend.toString()));
    }
}
