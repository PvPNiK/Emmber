package me.pvpnik.emmber.core.scoreboard;

import me.pvpnik.emmber.Emmber;
import me.pvpnik.emmber.api.entity.EntityStats;
import me.pvpnik.emmber.api.files.Messages;
import me.pvpnik.emmber.api.jobs.EmmberJob;
import me.pvpnik.emmber.api.scoreboard.EmmberScoreboard;
import org.bukkit.ChatColor;

import javax.annotation.Nonnull;

public class StatsScoreboard extends EmmberScoreboard {

    private EmmberJob player;

    public StatsScoreboard(@Nonnull EmmberJob player) {
        super(EmmberScoreboards.STATS.name(),ChatColor.DARK_PURPLE + Emmber.getInstance().localFileManager.messages.getMessage("scoreboard.castleofemmber"));
        this.player = player;
        Messages messages = Emmber.getInstance().localFileManager.messages;
        add(org.bukkit.ChatColor.YELLOW + "!" + player.getPlayer().getName() + " " + messages.getMessage("scoreboard.welcome"));
        blankLine();
        addTeam(ChatColor.DARK_AQUA + messages.getMessage("player.level"), 7);
        addTeam(ChatColor.DARK_AQUA + messages.getMessage("item.lore.dmg"), 6);
        addTeam(ChatColor.DARK_AQUA + messages.getMessage("item.lore.armor"), 5);
        addTeam(ChatColor.DARK_AQUA + messages.getMessage("item.lore.hp"), 4);
        add(ChatColor.DARK_AQUA + messages.getMessage("scoreboard.website"));
        blankLine();
        add(ChatColor.GOLD + messages.getMessage("scoreboard.shop.part1"));
        add(ChatColor.GOLD + messages.getMessage("scoreboard.shop.part2"));
    }

    @Override
    public void update() {
        String str = ChatColor.DARK_GRAY + "(" + ChatColor.RESET + player.getLevelProgress() + "%" + ChatColor.DARK_GRAY + ")" + ChatColor.RESET + player.getLevel() + " ";
        getTeam(7).get().setPrefix(str.substring(0, str.length() > 16 ? 15 : str.length()));
        EntityStats stats = Emmber.getInstance().entityStatsManager.get(player.getPlayer());
        getTeam(6).get().setPrefix(ChatColor.RED + "" + stats.getDmg() + ChatColor.DARK_RED + " ");
        getTeam(5).get().setPrefix(ChatColor.YELLOW + "" + stats.getArmor() + ChatColor.DARK_RED + " ");
        getTeam(4).get().setPrefix(ChatColor.GREEN + "" + ((int) stats.getMaxHp()) + ChatColor.DARK_RED + " ");
    }

    @Override
    public void build() {
        super.build();
        update();
    }

    @Override
    public void reset() {
        super.reset();
        player = null;
    }
}
