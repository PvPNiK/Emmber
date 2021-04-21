package me.pvpnik.emmber.commands;

import me.pvpnik.emmber.Emmber;
import me.pvpnik.emmber.api.jobs.EmmberJob;
import org.bukkit.command.CommandSender;
import org.bukkit.command.defaults.BukkitCommand;
import org.bukkit.entity.Player;

import java.util.Arrays;

public class CommandCheckPoint extends BukkitCommand {

    public CommandCheckPoint() {
        super("checkpoint");
        this.description = "Teleports the player back to their saved check point";
        this.usageMessage = "/cp";
        this.setAliases(Arrays.asList("cp"));
    }

    @Override
    public boolean execute(CommandSender commandSender, String s, String[] strings) {
        if (!(commandSender instanceof Player)) {
            return false;
        }
        Player player = (Player) commandSender;
        EmmberJob emmberJob = Emmber.getInstance().playerManager.get(player.getUniqueId());
        emmberJob.teleportToCheckPoint();
        return true;
    }

}
