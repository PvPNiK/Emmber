package me.pvpnik.emmber.managers;

import me.pvpnik.emmber.commands.*;
import me.pvpnik.emmber.commands.hero.CommandPay;
import me.pvpnik.emmber.commands.hero.CommandTph;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_12_R1.CraftServer;

public class CommandManager {

    public CommandManager() {
        ((CraftServer) Bukkit.getServer()).getCommandMap().register("castle", new CommandCastle());
        ((CraftServer) Bukkit.getServer()).getCommandMap().register("checkpoint", new CommandCheckPoint());
        ((CraftServer) Bukkit.getServer()).getCommandMap().register("friends", new CommandFriends());
        ((CraftServer) Bukkit.getServer()).getCommandMap().register("guild", new CommandGuild());
        ((CraftServer) Bukkit.getServer()).getCommandMap().register("pm", new CommandPM());
        ((CraftServer) Bukkit.getServer()).getCommandMap().register("r", new CommandR());
        ((CraftServer) Bukkit.getServer()).getCommandMap().register("shield", new CommandShield());
        ((CraftServer) Bukkit.getServer()).getCommandMap().register("shop", new CommandShop());
        ((CraftServer) Bukkit.getServer()).getCommandMap().register("pay", new CommandPay());
        ((CraftServer) Bukkit.getServer()).getCommandMap().register("tph", new CommandTph());
        ((CraftServer) Bukkit.getServer()).getCommandMap().register("transfer", new CommandTransfer());
        ((CraftServer) Bukkit.getServer()).getCommandMap().register("star", new CommandStar());
        ((CraftServer) Bukkit.getServer()).getCommandMap().register("esc", new CommandSC());
    }

}
