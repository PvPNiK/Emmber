package me.pvpnik.emmber.utils;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ItemFactory {

    private ItemFactory() {}

    public static String serializeItemStack(ItemStack is) {
        if (is == null)
            return null;

        String serializedItemStack = new String();

        String isType = String.valueOf(is.getType().getId());
        serializedItemStack += "t@" + isType;

        if (is.getDurability() != 0) {
            String isDurability = String.valueOf(is.getDurability());
            serializedItemStack += "#d@" + isDurability;
        }

        if (is.getAmount() != 1) {
            String isAmount = String.valueOf(is.getAmount());
            serializedItemStack += "#a@" + isAmount;
        }

        Map<Enchantment, Integer> isEnch = is.getEnchantments();
        if (isEnch.size() > 0) {
            for (Map.Entry<Enchantment, Integer> ench : isEnch.entrySet()) {
                serializedItemStack += "#e@" + ench.getKey().getId() + "@" + ench.getValue();
            }
        }

        if (is.hasItemMeta()) {
            ItemMeta im = is.getItemMeta();

            if (im.hasDisplayName())
                serializedItemStack += "#n@" + im.getDisplayName().replaceAll("�", "&");

            if (im.getLore() != null)
                for (String l : im.getLore())
                    serializedItemStack += "#l@" + l.replaceAll("�", "&");
        }

        return serializedItemStack;
    }

    @SuppressWarnings("deprecation")
    public static ItemStack deserializeItemStack(String itemString) {

        if (itemString == null)
            return null;

        ItemStack is = null;
        ItemMeta im = null;
        List<String> lore = new ArrayList<String>();

        String[] serializedItemStack = itemString.split("#");

        for (String itemInfo : serializedItemStack) {
            String[] itemAttribute = itemInfo.split("@");
            if (itemAttribute[0].equals("t")) {
                is = new ItemStack(Material.getMaterial(Integer.valueOf(itemAttribute[1])));
                im = is.getItemMeta();
            } else if (itemAttribute[0].equals("d"))
                is.setDurability(Short.valueOf(itemAttribute[1]));
            else if (itemAttribute[0].equals("a"))
                is.setAmount(Integer.valueOf(itemAttribute[1]));
            else if (itemAttribute[0].equals("e"))
                im.addEnchant(Enchantment.getById(Integer.valueOf(itemAttribute[1])), Integer.valueOf(itemAttribute[2]),
                        true);
            else if (itemAttribute[0].equals("n"))
                im.setDisplayName(ChatColor.translateAlternateColorCodes('&', itemAttribute[1]));
            else if (itemAttribute[0].equals("l"))
                lore.add(ChatColor.translateAlternateColorCodes('&', itemAttribute[1]));
        }

        im.setLore(lore);
        im = ItemUtils.hideFlags(im);
        im.spigot().setUnbreakable(true);
        is.setItemMeta(im);

        return is;
    }

}
