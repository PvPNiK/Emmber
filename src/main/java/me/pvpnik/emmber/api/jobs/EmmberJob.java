package me.pvpnik.emmber.api.jobs;

import com.gmail.filoghost.holographicdisplays.api.Hologram;
import com.gmail.filoghost.holographicdisplays.api.HologramsAPI;
import com.google.common.util.concurrent.AtomicDouble;
import com.xxmicloxx.NoteBlockAPI.NBSDecoder;
import com.xxmicloxx.NoteBlockAPI.RadioSongPlayer;
import com.xxmicloxx.NoteBlockAPI.Song;
import com.xxmicloxx.NoteBlockAPI.SongPlayer;
import me.pvpnik.emmber.Emmber;
import me.pvpnik.emmber.ServerClock;
import me.pvpnik.emmber.core.Checkpoint;
import me.pvpnik.emmber.core.guild.Guild;
import me.pvpnik.emmber.core.jobs.BaseStats;
import me.pvpnik.emmber.core.jobs.PlayerPath;
import me.pvpnik.emmber.core.scoreboard.EmmberScoreboards;
import me.pvpnik.emmber.utils.OUT;
import me.pvpnik.emmber.utils.Title;
import net.luckperms.api.LuckPerms;
import net.luckperms.api.model.user.User;
import net.minecraft.server.v1_12_R1.EnumParticle;
import net.minecraft.server.v1_12_R1.PacketPlayOutWorldParticles;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.craftbukkit.v1_12_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.io.File;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

public class EmmberJob {

    private UUID uuid;
    private BaseStats level;
    private float exp;
    private Checkpoint spawn = Checkpoint.EssverHospital;
    private PlayerPath path = PlayerPath.NONE;
    private int points = 0;
    private int diamonds = 0;
    private int seasonlevel = 0;
    private int crystal = 0;

    public EmmberJob(UUID uuid) {
        this(uuid, BaseStats.one, 0);
    }

    public EmmberJob(UUID uuid, BaseStats level, float exp) {
        this.uuid = uuid;
        this.level = level;
        this.exp = exp;
    }

    // core
    public List<UUID> getFriends() {
        return Emmber.getInstance().sqlManager.friends.getFriends(getUuid());
    }

    public void teleportToCheckPoint() {
        new BukkitRunnable() {
            @Override
            public void run() {
                getOptionalPlayer().ifPresent(player -> player.teleport(spawn.getLocation()));
            }
        }.runTaskLater(Emmber.getInstance(), 1);
    }

    @Nullable
    public Guild getGuild() {
        return Emmber.getInstance().emmberCoreManager.guilds.getGuild(getUuid());
    }
    //

    // vault
    public double getMoney() {
        AtomicDouble money = new AtomicDouble(-1);
        Emmber.getInstance().economy.ifPresent(economy -> {
            money.set(economy.getBalance(getPlayer()));
        });
        return money.get();
    }

    public double setMoney(@Nonnull final double money) {
        Emmber.getInstance().economy.ifPresent(economy -> {
            withdraw(getMoney());
            deposit(money);
        });
        Emmber.getInstance().emmberCoreManager.scoreboardManager.update(EmmberScoreboards.MAIN, getPlayer());
        return getMoney();
    }

    /**
     * Takes money
     */
    public double withdraw(@Nonnull final double amount) {
        Emmber.getInstance().economy.ifPresent(economy -> {
            economy.withdrawPlayer(getPlayer(), amount);
        });
        Emmber.getInstance().emmberCoreManager.scoreboardManager.update(EmmberScoreboards.MAIN, getPlayer());
        return getMoney();
    }

    /**
     * Adds money
     */
    public double deposit(@Nonnull final double amount) {
        Emmber.getInstance().economy.ifPresent(economy -> {
            economy.depositPlayer(getPlayer(), amount);
        });
        Emmber.getInstance().emmberCoreManager.scoreboardManager.update(EmmberScoreboards.MAIN, getPlayer());
        return getMoney();
    }
    //

