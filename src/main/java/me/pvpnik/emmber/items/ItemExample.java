package me.pvpnik.emmber.items;

import me.pvpnik.emmber.Emmber;
import me.pvpnik.emmber.api.item.EmmberItem;
import org.bukkit.ChatColor;
import org.bukkit.Material;

public class ItemExample extends EmmberItem {

    public ItemExample() {
        super(Material.LEATHER_HELMET, ChatColor.AQUA + Emmber.getInstance().localFileManager.messages.getMessage("items.startingitems.helmet"));

        setArmor(0);
        setDmg(0);
        setHp(0);
        setMinLevel(0);
        setPrice(0);

        reloadLore();
    }

}
