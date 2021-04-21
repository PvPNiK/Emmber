package me.pvpnik.emmber.core.stars;

import me.pvpnik.emmber.api.item.ItemLoreUtils;
import me.pvpnik.emmber.utils.HebrewUtil;
import me.pvpnik.emmber.utils.WeightedCollection;
import org.apache.commons.lang.StringUtils;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

public class Stars {

    public enum Rarity {
        NORMAL(ChatColor.WHITE, 0, multiplier(1.07, 1.03), Color.WHITE),
        RARE(ChatColor.AQUA, 3, multiplier(1.11, 1.07), Color.AQUA),
        EPIC(ChatColor.BLUE, 11, multiplier(1.14, 1.11), Color.BLUE),
        UNIQUE(ChatColor.LIGHT_PURPLE, 2, multiplier(1.18, 1.14), Color.PURPLE),
        LEGENDARY(ChatColor.GOLD, 4, multiplier(1.26, 1.19), Color.YELLOW);

        protected ChatColor chatColor;
        protected int glassPlaneColor;
        protected double multiplier;
        protected Color fireWorkColor;

        Rarity(ChatColor chatColor, int glassPlaneColor, double multiplier, Color fireWorkColor) {
            this.chatColor = chatColor;
            this.glassPlaneColor = glassPlaneColor;
            this.multiplier = multiplier;
            this.fireWorkColor = fireWorkColor;
        }

        private static double multiplier(double max, double min) {
            return ThreadLocalRandom.current().nextDouble(min, max);
        }
    }

    public static int getUpgradeCost(ItemStack is) {
        int itemLevel = ItemLoreUtils.getMinLvl(is);
        if (itemLevel == 0) return Integer.MAX_VALUE;
        if (getLevel(is) == -1) return Integer.MAX_VALUE;

        int starLevel = getLevel(is) == 0 ? 1 : getLevel(is) + 1;

        if (itemLevel <= 9) {
            return (int) Math.ceil(100 * starLevel);
        } else if (itemLevel >= 10 && itemLevel <= 15) {
            return (int) Math.ceil(170 * starLevel);
        } else if (itemLevel >= 16 && itemLevel <= 20) {
            return (int) Math.ceil(280 * starLevel);
        } else if (itemLevel >= 21 && itemLevel <= 25) {
            return (int) Math.ceil(420 * starLevel);
        } else if (itemLevel >= 26 && itemLevel <= 30) {
            return (int) Math.ceil(650 * starLevel);
        } else if (itemLevel >= 31 && itemLevel <= 35) {
            return (int) Math.ceil(910 * starLevel);
        } else if (itemLevel >= 36 && itemLevel <= 40) {
            return (int) Math.ceil(1320 * starLevel);
        } else if (itemLevel >= 41) {
            return (int) Math.ceil(1770 * starLevel);
        }

        return Integer.MAX_VALUE;
    }

    public static int getResetCost(ItemStack is) {
        int itemLevel = ItemLoreUtils.getMinLvl(is);
        if (itemLevel == 0) return Integer.MAX_VALUE;

        if (itemLevel <= 9) {
            return 400;
        } else if (itemLevel >= 10 && itemLevel <= 15) {
            return 650;
        } else if (itemLevel >= 16 && itemLevel <= 20) {
            return 1100;
        } else if (itemLevel >= 21 && itemLevel <= 25) {
            return 1600;
        } else if (itemLevel >= 26 && itemLevel <= 30) {
            return 2600;
        } else if (itemLevel >= 31 && itemLevel <= 35) {
            return 3600;
        } else if (itemLevel >= 36 && itemLevel <= 40) {
            return 5300;
        } else if (itemLevel >= 41) {
            return 7000;
        }

        return Integer.MAX_VALUE;
    }

    public static int getUpgradeChance(ItemStack is) {
        int starLevel = getLevel(is);
        if (starLevel == -1) return -1;

        return 100 + (-15 * (starLevel));
    }

    public static ItemStack upgradeForAnimation(UUID uuid, ItemStack is) {
        if (is == null) return is;
        if (is.getType() == Material.AIR) return is;

        int nextLevel = getLevel(is) + 1;
        if (nextLevel > 7) return is;
        double multiplier = 1;

        Rarity r;

        if (nextLevel >= 0 && nextLevel <= 3)
            r = getRarityLevelOneThree();
        else if (nextLevel >= 4 && nextLevel <= 5)
            r = getRarityLevelFourFive();
        else if (nextLevel == 6)
            r = getRarityLevelSix();
        else if (nextLevel == 7)
            r = getRarityLevelSeven();
        else
            return is;

        StarUpgradeAnimation.setRarity(uuid, r);

        multiplier = r.multiplier;/*Rarity.getMultiplier(r);*/

        is = addStarLevel(is, r);
        is = setPoints(is, (int) (getPoints(is) + (1000 * (multiplier - ((int) multiplier)))));
        is = ItemLoreUtils.setDamage(is, (int) Math.ceil((ItemLoreUtils.getDamage(is) == 0 ? 1 : ItemLoreUtils.getDamage(is)) * multiplier));
        is = ItemLoreUtils.setArmor(is, (int) Math.ceil((ItemLoreUtils.getHp(is) == 0 ? 1 : ItemLoreUtils.getHp(is)) * multiplier));
        is = ItemLoreUtils.setHp(is, (int) Math.ceil((ItemLoreUtils.getHp(is) == 0 ? 1 : ItemLoreUtils.getHp(is)) * multiplier));

        return is;
    }

