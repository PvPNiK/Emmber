package me.pvpnik.emmber.items.armor;

import me.pvpnik.emmber.Emmber;
import me.pvpnik.emmber.api.item.EmmberItem;
import org.bukkit.ChatColor;
import org.bukkit.Material;

public class armorlv4pants extends EmmberItem {

    public armorlv4pants() {
        super(Material.LEATHER_LEGGINGS, ChatColor.AQUA + Emmber.getInstance().localFileManager.messages.getMessage("items.armor.armor-lv4-pants"));

        setArmor(9);
        setDmg(8);
        setHp(10);
        setMinLevel(4);
        setPrice(86);

        reloadLore();
    }

}
