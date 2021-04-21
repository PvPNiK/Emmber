package me.pvpnik.emmber.managers;

import me.pvpnik.emmber.Emmber;
import me.pvpnik.emmber.core.NightMultiplier;
import me.pvpnik.emmber.core.bank.BankListener;
import me.pvpnik.emmber.core.guild.GuildInvites;
import me.pvpnik.emmber.core.stars.StarUpgrade;
import me.pvpnik.emmber.core.stars.StarUpgradeAnimation;
import me.pvpnik.emmber.utils.EmmberChatClickCallBack;
import org.bukkit.Bukkit;

/**
 * All classes which are needed for Emmber to run as Emmber server
 */
public class EmmberCoreManager {

    public NightMultiplier nightMultiplier = new NightMultiplier();
    public RegionBossBarManager regionBossBar = new RegionBossBarManager();
    public GuildsManager guilds = new GuildsManager();
    public ScoreboardManager scoreboardManager = new ScoreboardManager();
    public PrizepointsManager prizepoints = new PrizepointsManager();

    public EmmberChatClickCallBack callBack;

    public GuildInvites guildInvites;

    public EmmberCoreManager(Emmber emmber) {
        Bukkit.getPluginManager().registerEvents(new BankListener(), emmber);
        Bukkit.getPluginManager().registerEvents(new StarUpgrade(), emmber);
        Bukkit.getPluginManager().registerEvents(new StarUpgradeAnimation(), emmber);

        callBack = new EmmberChatClickCallBack(emmber);
        guildInvites = new GuildInvites();
    }

}
