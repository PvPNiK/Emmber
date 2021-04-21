package me.pvpnik.emmber;

import com.sk89q.worldedit.EditSession;
import com.sk89q.worldedit.Vector;
import com.sk89q.worldedit.WorldEdit;
import com.sk89q.worldedit.bukkit.BukkitWorld;
import com.sk89q.worldedit.bukkit.adapter.BukkitImplAdapter;
import com.sk89q.worldedit.extent.clipboard.BlockArrayClipboard;
import com.sk89q.worldedit.function.operation.ForwardExtentCopy;
import com.sk89q.worldedit.regions.CuboidRegion;
import me.pvpnik.emmber.api.holograms.temporary.HologramDamage;
import me.pvpnik.emmber.core.friends.FriendsInventory;
import me.pvpnik.emmber.core.guild.GuildMenuInventory;
import me.pvpnik.emmber.core.shield.ShieldInventory;
import me.pvpnik.emmber.items.startingItems.StartingSword;
import me.pvpnik.emmber.mobs.regular.skeleton.WeakSkeleton;
import me.pvpnik.emmber.utils.OUT;
import me.pvpnik.emmber.utils.anvilGUI.AnvilGUI;
import org.bukkit.*;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Note implements Listener {

    /*private ArrayList<Chunk> chunks = new ArrayList<>();

    @EventHandler
    public void onCHunkLoad(ChunkLoadEvent e) {
        if (e.getWorld().getName().equalsIgnoreCase(emmber.getName())) {
            if (chunks.contains(e.getChunk())) {
                return;
            }
            chunks.add(e.getChunk());
            for (int y = 0; y <= 4; y++) {
                for (int x = 0; x < 16; x++) {
                    for (int z = 0; z < 16; z++) {
                        int finalY = y;
                        int finalX = x;
                        int finalZ = z;
                        new BukkitRunnable() {
                            @Override
                            public void run() {
                                e.getChunk().getBlock(finalX, finalY, finalZ).setType(Material.AIR);
                            }
                        }.runTaskLater(Emmber.getInstance(), 2);
                    }
                }
            }
        }
    }*/

    public Note() {
        //loadWorld();
    }

    /*private void loadWorld() {
        if (emmber == null) {
            emmber = Bukkit.getWorld("emmberNew");
            if (emmber == null) {
                emmber = new WorldCreator("emmberNew").createWorld();
            }
        }
    }*/

    /*private Location loc1 = null;
    private Location loc2 = null;
    private Cuboid cuboid = null;
    private World emmber = null;*/

    /*private Location toEmmberLoc(Location location) {
        return new Location(emmber, location.getX(), location.getY(), location.getZ());
    }*/

   /* private String[][] replace = {
            { "/", "q" }, { "\'", "w" }, { "ק", "e" }, { "ר", "r" }, { "א", "t" }, { "ט", "y" }, { "ו", "u" }, { "ן", "i" }, { "ם", "o" }, { "פ", "p" },
            { "ש", "a" }, { "ד", "s" }, { "ג", "d" }, { "כ", "f" }, { "ע", "g" }, { "י", "h" }, { "ח", "j" }, { "ל", "k" }, { "ך", "l" },
            { "ז", "z" }, { "ס", "x" }, { "ב", "c" }, { "ה", "v" }, { "נ", "b" }, { "מ", "n" }, { "צ", "m" }
    };

    @EventHandler
    public void hebrewCommand(PlayerCommandPreprocessEvent e) {
        if (!isHebrew(e.getMessage()))
            return;

        if (!e.getMessage().contains("/"))
            return;

        String cmdName = e.getMessage();

        if (e.getMessage().contains(" "))
            cmdName = e.getMessage().split(" ")[0];

        if (!isHebrew(cmdName))
            return;

        for (int i = 1; i < cmdName.length(); i++)
            for (int j = 0; j < replace.length; j++)
                if (cmdName.charAt(i) == replace[j][0].charAt(0))
                    cmdName = cmdName.replace(cmdName.charAt(i), replace[j][1].charAt(0));

        e.setCancelled(true);

        String cmd = "";

        if (e.getMessage().length() > 1 + cmdName.length())
            cmd = cmdName + e.getMessage().substring(cmdName.length());
        else
            cmd = cmdName;

        Bukkit.broadcastMessage("cmd: " + cmd);
        e.getPlayer().chat(cmd);
    }*/

    public boolean isHebrew(String str) {
        Pattern p = Pattern.compile("\\p{InHebrew}");
        Matcher m = p.matcher(str);
        return m.find();
    }

    @EventHandler
    public void onc(AsyncPlayerChatEvent e) {
        if (e.getMessage().equalsIgnoreCase("dmg")) {
            new HologramDamage(e.getPlayer(), 2, e.getPlayer().getLocation().add(1,1,0));
        }
        if (e.getMessage().equalsIgnoreCase("worldName")) {
            e.getPlayer().sendMessage("w: " + e.getPlayer().getWorld().getName());
            return;
        }
        /*if (e.getMessage().equalsIgnoreCase("emmber")) { // tp to emmberNew
            loadWorld();
            Location location = e.getPlayer().getLocation();
            new BukkitRunnable() {
                @Override
                public void run() {
                    e.getPlayer().teleport(toEmmberLoc(location));
                }
            }.runTaskLater(Emmber.getInstance(), 1);
        }
        if (e.getMessage().equalsIgnoreCase("world")) { // tp to emmber
            Location location = new Location(Bukkit.getWorlds().get(0), e.getPlayer().getLocation().getX(), e.getPlayer().getLocation().getY(), e.getPlayer().getLocation().getZ());
            new BukkitRunnable() {
                @Override
                public void run() {
                    e.getPlayer().teleport(location);
                }
            }.runTaskLater(Emmber.getInstance(), 1);
        }*/
    }

    @EventHandler
    public void onChat(AsyncPlayerChatEvent e) {
        if (e.getMessage().equalsIgnoreCase("skele")) {
            final Location location = e.getPlayer().getLocation();
            WeakSkeleton weakSkeleton = WeakSkeleton.spawn(location);
            weakSkeleton.setHp(245);
            weakSkeleton.setArmor(23);
            /*final LevelOneSkeleton emmberSkeleton = new LevelOneSkeleton(location);
            new BukkitRunnable() {
                public void run() {
                    ((CraftWorld)location.getWorld()).getHandle().addEntity(emmberSkeleton, CreatureSpawnEvent.SpawnReason.CUSTOM);
                    //Emmber.getInstance().entityManager.add(emmberSkeleton.getUniqueID(), emmberSkeleton);
                }
            }.runTaskLater(Emmber.getInstance(), 1);*/
        }
        if (e.getMessage().equalsIgnoreCase("ss")) {
            StartingSword startingSword = new StartingSword();
            e.getPlayer().getInventory().addItem(startingSword);
        }
        if (e.getMessage().equalsIgnoreCase("anvil")) {
            // Plugin plugin, Player holder,String insert, BiFunction<Player, String, String> biFunction
            new AnvilGUI(Emmber.getInstance(), e.getPlayer(), "", (player, s) -> {
                OUT.toConsole("s: " + s);
                return Emmber.getInstance().localFileManager.messages.getMessage("friends.notonline");
            });
        }
        if (e.getMessage().equalsIgnoreCase("friends")) {
            FriendsInventory.getFriendsMenu(e.getPlayer());
        }
        if (e.getMessage().equalsIgnoreCase("friends test")) {
            Emmber.getInstance().sqlManager.friends.addFriend(e.getPlayer().getUniqueId(), UUID.randomUUID());
        }
        if (e.getMessage().equalsIgnoreCase("friends tt")) {
            Emmber.getInstance().sqlManager.friends.addFriend(e.getPlayer().getUniqueId(), e.getPlayer().getUniqueId());
        }
        if (e.getMessage().equalsIgnoreCase("shield")) {
            e.getPlayer().openInventory(ShieldInventory.getShieldInv(e.getPlayer()));
        }
        if (e.getMessage().equalsIgnoreCase("guild")) {
            GuildMenuInventory.openGuildmenuInventory(e.getPlayer());
        }
    }

    @EventHandler
    public void onSlotChange(PlayerItemHeldEvent event) {
        Player player = event.getPlayer();
        new BukkitRunnable() {
            @Override
            public void run() {
                Emmber.getInstance().entityStatsManager.update(event.getPlayer());
            }
        }.runTaskLater(Emmber.getInstance(), 1);
       /* Emmber.getInstance().serverClock.addAction(new ServerClock.Action(UUID.randomUUID().toString(), 1, o -> {
            Emmber.getInstance().entityStatsManager.update(event.getPlayer().getUniqueId());
        }).setRepeat(false));*/
    }

}