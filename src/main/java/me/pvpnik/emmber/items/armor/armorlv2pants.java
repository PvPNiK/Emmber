package me.pvpnik.emmber.items.armor;

import me.pvpnik.emmber.Emmber;
import me.pvpnik.emmber.api.item.EmmberItem;
import org.bukkit.ChatColor;
import org.bukkit.Material;

public class armorlv2pants extends EmmberItem {

    public armorlv2pants() {
        super(Material.LEATHER_LEGGINGS, ChatColor.AQUA + Emmber.getInstance().localFileManager.messages.getMessage("items.armor.armor-lv2-pants"));

        setArmor(7);
        setDmg(5);
        setHp(7);
        setMinLevel(2);
        setPrice(56);

        reloadLore();
    }

}
