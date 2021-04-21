package me.pvpnik.emmber.api.files;

import java.util.ArrayList;
import java.util.List;

public class ArmorBlockedFile extends LocalFile {

    private List<String> blocked = new ArrayList<>();

    public ArmorBlockedFile() {
        super("armorBlocked.yml");
    }

    @Override
    public void reloadConfig() {
        super.reloadConfig();
        blocked = yamlConfiguration.getStringList("blocked");
    }

    @Override
    public void loadMessages() {}

    public List<String> getBlocked() {
        return blocked;
    }
}
