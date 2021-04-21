package me.pvpnik.emmber.utils;

import me.mattstudios.mfgui.gui.components.ItemBuilder;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ItemUtils {

    private ItemUtils() {}

    public static ItemMeta hideFlags(ItemMeta im) {
        im.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
        im.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        im.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        im.addItemFlags(ItemFlag.HIDE_DESTROYS);
        im.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);
        im.addItemFlags(ItemFlag.HIDE_PLACED_ON);
        im.spigot().setUnbreakable(true);
        return im;
    }

    public static ItemStack hideFlags(ItemStack is) {
        ItemMeta im = hideFlags(is.getItemMeta());
        is.setItemMeta(im);
        return is;
    }

}