    public static ItemStack upgrade(ItemStack is) {
        if (is == null) return is;
        if (is.getType() == Material.AIR) return is;

        int nextLevel = getLevel(is) + 1;
        if (nextLevel > 7) return is;
        double multiplier = 1;

        Rarity r;

        if (nextLevel >= 0 && nextLevel <= 3)
            r = getRarityLevelOneThree();
        else if (nextLevel >= 4 && nextLevel <= 5)
            r = getRarityLevelFourFive();
        else if (nextLevel == 6)
            r = getRarityLevelSix();
        else if (nextLevel == 7)
            r = getRarityLevelSeven();
        else
            return is;

        multiplier = r.multiplier;/*Rarity.getMultiplier(r)*/

        is = addStarLevel(is, r);
        is = setPoints(is, (int) (getPoints(is) + (1000 * (multiplier - ((int) multiplier)))));
        is = ItemLoreUtils.setDamage(is, (int) Math.ceil((ItemLoreUtils.getDamage(is) == 0 ? 1 : ItemLoreUtils.getDamage(is)) * multiplier));
        is = ItemLoreUtils.setArmor(is, (int) Math.ceil((ItemLoreUtils.getArmor(is) == 0 ? 1 : ItemLoreUtils.getArmor(is)) * multiplier));
        is = ItemLoreUtils.setHp(is, (int) Math.ceil((ItemLoreUtils.getHp(is) == 0 ? 1 : ItemLoreUtils.getHp(is)) * multiplier));

        return is;
    }

	public static ItemStack reset(ItemStack is) {
		int level = getLevel(is);
		if (level <= 0) {
		    return is;
        }
		is = removeStars(is);
		is = remvoePoints(is);
        double multiplier = Rarity.UNIQUE.multiplier;
		for (int i = 0; i < level; i++) {
            is = setPoints(is, (int) (getPoints(is) - (1000 * (multiplier - ((int) multiplier)))));
            is = ItemLoreUtils.setDamage(is, (int) Math.ceil((ItemLoreUtils.getDamage(is) == 0 ? 1 : ItemLoreUtils.getDamage(is)) / multiplier));
            is = ItemLoreUtils.setArmor(is, (int) Math.ceil((ItemLoreUtils.getArmor(is) == 0 ? 1 : ItemLoreUtils.getArmor(is)) / multiplier));
            is = ItemLoreUtils.setHp(is, (int) Math.ceil((ItemLoreUtils.getHp(is) == 0 ? 1 : ItemLoreUtils.getHp(is)) / multiplier));
        }
		return is;
	}

    public static void print(int x) {
        int[] arr = new int[x];
        for (int i = 0; i < x; i++) {
            arr[i] = (int) (Math.random() * ((999 - 100) + 1)) + 100;
            System.out.println(arr[i]);
        }
    }

    private static ItemStack addStarLevel(ItemStack is, Rarity r) {
        int level = getLevel(is);
        if (level >= 7 || level < 0)
            return is;
        if (!is.hasItemMeta()) return is;
        ItemMeta im = is.getItemMeta();

        List<String> lore = im.getLore();
        int pos = getStarPos(lore);

        if (pos == -1)
            lore.add(r.chatColor/*Rarity.getChatColor(r)*/ + "✫");
        else
            lore.set(pos, lore.get(pos) + " " + r.chatColor/*Rarity.getChatColor(r)*/ + "✫");

        im.setLore(lore);
        is.setItemMeta(im);
        return is;
    }

    public static ItemStack remvoePoints(ItemStack is) {
        ItemMeta im = is.getItemMeta();
        if (!hasStars(im)) return is;

        List<String> lore = new ArrayList<>();

        for (String s : im.getLore())
            if (!s.toLowerCase().contains(HebrewUtil.reverseHebrew("ציון כוכבים")))
                lore.add(s);

        im.setLore(lore);
        is.setItemMeta(im);
        return is;
    }

