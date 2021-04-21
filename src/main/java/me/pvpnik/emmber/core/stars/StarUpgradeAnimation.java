package me.pvpnik.emmber.core.stars;

import me.pvpnik.emmber.Emmber;
import me.pvpnik.emmber.utils.ItemUtils;
import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.FireworkEffect.Type;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;
import java.util.UUID;

public class StarUpgradeAnimation implements Listener {

    private static class Glasses {

        public static HashMap<Integer, HashMap<Integer, ItemStack>> getFirstAnimation() {
            HashMap<Integer, HashMap<Integer, ItemStack>> glasses = new HashMap<Integer, HashMap<Integer, ItemStack>>();
            HashMap<Integer, ItemStack> glass = null;

            for (int state = 17; state > 0; state--) {
                glass = new HashMap<Integer, ItemStack>();
                if (state == 16) {
                    glass.put(35, glass_plane(0));
                    glass.put(44, glass_plane(0));
                    glass.put(53, glass_plane(0));
                } else if (state <= 15 && state >= 9) {
                    glass.put(state + 19, glass_plane(0));
                    glass.put(state + 37, glass_plane(0));
                } else if (state == 8) {
                    glass.put(27, glass_plane(0));
                    glass.put(36, glass_plane(0));
                    glass.put(45, glass_plane(0));
                } else if (state <= 7 && state >= 1) {
                    glass.put((-1 * state) + 35, glass_plane(0));
                    glass.put((-1 * state) + 53, glass_plane(0));
                } else if (state == 0) {
                    glass.put(35, glass_plane(0));
                    glass.put(44, glass_plane(0));
                    glass.put(53, glass_plane(0));
                }
                if (!glass.isEmpty())
                    glasses.put(state, glass);
            }

            return glasses;
        }

        public static HashMap<Integer, HashMap<Integer, ItemStack>> getSecondAnimation() {
            HashMap<Integer, HashMap<Integer, ItemStack>> glasses = new HashMap<Integer, HashMap<Integer, ItemStack>>();
            HashMap<Integer, ItemStack> glass = null;

            for (int state = 17; state > 0; state--) {
                glass = new HashMap<Integer, ItemStack>();
                if (state == 16) {
                    glass.put(27, glass_plane(0));
                    glass.put(36, glass_plane(0));
                    glass.put(45, glass_plane(0));
                } else if (state <= 15 && state >= 9) {
                    glass.put((-1 * state) + 44, glass_plane(0));
                    glass.put((-1 * state) + 62, glass_plane(0));
                } else if (state == 8) {
                    glass.put(35, glass_plane(0));
                    glass.put(44, glass_plane(0));
                    glass.put(53, glass_plane(0));
                } else if (state <= 7 && state >= 1) {
                    glass.put(state + 28, glass_plane(0));
                    glass.put(state + 46, glass_plane(0));
                } else if (state == 0) {
                    glass.put(27, glass_plane(0));
                    glass.put(36, glass_plane(0));
                    glass.put(45, glass_plane(0));
                }
                if (!glass.isEmpty())
                    glasses.put(state, glass);
            }

            return glasses;
        }

        public static HashMap<Integer, HashMap<Integer, ItemStack>> getThirdAnimation() {
            HashMap<Integer, HashMap<Integer, ItemStack>> glasses = new HashMap<Integer, HashMap<Integer, ItemStack>>();
            HashMap<Integer, ItemStack> glass = null;

            for (int state = 17; state > 0; state--) {
                glass = new HashMap<Integer, ItemStack>();
                if (state == 16 || state == 8 || state == 0) {
                    glass.put(31, glass_plane(0));
                    glass.put(49, glass_plane(0));
                } else if (state <= 15 && state >= 12) {
                    glass.put((-1 * state) + 47, glass_plane(0));
                    glass.put((-1 * state) + 65, glass_plane(0));
                    glass.put(state + 15, glass_plane(0));
                    glass.put(state + 33, glass_plane(0));
                } else if (state <= 11 && state >= 9) {
                    glass.put((-1 * state) + 39, glass_plane(0));
                    glass.put((-1 * state) + 57, glass_plane(0));
                    glass.put(state + 23, glass_plane(0));
                    glass.put(state + 41, glass_plane(0));
                } else if (state <= 7 && state >= 4) {
                    glass.put((-1 * state) + 39, glass_plane(0));
                    glass.put((-1 * state) + 57, glass_plane(0));
                    glass.put(state + 23, glass_plane(0));
                    glass.put(state + 41, glass_plane(0));
                } else if (state <= 3 && state >= 1) {
                    glass.put((-1 * state) + 31, glass_plane(0));
                    glass.put((-1 * state) + 49, glass_plane(0));
                    glass.put(state + 31, glass_plane(0));
                    glass.put(state + 49, glass_plane(0));
                }
                if (state == 12 || state == 4) {
                    glass.put(44, glass_plane(0));
                    glass.put(36, glass_plane(0));
                }
                if (!glass.isEmpty())
                    glasses.put(state, glass);
            }

            return glasses;
        }

