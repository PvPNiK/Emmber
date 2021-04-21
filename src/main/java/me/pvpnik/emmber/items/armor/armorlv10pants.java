package me.pvpnik.emmber.items.armor;

import me.pvpnik.emmber.Emmber;
import me.pvpnik.emmber.api.item.EmmberItem;
import org.bukkit.ChatColor;
import org.bukkit.Material;

public class armorlv10pants extends EmmberItem {

    public armorlv10pants() {
        super(Material.LEATHER_LEGGINGS, ChatColor.AQUA + Emmber.getInstance().localFileManager.messages.getMessage("items.armor.armor-lv10-pants"));

        setArmor(18);
        setDmg(18);
        setHp(30);
        setMinLevel(10);
        setPrice(256);

        reloadLore();
    }

}
