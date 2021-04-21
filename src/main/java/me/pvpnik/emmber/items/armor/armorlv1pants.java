package me.pvpnik.emmber.items.armor;

import me.pvpnik.emmber.Emmber;
import me.pvpnik.emmber.api.item.EmmberItem;
import org.bukkit.ChatColor;
import org.bukkit.Material;

public class armorlv1pants extends EmmberItem {

    public armorlv1pants() {
        super(Material.LEATHER_LEGGINGS, ChatColor.AQUA + Emmber.getInstance().localFileManager.messages.getMessage("items.armor.armor-lv1-pants"));

        setArmor(6);
        setDmg(4);
        setHp(5);
        setMinLevel(1);
        setPrice(45);

        reloadLore();
    }

}
