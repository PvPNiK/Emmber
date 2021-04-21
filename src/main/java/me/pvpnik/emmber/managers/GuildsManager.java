package me.pvpnik.emmber.managers;

import me.pvpnik.emmber.Emmber;
import me.pvpnik.emmber.core.guild.Guild;
import me.pvpnik.emmber.utils.HebrewUtil;
import me.pvpnik.emmber.utils.OUT;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.*;

public class GuildsManager {

    private HashMap<String, Guild> guilds;

    public GuildsManager() {
        guilds = new HashMap<>();
    }

    public void addGuild(@Nonnull Guild guild) {
        if (!exist(guild.getName())) {
            guilds.put(guild.getName(), guild);
            Emmber.getInstance().sqlManager.guilds.add(guild.getName(), guild.getLeader());
        }
    }

    public void deleteGuild(String name) {
        if (exist(name)) {
            Guild guild = guilds.remove(name);
            for (UUID uuid : new HashSet<>(guild.getMembersList())) {
                guild.removeMember(uuid);
            }
            Emmber.getInstance().sqlManager.guilds.delete(name);
        }
    }

    public boolean exist(String name) {
        if (guilds.containsKey(name)) {
            return true;
        }
        if (Emmber.getInstance().sqlManager.guilds.exist(name)) {
            guilds.put(name, Emmber.getInstance().sqlManager.guilds.getGuild(name));
            loadMembers(guilds.get(name));
            return true;
        }
        return false;
    }

    public void loadMembers(@Nonnull Guild guild) {
        List<UUID> members = Emmber.getInstance().sqlManager.guildMembers.get(guild.getName());
        members.forEach(uuid -> guild.addMember(uuid));
    }

    @Nullable
    public Guild getGuild(String name) {
        if (!exist(name)) {
            return null;
        }
        return guilds.get(name);
    }

    @Nullable
    public Guild getGuild(UUID uuid) {
        String guildName = Emmber.getInstance().sqlManager.guildMembers.getGuildName(uuid); // member check
        if (guildName == null) {
            guildName = Emmber.getInstance().sqlManager.guilds.getGuildName(uuid); // leader check
            if (guildName == null) {
                return null;
            }
        }
        exist(guildName);
        return guilds.get(guildName);
    }

    public Optional<String> isValidGuildName(String name) {
        if (exist(name)) {
            return Optional.of(ChatColor.GREEN + HebrewUtil.reverseHebrew("קיים כבר גילד בשם הזה"));
        }
        if (name.contains("'") || name.contains("`") || name.contains(".") || name.contains(":")) {
            return Optional.of(ChatColor.GREEN + HebrewUtil.reverseHebrew("השם אינו יכול להכיל ' או ` או . או :"));
        }
        if (name.length() < 3 || name.length() > 10) {
            return Optional.of(ChatColor.GREEN + HebrewUtil.reverseHebrew("הקלד את שם הברית בין 3 ל 10 תווים"));
        }
        return Optional.empty();
    }

}
