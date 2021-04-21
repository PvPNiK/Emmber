package me.pvpnik.emmber.commands;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.command.defaults.BukkitCommand;
import org.bukkit.entity.Player;

import javax.annotation.Nullable;
import java.util.UUID;

public class CommandPM extends BukkitCommand {

    private static BiMap<UUID, UUID> last = HashBiMap.create();
    @Nullable
    public static UUID getLast(UUID uuid) {
        UUID uuid1 = last.get(uuid);
        if (uuid1 == null) {
            uuid1 = last.inverse().get(uuid);
        }
        return uuid1;
    }

    public CommandPM() {
        super("pm");
        this.description = "Sends private message to a player";
        this.usageMessage = "/pm <name> <message>";
    }

    @Override
    public boolean execute(CommandSender commandSender, String s, String[] args) {
        if (args.length < 3) {
            return false;
        }

        String targetName = args[0];
        Player target = Bukkit.getPlayer(targetName);
        if (target == null) {
            commandSender.sendMessage("No player by the name " + targetName + " found");
            return false;
        }

        String msg = "";
        for (int i = 1 ; i < args.length ; i++) {
            msg += msg + " " + args[i];
        }

        target.sendMessage(commandSender.getName() + " > " + msg);
        if (commandSender instanceof Player) {
            last.forcePut(((Player) commandSender).getUniqueId(), target.getUniqueId());
        }
        return true;
    }

}
