package me.pvpnik.emmber.items;

import me.pvpnik.emmber.Emmber;
import me.pvpnik.emmber.utils.ItemUtils;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class Shield extends ItemStack {

    public Shield(byte shield) {
        super(Material.SHIELD, 1, shield);
        ItemMeta meta = ItemUtils.hideFlags(getItemMeta());
        meta.setDisplayName(Emmber.getInstance().localFileManager.messages.getMessage("items.shield." + shield));
        List<String> lore = new ArrayList<String>();
        lore.add(Emmber.getInstance().localFileManager.messages.getMessage("items.shield.clicktodefend.part1"));
        lore.add(Emmber.getInstance().localFileManager.messages.getMessage("items.shield.clicktodefend.part2"));
        meta.setLore(lore);
        setItemMeta(meta);
    }

}
