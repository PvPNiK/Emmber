package me.pvpnik.emmber.api.database;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import me.pvpnik.emmber.Emmber;
import me.pvpnik.emmber.utils.OUT;
import net.milkbowl.vault.chat.Chat;
import org.bukkit.ChatColor;

import java.net.ConnectException;
import java.sql.Connection;
import java.sql.SQLException;

public class DataSource {

    private static HikariConfig config;
    private static HikariDataSource ds;

    static {
        Emmber emmber = Emmber.getInstance();
        config = new HikariConfig();
        config.setJdbcUrl("jdbc:mysql://" + emmber.localFileManager.database.getValue("host") + "/" + emmber.localFileManager.database.getValue("database") + "?useSSL=false");
        config.setUsername(emmber.localFileManager.database.getValue("user"));
        config.setPassword(emmber.localFileManager.database.getValue("pass"));
        config.addDataSourceProperty("cachePrepStmts" , "true");
        config.addDataSourceProperty("prepStmtCacheSize" , "250");
        config.addDataSourceProperty("prepStmtCacheSqlLimit" , "2048");
        config.setLeakDetectionThreshold(60 * 1000);
        OUT.toConsole(ChatColor.AQUA + "dataSource static");
        ds = new HikariDataSource(config);
    }

    private DataSource() {}

    public static Connection getConnection() throws SQLException {
        return ds.getConnection();
    }

    // Close the datasource
    public static void closeConnection(){
        if (ds != null) {
            ds.close();
        }
    }

}