    // luck Perms
    @Nullable
    public String getPrefix() {
        AtomicReference<String> groupPrefix = new AtomicReference<>("Default");
        if (Emmber.getInstance().luckPerms.isPresent()) {
            LuckPerms luckPerms = Emmber.getInstance().luckPerms.get();
            User user = luckPerms.getUserManager().getUser(getUuid());
            groupPrefix.set(user.getCachedData().getMetaData().getPrefix());
            if (groupPrefix.get() != null) {
                groupPrefix.set(ChatColor.translateAlternateColorCodes('&', groupPrefix.get()));
            }
        }
       /* Emmber.getInstance().luckPerms.ifPresent(luckPerms -> {

        });*/
        return groupPrefix.get();
    }
    //

    public UUID getUuid() {
        return uuid;
    }

    public Player getPlayer() {
        return Bukkit.getPlayer(uuid);
    }

    public Optional<Player> getOptionalPlayer() {
        return Optional.ofNullable(Bukkit.getPlayer(uuid));
    }

    public int getLevel() {
        return level.level;
    }

    public BaseStats getBaseStats() {
        return level;
    }

    public void setLevel(BaseStats level, boolean load) {
        if (!load) {
            if (level.level > this.level.level) {
                /*for (int i = this.level.level; i <= level.level; i++) {
                    LevelReward.getInstance().giveReward(getUniqueId(), i);
                }*/
                if (getPlayer() != null) {
                    Player p = getPlayer();
                    // client
                    PacketPlayOutWorldParticles packet = new PacketPlayOutWorldParticles(EnumParticle.PORTAL, true,
                            (float) p.getLocation().getX(), (float) p.getLocation().getY(), (float) p.getLocation().getZ(),
                            0, 0, 0, 10, 10000, null);
                    ((CraftPlayer) p).getHandle().playerConnection.sendPacket(packet);
                    Title.sendTitle(getPlayer(), ChatColor.GREEN + Emmber.getInstance().localFileManager.messages.getMessage("player.lvlup"),
                            ChatColor.WHITE + "", 1, 1, 1);
                    // server, all players
                    final Hologram hologram = HologramsAPI.createHologram(Emmber.getInstance(), getPlayer().getLocation().add(0, 1.2, 0));
                    hologram.appendTextLine(ChatColor.GREEN + Emmber.getInstance().localFileManager.messages.getMessage("player.ilvlup") + ChatColor.GRAY + " (" + p.getName() + ")");
                    // append hologram upwards
                    final BukkitTask task = new BukkitRunnable() {

                        @Override
                        public void run() {
                            hologram.teleport(hologram.getLocation().clone().add(0, 0.02, 0));
                        }
                    }.runTaskTimer(Emmber.getInstance(), 1L, 1L);
                    // after 5 seconds delete the hologram
                    Emmber.getInstance().serverClock.addAction(new ServerClock.Action(uuid.toString() + "Lvlup" + level.level, 5, integer -> {
                        task.cancel();
                        if (!hologram.isDeleted()) {
                            hologram.delete();
                        }
                    }).setRepeat(false));
                    // play custom sound for player
                    try {
                        Song s = NBSDecoder.parse(new File(Emmber.getInstance().getDataFolder(), "/music/levelup.nbs"));
                        final SongPlayer sp = new RadioSongPlayer(s);
                        sp.setAutoDestroy(true);
                        sp.setPlaying(true);
                        sp.addPlayer(getPlayer());
                    } catch (Exception e) {
                    }
                }
            } else {
                this.exp = 0;
            }
        }
        this.level = level;

        if (checkEXPForLevel()) {
            return;
        }

        if (getPlayer() != null) {
            getPlayer().setLevel(level.level);
            updateExpBar();
            //CastleOfEssver.bsmanager.updateScoreboard(getPlayer());
        }
        //updateGroup();
    }

    public float getExp() {
        return exp;
    }

    public void addExp(float exp) {
        setExp(getExp() + exp);
    }

    public void setExp(float experience) {
        if (experience <= 0) {
            experience = 0;
        }

        this.exp = experience;

        if (!checkEXPForLevel()) { // not leveled up
            if (getPlayer() != null) {
                updateExpBar();
                Emmber.getInstance().emmberCoreManager.scoreboardManager.update(EmmberScoreboards.MAIN, getPlayer());
                //CastleOfEssver.bsmanager.updateScoreboard(getPlayer());
            }
        }
    }

