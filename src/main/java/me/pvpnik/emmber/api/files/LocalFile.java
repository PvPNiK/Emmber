package me.pvpnik.emmber.api.files;

import me.pvpnik.emmber.Emmber;
import me.pvpnik.emmber.utils.OUT;
import org.bukkit.configuration.MemorySection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.InputStreamReader;
import java.util.HashMap;

public class LocalFile {

    private String fileName;
    FileConfiguration yamlConfiguration;
    private HashMap<String, String> messages;

    public LocalFile(String fileName) {
        this.fileName = fileName;
        messages = new HashMap<String, String>();
        reloadConfig();
        loadMessages();
    }

    /**
     * Loads the physical file
     */
    public void reloadConfig() {
        this.yamlConfiguration = new YamlConfiguration();
        try {
            yamlConfiguration.load(new InputStreamReader(Emmber.getInstance().getResource(fileName)));
        }
        catch (Exception e) {
            OUT.toConsole(fileName);
            OUT.toConsole("Couldnt load " + fileName + " to yamlConfiguration");
            e.printStackTrace();
            return;
        }
    }

    /**
     * Loads all the messages in yamlConfiguration into messages
     */
    public void loadMessages() {
        for (String str : yamlConfiguration.getKeys(true)) {
            Object value = yamlConfiguration.get(str);
            if (value instanceof MemorySection) {
                continue;
            }
            messages.put(str, value.toString());
        }
    }

    /**
     * @param path - path
     * @return value
     */
    public String getValue(String path) {
        if (!messages.containsKey(path)) {
            try {
                throw new NullPointerException("No value in path: " + path);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                return "None";
            }
        }
        return messages.get(path);
    }


}