    public static ItemStack setPoints(ItemStack is, int points) {
        if (is == null)
            return is;
        if (is.getType() == Material.AIR)
            return is;
        if (!is.hasItemMeta())
            return is;
        ItemMeta meta = is.getItemMeta();
        List<String> lore = new ArrayList<>();
        if (!meta.hasLore()) {
            lore.add("" + ChatColor.GRAY + points + " " + ChatColor.WHITE + HebrewUtil.reverseHebrew("ציון כוכבים:"));
        } else {
            boolean add = true;
            for (String str : meta.getLore()) {
                if (str.toLowerCase().contains(HebrewUtil.reverseHebrew("ציון כוכבים"))) {
                    lore.add("" + ChatColor.GRAY + points + " " + ChatColor.WHITE + HebrewUtil.reverseHebrew("ציון כוכבים:"));
                    add = false;
                    continue;
                }
                lore.add(str);
            }
            if (add) {
                lore.add("" + ChatColor.GRAY + points + " " + ChatColor.WHITE + HebrewUtil.reverseHebrew("ציון כוכבים:"));
            }
        }
        meta.setLore(lore);
        is.setItemMeta(meta);
        return is;
    }

    public static double getPoints(ItemStack is) {
        if (is == null)
            return 0;
        if (is.getType() == Material.AIR)
            return 0;
        if (!is.hasItemMeta())
            return 0;

        ItemMeta meta = is.getItemMeta();
        if (!meta.hasLore())
            return 0;

        List<String> lore = meta.getLore();
        int pos = -1, i = 0;
        for (String str : lore) {
            if (ChatColor.stripColor(str).toLowerCase().contains(HebrewUtil.reverseHebrew("ציון כוכבים"))) {
                pos = i;
                break;
            }
            i++;
        }
        if (pos == -1)
            return 0;
        String str = ChatColor.stripColor(lore.get(pos)).split(" ")[0];
        return Double.parseDouble(str);
    }

    public static int getLevel(ItemStack is) {
        if (is == null) return -1;
        if (is.getType() == Material.AIR) return -1;
        ItemMeta im = is.getItemMeta();
        if (!hasStars(im)) return 0;

        List<String> lore = im.getLore();
        int pos = getStarPos(lore);
        if (pos == -1) return 0;
        return StringUtils.countMatches(ChatColor.stripColor(lore.get(pos)), "✫");
    }
	
    public static ItemStack removeStars(ItemStack is) {
        ItemMeta im = is.getItemMeta();
        if (!hasStars(im)) return is;

        List<String> lore = new ArrayList<>();

        for (String s : im.getLore())
            if (!ChatColor.stripColor(s).contains("✫"))
                lore.add(s);

        im.setLore(lore);
        is.setItemMeta(im);
        return is;
    }
	
    public static boolean hasStars(ItemMeta im) {
        if (im == null) return false;
        if (!im.hasLore()) return false;
        for (String s : im.getLore())
            if (ChatColor.stripColor(s).contains("✫"))
                return true;
        return false;
    }

    private static Rarity getRarityLevelOneThree() {
        WeightedCollection<Rarity> wc = new WeightedCollection<>();
        wc.add(850, Rarity.NORMAL);
        wc.add(100, Rarity.RARE);
        wc.add(35, Rarity.EPIC);
        wc.add(13, Rarity.UNIQUE);
        wc.add(2, Rarity.LEGENDARY);
        return wc.next();
    }

    private static Rarity getRarityLevelFourFive() {
        WeightedCollection<Rarity> wc = new WeightedCollection<>();
        wc.add(750, Rarity.NORMAL);
        wc.add(130, Rarity.RARE);
        wc.add(70, Rarity.EPIC);
        wc.add(37, Rarity.UNIQUE);
        wc.add(13, Rarity.LEGENDARY);
        return wc.next();
    }

    private static Rarity getRarityLevelSix() {
        WeightedCollection<Rarity> wc = new WeightedCollection<>();
        wc.add(400, Rarity.NORMAL);
        wc.add(200, Rarity.RARE);
        wc.add(120, Rarity.EPIC);
        wc.add(50, Rarity.UNIQUE);
        wc.add(30, Rarity.LEGENDARY);
        return wc.next();
    }

    private static Rarity getRarityLevelSeven() {
        WeightedCollection<Rarity> wc = new WeightedCollection<>();
        wc.add(300, Rarity.NORMAL);
        wc.add(250, Rarity.RARE);
        wc.add(200, Rarity.EPIC);
        wc.add(150, Rarity.UNIQUE);
        wc.add(100, Rarity.LEGENDARY);
        return wc.next();
    }

    private static int getStarPos(List<String> lore) {
        int pos = -1, i = 0;
        for (String str : lore) {
            if (ChatColor.stripColor(str).toLowerCase().contains("✫")) {
                pos = i;
                break;
            }
            i++;
        }
        return pos;
    }

}