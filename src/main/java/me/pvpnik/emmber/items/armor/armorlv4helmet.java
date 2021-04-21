package me.pvpnik.emmber.items.armor;

import me.pvpnik.emmber.Emmber;
import me.pvpnik.emmber.api.item.EmmberItem;
import org.bukkit.ChatColor;
import org.bukkit.Material;

public class armorlv4helmet extends EmmberItem {

    public armorlv4helmet() {
        super(Material.LEATHER_HELMET, ChatColor.AQUA + Emmber.getInstance().localFileManager.messages.getMessage("items.armor.armor-lv4-helmet"));

        setArmor(11);
        setDmg(9);
        setHp(20);
        setMinLevel(4);
        setPrice(72);

        reloadLore();
    }

}
