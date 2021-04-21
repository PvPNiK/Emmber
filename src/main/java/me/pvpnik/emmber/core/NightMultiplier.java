package me.pvpnik.emmber.core;

import com.google.common.util.concurrent.AtomicDouble;
import me.pvpnik.emmber.Emmber;
import me.pvpnik.emmber.ServerClock;
import me.pvpnik.emmber.utils.Title;
import me.pvpnik.emmber.utils.WeightedCollection;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class NightMultiplier {

    private static AtomicDouble nightMultiplier = new AtomicDouble(Night.REGULAR.multiplier);
    public static double getCurrentNightMultiplier() {
        return nightMultiplier.get();
    }

    private WeightedCollection<Double> wc = new WeightedCollection<Double>();

    public NightMultiplier() {
        for (Night night : Night.values()) {
            wc.add(night.chance, night.multiplier);
        }

        new BukkitRunnable() {
            @Override
            public void run() {
                long time = Emmber.getInstance().getWorld().getTime();
                if (time >= 14000) { // night
                    if (time == 14002) {
                        playHorn(getNightByMulti(nightMultiplier.get()));
                    }
                    if (nightMultiplier.get() == 1) {
                        nightMultiplier.set(wc.next());
                        Emmber.getInstance().entityManager.reloadEntityStats();
                    }
                } else { // day
                    if (nightMultiplier.get() != 1) {
                        nightMultiplier.set(1);
                        Emmber.getInstance().entityManager.reloadEntityStats();
                    }
                }
                if (time == 10) {
                    for (Player p : Bukkit.getOnlinePlayers()) {
                        String title = ChatColor.GREEN + Emmber.getInstance().localFileManager.messages.getMessage("world.morning");
                        String subtitle = Emmber.getInstance().localFileManager.messages.getMessage("world.mobReturnToDefault");
                        Title.sendTitle(p, title, subtitle, 3, 8, 3);
                    }
                }
            }
        }.runTaskTimer(Emmber.getInstance(), 1, 1);
    }

    private void playHorn(final Night night) {
        Emmber.getInstance().serverClock.addAction(new ServerClock.Action("NightMultiplerPlayHorn", 5, cycle -> {
            if (Emmber.getInstance().getWorld().getTime() > 14000) {
                if (cycle == 1) {
                    showMessage(night);
                }
                Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "playsound minecraft:horn master @a 244 119 1215 25");
                Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "playsound minecraft:horn master @a 120 107 1410 18");
                Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "playsound minecraft:horn master @a 446 112 1348 10");
            }
        }).setCycle(night.horn));
    }

    private void showMessage(final Night night) {
        Emmber.getInstance().serverClock.addAction(new ServerClock.Action("NightMultiplierShowMessage", 6, integer -> {
            if (Emmber.getInstance().getWorld().getTime() > 14000) {
                for (Player p : Bukkit.getOnlinePlayers()) {
                    Title.sendTitle(p, night.name, "X" + night.multiplier + " " + Emmber.getInstance().localFileManager.messages.getMessage("world.difficultyLevel"), 3, 8, 3);
                }
            }
        }).setRepeat(false));
    }

    private Night getNightByMulti(double multi) {
        for (Night night : Night.values()) {
            if (night.multiplier == multi) {
                return night;
            }
        }
        return null;
    }

    public enum Night {
        REGULAR(850, 1.5, 1, ChatColor.RESET + Emmber.getInstance().localFileManager.messages.getMessage("world.night.regular")),
        DARK(100, 2.0, 2, ChatColor.GRAY + Emmber.getInstance().localFileManager.messages.getMessage("world.night.dark")),
        EXTREME(39, 3.0, 3, ChatColor.RED + Emmber.getInstance().localFileManager.messages.getMessage("world.night.extreme")),
        DEATH(9, 5.0, 4, ChatColor.DARK_RED + Emmber.getInstance().localFileManager.messages.getMessage("world.night.death")),
        NETHER(2, 8.0, 5, ChatColor.DARK_PURPLE + Emmber.getInstance().localFileManager.messages.getMessage("world.night.nether"));

        /**
         * The chances of this night to be choosen
         */
        private int chance;
        /**
         * Night's multiplier
         */
        private double multiplier;
        /**
         * Number of horns to make
         */
        private int horn;
        /**
         * Night's name to display for players
         */
        private String name;

        Night(int chance, double multiplier, int horn, String name) {
            this.chance = chance;
            this.multiplier = multiplier;
            this.horn = horn;
            this.name = name;
        }

        @Override
        public String toString() {
            return "Night{" +
                    "chance=" + chance +
                    ", multiplier=" + multiplier +
                    ", horn=" + horn +
                    ", name='" + name + '\'' +
                    '}';
        }
    }

}