    private boolean checkEXPForLevel() {
        int exp = level.expForNextLevel;

        if (exp == -1) {
            return false;
        }

        if (this.exp < exp) {
            return false;
        }

        this.exp = this.exp - exp;

        setLevel(BaseStats.getLevel(getLevel() + 1), false);
        return true;
    }

    private void updateExpBar() {
        if (getPlayer() == null) {
            return;
        }

        int expNxtLvl = level.expForNextLevel;
        float experience = this.exp;

        if (expNxtLvl == -1) {
            getPlayer().setExp(0);
            return;
        }

        if (experience / expNxtLvl > 1) { // player's exp higher than {@link BaseStats#expForNextLevel}
            checkEXPForLevel();
        } else {
            getPlayer().setExp(experience / expNxtLvl);
        }
    }

    public int getLevelProgress() {
        int expNxtLvl = level.expForNextLevel;
        float experience = this.exp;

        if (expNxtLvl == -1)
            return 0;

        return (int) (experience / expNxtLvl * 100);
    }

    public Checkpoint getSpawn() {
        return spawn;
    }

    public void setSpawn(Checkpoint spawn) {
        this.spawn = spawn;
    }

    public PlayerPath getPath() {
        return path;
    }

    public void setPath(PlayerPath path) {
        this.path = path;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
        Emmber.getInstance().emmberCoreManager.scoreboardManager.update(EmmberScoreboards.MAIN, getPlayer());
    }

    public int getDiamonds() {
        return diamonds;
    }

    public void setDiamonds(int diamonds) {
        this.diamonds = diamonds;
        Emmber.getInstance().emmberCoreManager.scoreboardManager.update(EmmberScoreboards.MAIN, getPlayer());
    }

    public int getSeasonlevel() {
        return seasonlevel;
    }

    public void setSeasonlevel(int seasonlevel) {
        this.seasonlevel = seasonlevel;
    }

    public int getCrystal() {
        return crystal;
    }

    public void setCrystal(int crystal) {
        this.crystal = crystal;
        Emmber.getInstance().emmberCoreManager.scoreboardManager.update(EmmberScoreboards.MAIN, getPlayer());
    }
}

 /*private void removeGroups(User user) {
        user.data().remove(Node.builder("group.rookie").build());
        user.data().remove(Node.builder("group.Soldier").build());
        user.data().remove(Node.builder("group.Fighter").build());
        user.data().remove(Node.builder("group.Scout").build());
        user.data().remove(Node.builder("group.Hunter").build());
        user.data().remove(Node.builder("group.Slayer").build());
        user.data().remove(Node.builder("group.Senior").build());
    }

    public void updateGroup() {
        if (getPermissionUser() == null)
            return;
        //"CastleOfEssver.rank.deny"
        if (getPermissionUser().getNodes().stream().anyMatch(n -> {
            return n.getKey().equals("CastleOfEssver.rank.deny");
        }) || getOfflinePlayer().isOp())
            return;

//		String group = getPermissionUser().getParentIdentifiers().get(0);
        User user = CastleOfEssver.lpAPI.getUserManager().getUser(getName());

        removeGroups(user);

        if (level == 1) {
//			if (!group.equalsIgnoreCase("Default")) {
            user.data().add(Node.builder("group.Default").build());
//			}
        } else if (level < 8) {
//			if (!group.equalsIgnoreCase("Rookie")) {
            user.data().add(Node.builder("group.Rookie").build());
//			}
        } else if (level < 15) {
//			if (!group.equalsIgnoreCase("Soldier")) {
            user.data().add(Node.builder("group.Soldier").build());
//			}
        } else if (level < 22) {
//			if (!group.equalsIgnoreCase("Fighter")) {
            user.data().add(Node.builder("group.Fighter").build());
//			}
        } else if (level < 28) {
//			if (!group.equalsIgnoreCase("Scout")) {
            user.data().add(Node.builder("group.Scout").build());
//			}
        } else if (level < 35) {
//			if (!group.equalsIgnoreCase("Hunter")) {
            user.data().add(Node.builder("group.Hunter").build());
//			}
        } else if (level < 50) {
//			if (!group.equalsIgnoreCase("Slayer")) {
            user.data().add(Node.builder("group.Slayer").build());
//			}
        } else {
//			if (!group.equalsIgnoreCase("Senior")) {
            user.data().add(Node.builder("group.Senior").build());
//			}
        }
    }*/