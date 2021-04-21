package me.pvpnik.emmber.managers;

import me.pvpnik.emmber.api.files.ArmorBlockedFile;
import me.pvpnik.emmber.api.files.DatabaseFile;
import me.pvpnik.emmber.api.files.Messages;
import org.bukkit.plugin.Plugin;

/**
 * Holds all the local files
 */
public class LocalFileManager {

    public Messages messages = new Messages();
    public DatabaseFile database = new DatabaseFile();
    public ArmorBlockedFile armorBlocked = new ArmorBlockedFile();

    public LocalFileManager(Plugin plugin) {

    }

}
