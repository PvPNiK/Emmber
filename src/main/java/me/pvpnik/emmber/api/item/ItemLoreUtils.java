package me.pvpnik.emmber.api.item;

import me.pvpnik.emmber.Emmber;
import me.pvpnik.emmber.utils.OUT;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class ItemLoreUtils {

    private ItemLoreUtils() {
    }

    public static int getDamage(ItemStack item) {
        String dmg = get(item, Emmber.getInstance().localFileManager.messages.getMessage("item.lore.dmg"));
        try {
            return Integer.parseInt(dmg) * item.getAmount();
        } catch (Exception e) {
            if (dmg != null) {
                OUT.toConsole(ChatColor.RED + "Dmg: " + dmg);
                e.printStackTrace();
            }
            return 0;
        }
    }

    public static ItemStack setDamage(ItemStack is, int dmg) {
        return set(is, Emmber.getInstance().localFileManager.messages.getMessage("item.lore.dmg"), dmg + "");
    }

    public static int getHp(ItemStack item) {
        String hp = get(item, Emmber.getInstance().localFileManager.messages.getMessage("item.lore.hp"));
        try {
            return Integer.parseInt(hp) * item.getAmount();
        } catch (Exception e) {
            if (hp != null) {
                OUT.toConsole(ChatColor.RED + "hp: " + hp);
                e.printStackTrace();
            }
            return 0;
        }
    }

    public static ItemStack setHp(ItemStack is, int hp) {
        return set(is, Emmber.getInstance().localFileManager.messages.getMessage("item.lore.hp"), hp + "");
    }

    public static int getArmor(ItemStack item) {
        String armor = get(item, Emmber.getInstance().localFileManager.messages.getMessage("item.lore.armor"));
        try {
            return Integer.parseInt(armor) * item.getAmount();
        } catch (Exception e) {
            if (armor != null) {
                OUT.toConsole(ChatColor.RED + "armor: " + armor);
                e.printStackTrace();
            }
            return 0;
        }
    }

    public static ItemStack setArmor(ItemStack is, int armor) {
        return set(is, Emmber.getInstance().localFileManager.messages.getMessage("item.lore.armor"), armor + "");
    }

    public static int getPrice(ItemStack item) {
        String price = get(item, Emmber.getInstance().localFileManager.messages.getMessage("item.lore.price"));
        try {
            return Integer.parseInt(price) * item.getAmount();
        } catch (Exception e) {
            if (price != null) {
                OUT.toConsole(ChatColor.RED + "price: " + price);
                e.printStackTrace();
            }
            return 0;
        }
    }

    public static ItemStack setPrice(ItemStack is, int price) {
        return set(is, Emmber.getInstance().localFileManager.messages.getMessage("item.lore.price"), price + "");
    }

    public static int getMinLvl(ItemStack item) {
        String minlvl = get(item, Emmber.getInstance().localFileManager.messages.getMessage("item.lore.minlvl"));
        try {
            return Integer.parseInt(minlvl);
        } catch (Exception e) {
            if (minlvl != null) {
                OUT.toConsole(ChatColor.RED + "minlvl: " + minlvl);
                e.printStackTrace();
            }
            return 0;
        }
    }

    public static ItemStack setMinLvl(ItemStack is, int minlvl) {
        return set(is, Emmber.getInstance().localFileManager.messages.getMessage("item.lore.minlvl"), minlvl + "");
    }

    public static ItemAtkSpeed getItemAttackSpeed(ItemStack itemStack) {
        String atkSpeedDisplayName = get(itemStack, Emmber.getInstance().localFileManager.messages.getMessage("item.lore.atkspeed"));
        try {
            return ItemAtkSpeed.getEnum(atkSpeedDisplayName);
        } catch (Exception e) {
            if (atkSpeedDisplayName != null) {
                OUT.toConsole(ChatColor.RED + "atkSpeedDisplayName: " + atkSpeedDisplayName);
                e.printStackTrace();
            }
            return ItemAtkSpeed.REGULAR;
        }
    }

    public static ItemStack setAttackSpeed(ItemStack is, ItemAtkSpeed itemAtkSpeed) {
        return set(is, Emmber.getInstance().localFileManager.messages.getMessage("item.lore.atkspeed"), itemAtkSpeed.getDisplayName());
    }

    /**
     * Returns the attribute value in String format
     *
     * @param item      - the item stack
     * @param attribute - the attribute, -> messages.yml > item.lore
     * @return String of the value
     */
    @Nullable
    public static String get(@Nonnull ItemStack item, String attribute) {
        if (item == null) {
            return null;
        }
        if (item.getType() == Material.AIR) {
            return null;
        }
        if (!item.hasItemMeta()) {
            return null;
        }
        ItemMeta meta = item.getItemMeta();
        if (!meta.hasLore()) {
            return null;
        }

        List<String> lore = meta.getLore();
        int pos = -1, i = 0;
        for (String str : lore) {
            if (ChatColor.stripColor(str).toLowerCase().contains(attribute.replace(":", ""))) {
                pos = i;
                break;
            }
            i++;
        }
        if (pos == -1) {
            return null;
        }

        String str = ChatColor.stripColor(lore.get(pos)).split(" ")[0];
        int dmg = Integer.parseInt(str) * item.getAmount();
        return str;
    }

    public static ItemStack set(ItemStack is, String attribute, String value) {
        if (is == null)
            return is;
        if (is.getType() == Material.AIR)
            return is;
        if (!is.hasItemMeta())
            return is;
        ItemMeta meta = is.getItemMeta();
        List<String> lore = new ArrayList<>();
        if (!meta.hasLore()) {
            lore.add("" + ChatColor.GRAY + value + " " + ChatColor.WHITE + attribute);
        } else {
            boolean add = true;
            for (String str : meta.getLore()) {
                if (str.toLowerCase().contains(attribute.replace(":", ""))) {
                    lore.add("" + ChatColor.GRAY + value + " " + ChatColor.WHITE + attribute);
                    add = false;
                    continue;
                }
                lore.add(str);
            }
            if (add) {
                lore.add("" + ChatColor.GRAY + value + " " + ChatColor.WHITE + attribute);
            }
        }
        meta.setLore(lore);
        is.setItemMeta(meta);
        return is;
    }

}