        public static HashMap<Integer, HashMap<Integer, ItemStack>> getLastAnimation() {
            HashMap<Integer, HashMap<Integer, ItemStack>> glasses = new HashMap<Integer, HashMap<Integer, ItemStack>>();

            for (int state = 6; state > 0; state--) {
                int glassColor = state % 2 == 0 ? 0 : 15;

                glasses.put(state, getBorders(glassColor));
            }

            return glasses;
        }

        public static HashMap<Integer, HashMap<Integer, ItemStack>> getFailAnimation() {
            HashMap<Integer, HashMap<Integer, ItemStack>> glasses = new HashMap<Integer, HashMap<Integer, ItemStack>>();

            for (int state = 6; state > 0; state--) {
                int glassColor = state % 2 == 0 ? 14 : 15;

                glasses.put(state, getBorders(glassColor));
            }

            return glasses;
        }

        public static HashMap<Integer, ItemStack> getReset() {
            return getBorders(15);
        }

        public static HashMap<Integer, ItemStack> getBorders(int glassColor) {
            HashMap<Integer, ItemStack> glass = new HashMap<Integer, ItemStack>();

            for (int i = 27; i <= 35; i++)
                glass.put(i, glass_plane(glassColor));

            for (int i = 45; i <= 53; i++)
                glass.put(i, glass_plane(glassColor));

            glass.put(36, glass_plane(glassColor));
            glass.put(44, glass_plane(glassColor));

            return glass;
        }

    }

    // if ItemStack =:
    // null = item in doom
    // air = not upgraded
    // all the other = upgraded
    private static HashMap<UUID, ItemStack> item = new HashMap<UUID, ItemStack>();
    private static HashMap<UUID, Stars.Rarity> rarity = new HashMap<UUID, Stars.Rarity>();

    public static HashMap<UUID, Inventory> pInv = new HashMap<UUID, Inventory>();
    public static HashMap<UUID, AnimationStage> pStage = new HashMap<UUID, AnimationStage>();

    static boolean hasItem(UUID uuid) {
        return item.containsKey(uuid);
    }

    static void addItem(UUID uuid, ItemStack is) {
        if (hasItem(uuid)) {
            if (item.get(uuid).getType() == Material.AIR)
                item.put(uuid, is);
        } else
            item.put(uuid, is);
    }

    static void setRarity(UUID uuid, Stars.Rarity r) {
        if (!rarity.containsKey(uuid))
            rarity.put(uuid, r);
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e) {
        if (!StarUpgradeAnimation.hasItem(e.getPlayer().getUniqueId()))
            return;
        UUID uuid = e.getPlayer().getUniqueId();
        e.getPlayer().getInventory().addItem(Stars.upgrade(item.get(uuid)));
        item.remove(uuid);
        rarity.remove(uuid);
    }

    private static Sound SOUND = Sound.BLOCK_NOTE_PLING;

    public static void playFisrtAnimation(Inventory inv, Player p) {
        HashMap<Integer, HashMap<Integer, ItemStack>> states = Glasses.getFirstAnimation();

        pStage.put(p.getUniqueId(), AnimationStage.FIRST);

        int time = 0;
        for (int state : states.keySet()) {
            new BukkitRunnable() {
                @Override
                public void run() {
                    HashMap<Integer, ItemStack> glasses = states.get(state);

                    for (int slot : glasses.keySet())
                        inv.setItem(slot, glasses.get(slot));

                    p.playSound(p.getLocation(), SOUND, 1F, 1F);

                    pInv.put(p.getUniqueId(), inv);

                }
            }.runTaskLater(Emmber.getInstance(), 1 + time);

            new BukkitRunnable() {
                @Override
                public void run() {

                    resetInventory(inv);

                    pInv.put(p.getUniqueId(), inv);

                }
            }.runTaskLater(Emmber.getInstance(), 2 + time);

            time = time + 2;
        }

        new BukkitRunnable() {
            @Override
            public void run() {

                playSecondAnimation(inv, p);
                return;

            }
        }.runTaskLater(Emmber.getInstance(), time);

    }

