package me.pvpnik.emmber.core.scoreboard;

import me.pvpnik.emmber.Emmber;
import me.pvpnik.emmber.api.files.Messages;
import me.pvpnik.emmber.api.jobs.EmmberJob;
import me.pvpnik.emmber.api.scoreboard.EmmberScoreboard;
import net.luckperms.api.model.group.Group;
import net.luckperms.api.model.user.User;
import net.luckperms.api.node.NodeType;
import net.luckperms.api.node.types.PrefixNode;
import org.bukkit.ChatColor;

import javax.annotation.Nonnull;
import java.util.Collection;
import java.util.concurrent.atomic.AtomicReference;

public class MainScoreboard extends EmmberScoreboard {

    private EmmberJob player;

    public MainScoreboard(@Nonnull EmmberJob player) {
        super(EmmberScoreboards.MAIN.name(), ChatColor.DARK_PURPLE + Emmber.getInstance().localFileManager.messages.getMessage("scoreboard.castleofemmber"));
        this.player = player;
        Messages messages = Emmber.getInstance().localFileManager.messages;
        add(org.bukkit.ChatColor.YELLOW + "!" + player.getPlayer().getName() + " " + messages.getMessage("scoreboard.welcome"));
        blankLine();
        addTeam(ChatColor.DARK_AQUA + messages.getMessage("player.level"), 10);
        addTeam(ChatColor.DARK_AQUA + ":" + messages.getMessage("items.bank.credits"), 9);
        addTeam(ChatColor.DARK_AQUA + ":" + messages.getMessage("items.bank.gold"), 8);
        addTeam(ChatColor.DARK_AQUA + ":" + messages.getMessage("items.bank.crystal"), 7);
        addTeam(ChatColor.DARK_AQUA + ":" + messages.getMessage("items.bank.diamond"), 6);
        addTeam(ChatColor.DARK_AQUA + messages.getMessage("guild.guild"), 5);
        addTeam(ChatColor.DARK_AQUA + messages.getMessage("player.rank"), 4);
        blankLine();
        add(ChatColor.DARK_AQUA + messages.getMessage("scoreboard.website"));
        add(ChatColor.GOLD + messages.getMessage("scoreboard.shop.part1"));
        add(ChatColor.GOLD + messages.getMessage("scoreboard.shop.part2"));
    }

    @Override
    public void update() {
        String str = ChatColor.DARK_GRAY + "(" + ChatColor.RESET + player.getLevelProgress() + "%" + ChatColor.DARK_GRAY + ")" + ChatColor.RESET + player.getLevel() + " ";
        getTeam(10).get().setPrefix(str.substring(0, str.length() > 16 ? 15 : str.length()));
        getTeam(9).get().setPrefix(ChatColor.RED + "" + player.getPoints() + ChatColor.DARK_RED + " ");
        getTeam(8).get().setPrefix(ChatColor.YELLOW + "" + player.getMoney() + ChatColor.DARK_RED + " ");
        getTeam(7).get().setPrefix(ChatColor.GREEN + "" + player.getCrystal() + ChatColor.DARK_RED + " ");
        getTeam(6).get().setPrefix(ChatColor.AQUA + "" + player.getDiamonds() + ChatColor.DARK_RED + " ");
        getTeam(5).get().setPrefix((player.getGuild() == null ? Emmber.getInstance().localFileManager.messages.getMessage("guild.noguild") :
                player.getGuild().getName()) + " ");
        String prefix = player.getPrefix();
        if (prefix != null && prefix.length() > 15) {
            prefix = prefix.substring(0, 15);
        }
        getTeam(4).get().setPrefix(prefix + " ");
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
