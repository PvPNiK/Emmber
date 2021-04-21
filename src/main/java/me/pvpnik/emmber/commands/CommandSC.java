package me.pvpnik.emmber.commands;

import me.mattstudios.mfgui.gui.components.ItemBuilder;
import me.mattstudios.mfgui.gui.guis.Gui;
import me.pvpnik.emmber.Emmber;
import me.pvpnik.emmber.api.jobs.EmmberJob;
import me.pvpnik.emmber.core.scoreboard.EmmberScoreboards;
import me.pvpnik.emmber.core.scoreboard.MainScoreboard;
import me.pvpnik.emmber.core.scoreboard.StatsScoreboard;
import me.pvpnik.emmber.managers.ScoreboardManager;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.command.defaults.BukkitCommand;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;

public class CommandSC extends BukkitCommand {

    public CommandSC() {
        super("esc");
        this.description = "change scoreboard";
        this.usageMessage = "/esc";
        //setPermission("emmber.sc");
    }

    @Override
    public boolean execute(CommandSender commandSender, String s, String[] strings) {
        commandSender.sendMessage("gdfgdfgdfvdf");
        if (!(commandSender instanceof Player)) {
            return false;
        }

        ScoreboardManager scoreboardManager = Emmber.getInstance().emmberCoreManager.scoreboardManager;
        EmmberJob emmberJob = Emmber.getInstance().playerManager.get(((Player) commandSender).getUniqueId());

        Gui gui = new Gui(3, "Scoreboards");

        gui.setDefaultClickAction(event -> event.setCancelled(true));

        gui.setItem(2, 3, ItemBuilder.from(Material.YELLOW_FLOWER).setName(ChatColor.GOLD + "Main Scoreboard").addItemFlags(ItemFlag.values()).asGuiItem(event -> {
            event.getWhoClicked().closeInventory();
            if (scoreboardManager.getPlayerScoreboardId((Player) event.getWhoClicked()).equals(EmmberScoreboards.MAIN)) {
                return;
            }

            scoreboardManager.setScoreboard((Player) event.getWhoClicked(), new MainScoreboard(emmberJob));
        }));

        gui.setItem(2, 7, ItemBuilder.from(Material.RED_MUSHROOM).setName(ChatColor.RED + "Stats Scoreboard").addItemFlags(ItemFlag.values()).asGuiItem(event -> {
            event.getWhoClicked().closeInventory();
            if (scoreboardManager.getPlayerScoreboardId((Player) event.getWhoClicked()).equals(EmmberScoreboards.STATS)) {
                return;
            }

            scoreboardManager.setScoreboard((Player) event.getWhoClicked(), new StatsScoreboard(emmberJob));
        }));

        gui.open((Player) commandSender);

        return true;
    }
}