    private static void playSecondAnimation(Inventory inv, Player p) {
        HashMap<Integer, HashMap<Integer, ItemStack>> states = Glasses.getSecondAnimation();

        pStage.put(p.getUniqueId(), AnimationStage.SECOND);

        int time = 0;
        for (int state : states.keySet()) {
            new BukkitRunnable() {
                @Override
                public void run() {
                    HashMap<Integer, ItemStack> glasses = states.get(state);

                    for (int slot : glasses.keySet())
                        inv.setItem(slot, glasses.get(slot));

                    p.playSound(p.getLocation(), SOUND, 1F, 1F);

                    pInv.put(p.getUniqueId(), inv);

                }
            }.runTaskLater(Emmber.getInstance(), 1 + time);

            new BukkitRunnable() {
                @Override
                public void run() {

                    resetInventory(inv);

                    pInv.put(p.getUniqueId(), inv);

                }
            }.runTaskLater(Emmber.getInstance(), 2 + time);

            time = time + 2;
        }

        new BukkitRunnable() {
            @Override
            public void run() {

                playThirdAnimation(inv, p);
                return;

            }
        }.runTaskLater(Emmber.getInstance(), time);

    }

    private static void playThirdAnimation(Inventory inv, Player p) {
        HashMap<Integer, HashMap<Integer, ItemStack>> states = Glasses.getThirdAnimation();

        pStage.put(p.getUniqueId(), AnimationStage.THIRD);

        int time = 0;
        for (int state : states.keySet()) {
            new BukkitRunnable() {
                @Override
                public void run() {
                    HashMap<Integer, ItemStack> glasses = states.get(state);

                    for (int slot : glasses.keySet())
                        inv.setItem(slot, glasses.get(slot));

                    p.playSound(p.getLocation(), SOUND, 1F, 1F);

                    pInv.put(p.getUniqueId(), inv);

                }
            }.runTaskLater(Emmber.getInstance(), 1 + time);

            new BukkitRunnable() {
                @Override
                public void run() {

                    resetInventory(inv);

                    pInv.put(p.getUniqueId(), inv);

                }
            }.runTaskLater(Emmber.getInstance(), 2 + time);

            time = time + 2;
        }

        new BukkitRunnable() {
            @Override
            public void run() {

                playLastAnimation(inv, p);
                return;

            }
        }.runTaskLater(Emmber.getInstance(), time);

    }

    private static void playLastAnimation(Inventory inv, Player p) {
        HashMap<Integer, HashMap<Integer, ItemStack>> states = Glasses.getLastAnimation();

        pStage.put(p.getUniqueId(), AnimationStage.LAST);

        int time = 0;
        for (int state : states.keySet()) {
            new BukkitRunnable() {
                @Override
                public void run() {
                    HashMap<Integer, ItemStack> glasses = states.get(state);

                    for (int slot : glasses.keySet())
                        inv.setItem(slot, glasses.get(slot));

                    p.playSound(p.getLocation(), SOUND, 1F, 1F);

                    pInv.put(p.getUniqueId(), inv);

                }
            }.runTaskLater(Emmber.getInstance(), 4 + time);

            new BukkitRunnable() {
                @Override
                public void run() {

                    resetInventory(inv);

                    pInv.put(p.getUniqueId(), inv);

                }
            }.runTaskLater(Emmber.getInstance(), 6 + time);

            time = time + 6;
        }

        new BukkitRunnable() {
            @Override
            public void run() {

                playFinalAnimation(inv, p);
                return;

            }
        }.runTaskLater(Emmber.getInstance(), time);

    }

