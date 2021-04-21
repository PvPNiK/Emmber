package me.pvpnik.emmber.items.gui;

import me.pvpnik.emmber.utils.ItemUtils;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class InvisibleItem extends ItemStack {

    public InvisibleItem(String insert) {
        super(Material.DIAMOND_SPADE, 1, (short) 111);
        ItemMeta itemMeta = ItemUtils.hideFlags(getItemMeta());
        itemMeta.setDisplayName(insert);
        setItemMeta(itemMeta);
    }

    public InvisibleItem() {
        this(" ");
    }

}
