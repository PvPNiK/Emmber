package me.pvpnik.emmber.sql;

import me.pvpnik.emmber.api.database.SQLEmmber;

public class SQLDoomItems extends SQLEmmber {
    public SQLDoomItems() {
        super("doom");
    }

    @Override
    public void createTable() {
        executeUpdate("CREATE TABLE `doom` (" +
                "  `id` int(11) NOT NULL AUTO_INCREMENT," +
                "  `uuid` varchar(36) NOT NULL," +
                "  `item` text NOT NULL," +
                "  `date` bigint(11) NOT NULL," +
                "  PRIMARY KEY (`id`)" +
                ") ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;");
    }

}