    private static void playFinalAnimation(Inventory inv, Player p) {
        UUID uuid = p.getUniqueId();

        HashMap<Integer, ItemStack> border = Glasses.getReset();
        for (int slot : border.keySet())
            inv.setItem(slot, border.get(slot));

        ItemStack upgradedItem = item.get(uuid);

        if (upgradedItem == null || upgradedItem.getType() == Material.AIR) {
            playFailAnimation(inv, p);
            return;
        }

        inv.setItem(10, Stars.upgradeForAnimation(uuid, upgradedItem));

        pInv.put(p.getUniqueId(), inv);

        Stars.Rarity r = rarity.get(uuid);
        playFirework(p, r);

        border = Glasses.getBorders(r.glassPlaneColor);
        for (int slot : border.keySet())
            inv.setItem(slot, border.get(slot));

        new BukkitRunnable() {
            @Override
            public void run() {
                p.openInventory(StarInventory.get(p, inv.getItem(10)));
                item.remove(uuid);
                rarity.remove(uuid);
                pInv.remove(uuid);
                pStage.put(p.getUniqueId(), AnimationStage.OFF);
            }
        }.runTaskLater(Emmber.getInstance(), 40L);

        return;
    }

    private static void playFailAnimation(Inventory inv, Player p) {
        resetInventory(inv);
        HashMap<Integer, HashMap<Integer, ItemStack>> states = Glasses.getFailAnimation();

        pStage.put(p.getUniqueId(), AnimationStage.FAIL);

        int time = 0;
        for (int state : states.keySet()) {
            new BukkitRunnable() {
                @Override
                public void run() {
                    HashMap<Integer, ItemStack> glasses = states.get(state);

                    for (int slot : glasses.keySet())
                        inv.setItem(slot, glasses.get(slot));

                    p.playSound(p.getLocation(), Sound.BLOCK_ANVIL_BREAK, 1F, 1F);

                    pInv.put(p.getUniqueId(), inv);

                }
            }.runTaskLater(Emmber.getInstance(), 1 + time);

            new BukkitRunnable() {
                @Override
                public void run() {

                    resetInventory(inv);

                    pInv.put(p.getUniqueId(), inv);

                }
            }.runTaskLater(Emmber.getInstance(), 2 + time);

            time = time + 2;
        }

        new BukkitRunnable() {
            @Override
            public void run() {
                UUID uuid = p.getUniqueId();
                ItemStack upgradedItem = item.get(uuid);

                if (upgradedItem == null) {
                    inv.setItem(10, null);
                    p.sendMessage("not worked, IN doom");
                } else if (upgradedItem.getType() == Material.AIR) {
                    p.sendMessage("not worked, NOT in doom");
                }

                pStage.put(uuid, AnimationStage.OFF);
                item.remove(uuid);
                rarity.remove(uuid);
                pInv.remove(uuid);
                p.closeInventory();
                p.playSound(p.getLocation(), Sound.BLOCK_ANVIL_BREAK, 1F, 1F);
                playFailFirework(p);
                return;

            }
        }.runTaskLater(Emmber.getInstance(), time);
    }

    private static void resetInventory(Inventory inv) {
        HashMap<Integer, ItemStack> reset = Glasses.getReset();

        for (int slot : reset.keySet())
            inv.setItem(slot, reset.get(slot));

    }

    private static ItemStack glass_plane(int s) {
        ItemStack is = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) s);
        ItemMeta im = is.getItemMeta();
        im.setDisplayName(" ");
        im = ItemUtils.hideFlags(im);
        is.setItemMeta(im);
        return is;
    }

    private static void playFirework(Player p, Stars.Rarity r) {
        final Firework f = p.getLocation().getWorld().spawn(p.getLocation(), Firework.class);
        FireworkMeta fm = f.getFireworkMeta();
        FireworkEffect fe = FireworkEffect.builder().flicker(false).trail(true).with(Type.BALL).with(Type.BALL_LARGE)
                .withColor(r.fireWorkColor).withFade(r.fireWorkColor).build();
        fm.addEffect(fe);
        fm.setPower(127);
        f.setFireworkMeta(fm);
        new BukkitRunnable() {
            @Override
            public void run() {
                f.detonate();
            }
        }.runTaskLater(Emmber.getInstance(), 20L); // was 3
    }

    private static void playFailFirework(Player p) {
        final Firework f = p.getLocation().getWorld().spawn(p.getLocation(), Firework.class);
        FireworkMeta fm = f.getFireworkMeta();
        FireworkEffect fe = FireworkEffect.builder().flicker(false).trail(true).with(Type.BALL).with(Type.BALL_LARGE)
                .withColor(Color.RED).withFade(Color.BLACK).build();
        fm.addEffect(fe);
        fm.setPower(126 / 2);
        f.setFireworkMeta(fm);
        new BukkitRunnable() {
            @Override
            public void run() {
                f.detonate();
            }
        }.runTaskLater(Emmber.getInstance(), 3L);
    }

}
