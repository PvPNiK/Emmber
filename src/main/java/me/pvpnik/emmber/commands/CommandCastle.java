package me.pvpnik.emmber.commands;

import me.pvpnik.emmber.Emmber;
import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.command.defaults.BukkitCommand;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Arrays;

public class CommandCastle extends BukkitCommand {

    public CommandCastle() {
        super("castle");
        this.description = "Teleports the player back to the castle";
        this.usageMessage = "/castle";
        this.setAliases(Arrays.asList("spawn"));
    }

    @Override
    public boolean execute(CommandSender commandSender, String s, String[] strings) {
        if (commandSender instanceof Player) {
            Player player = (Player) commandSender;
            new BukkitRunnable() {
                @Override
                public void run() {
                    player.teleport(Emmber.getInstance().getSpawnLocation());
                }
            }.runTaskLater(Emmber.getInstance(), 1);
            return true;
        }
        return false;
    }
}
