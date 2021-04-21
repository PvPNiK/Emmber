package me.pvpnik.emmber;

import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import com.gmail.filoghost.holographicdisplays.api.HologramsAPI;
import com.mewin.WGRegionEvents.WGRegionEventsListener;
import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import me.arcaniax.hdb.api.HeadDatabaseAPI;
import me.pvpnik.emmber.api.database.DataSource;
import me.pvpnik.emmber.api.entity.CustomEntities;
import me.pvpnik.emmber.api.events.armor.ArmorListener;
import me.pvpnik.emmber.core.buyer.Buyer;
import me.pvpnik.emmber.core.buyer.BuyerListener;
import me.pvpnik.emmber.core.shops.npcs.ShopNPCListener;
import me.pvpnik.emmber.core.song.SongManager;
import me.pvpnik.emmber.listeners.CalculateDamage;
import me.pvpnik.emmber.listeners.EmmberWorldListener;
import me.pvpnik.emmber.listeners.PlayerListener;
import me.pvpnik.emmber.managers.*;
import me.pvpnik.emmber.utils.OUT;
import net.luckperms.api.LuckPerms;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.*;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import java.sql.SQLException;
import java.util.Optional;
import java.util.function.Consumer;

public class Emmber extends JavaPlugin {

    private static Emmber INSTANCE;
    public static Emmber getInstance() {
        return INSTANCE;
    }

    /**
     * Main Runnable, Do not create new Runnable!!!
     * Instead creating new action {@link ServerClock#addAction(String, int, Consumer)}`
     */
    public ServerClock serverClock;
    /**
     * Holds all local files instances
     */
    public LocalFileManager localFileManager;
    /**
     * Holds all sql instances
     */
    public EntityManager entityManager;
    /**
     * Entity's stats, holds his current hp dmg & armor.
     */
    public EntityStatsManager entityStatsManager;
    /**
     * Player manager
     */
    public EmmberJobsManager playerManager;

    public EmmberCoreManager emmberCoreManager;
    public SpawnerManager spawnerManager;
    public SQLManager sqlManager;
    public CommandManager commandManager;
    public SongManager songManager;
    public PortalsManager portalsManager;

    /**
     * Emmber.getInstance().holographicDisplays.get() > return True if "HolographicDisplays" Plugin is loaded.
     */
    public Optional<Boolean> holographicDisplays = Optional.of(false);
    public Optional<Economy> economy = Optional.ofNullable(null);
    public Optional<LuckPerms> luckPerms = Optional.ofNullable(null);
    public Optional<WorldGuardPlugin> worldGuard = Optional.ofNullable(null);
    public Optional<HeadDatabaseAPI> headDatabaseAPI = Optional.ofNullable(null);
    public Optional<ProtocolManager> protocolManager = Optional.ofNullable(null);

    @Override
    public void onEnable() {
        INSTANCE = this;
        long startTimeStamp = System.currentTimeMillis();
        OUT.toConsole("Starting Up Emmber!, timeStamp: " + startTimeStamp);

        if (!setUpProtocolLib()) {
            OUT.toConsole(ChatColor.RED + "Could not find ProtocolLib!");
        }

        if (!setupLuckPerms()) {
            OUT.toConsole(ChatColor.RED + "Could not find LuckPerms!");
        }

        if (!setupEconomy()) {
            OUT.toConsole(ChatColor.RED + "Could not find Economy Provider, Please install Vault & Economy Plugin");
        }

        if (!setUpHeadDatabase()) {
            OUT.toConsole(ChatColor.RED + "Could not fine HeadDatabase Plugin!");
        }

        holographicDisplays = Optional.ofNullable(Bukkit.getPluginManager().isPluginEnabled("HolographicDisplays"));

        serverClock = new ServerClock(this);
        localFileManager = new LocalFileManager(this);
        sqlManager = new SQLManager(this);
        entityManager = new EntityManager();
        entityStatsManager = new EntityStatsManager();
        spawnerManager = new SpawnerManager(this);
        emmberCoreManager = new EmmberCoreManager(this);
        playerManager = new EmmberJobsManager();
        commandManager = new CommandManager();
        songManager = new SongManager();
        portalsManager = new PortalsManager(this);

        //Bukkit.getPluginManager().registerEvents(new Note(), this);
        Bukkit.getPluginManager().registerEvents(new CalculateDamage(), this);
        Bukkit.getPluginManager().registerEvents(new PlayerListener(), this);
        Bukkit.getPluginManager().registerEvents(new EmmberWorldListener(), this);
        Bukkit.getPluginManager().registerEvents(new ShopNPCListener(), this);
        Bukkit.getPluginManager().registerEvents(new BuyerListener(), this);
        getServer().getPluginManager().registerEvents(new ArmorListener(localFileManager.armorBlocked.getBlocked()), this);
        registerWorldGuardRegionEvents();

        CustomEntities.registerEntities();

        long finishTimeStamp = System.currentTimeMillis();
        OUT.toConsole("Emmber has been setted up!, timeStamp: " + finishTimeStamp + ", time lapsed: " + (finishTimeStamp-startTimeStamp) + "ms");
    }

    @Override
    public void onDisable() {
        entityManager.remove();
        CustomEntities.unregisterEntities();
        DataSource.closeConnection();
    }

    /**
     * Only for Test Server!
     * @return Main server's world
     */
    //private World world;
    public World getWorld() {
        /*world = Bukkit.getWorld("Ember");
        if (world == null) {
            world = new WorldCreator("Ember").createWorld();
        }*/
        return Bukkit.getWorlds().get(0);
    }

    public Location getSpawnLocation() {
        return new Location(getWorld(), 0.5, 83.1, 40.5, 180, 0);
    }

    private void registerWorldGuardRegionEvents() {
        Plugin plugin = getServer().getPluginManager().getPlugin("WorldGuard");
        if (plugin == null || !(plugin instanceof WorldGuardPlugin)) {
            OUT.toConsole("Could not find World Guard plugin");
            worldGuard = Optional.ofNullable(null);
            return;
        }
        worldGuard = Optional.of((WorldGuardPlugin) plugin);
        getServer().getPluginManager().registerEvents(new WGRegionEventsListener(this, worldGuard.get()), plugin);
    }

    private boolean setupEconomy() {
        try {
            RegisteredServiceProvider<Economy> economyProvider = getServer().getServicesManager().getRegistration(Economy.class);
            economy = Optional.ofNullable(economyProvider.getProvider());
        } catch (Exception e) {
            economy = Optional.ofNullable(null);
        }
        return luckPerms.isPresent();
    }

    private boolean setupLuckPerms() {
        try {
            RegisteredServiceProvider<LuckPerms> provider = Bukkit.getServicesManager().getRegistration(LuckPerms.class);
            luckPerms = Optional.ofNullable(provider.getProvider());
        } catch (NoClassDefFoundError e) {
            luckPerms = Optional.ofNullable(null);
        }
        return luckPerms.isPresent();
    }

    private boolean setUpHeadDatabase() {
        Plugin plugin = getServer().getPluginManager().getPlugin("HeadDatabase");
        if (plugin == null) {
            headDatabaseAPI = Optional.ofNullable(null);
            return false;
        }
        headDatabaseAPI = Optional.of(new HeadDatabaseAPI());
        return true;
    }

    private boolean setUpProtocolLib() {
        Plugin plugin = getServer().getPluginManager().getPlugin("ProtocolLib");
        if (plugin == null) {
            protocolManager = Optional.ofNullable(null);
            return false;
        }
        protocolManager = Optional.of(ProtocolLibrary.getProtocolManager());
        return true;
    }

}
