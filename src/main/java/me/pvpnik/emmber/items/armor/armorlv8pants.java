package me.pvpnik.emmber.items.armor;

import me.pvpnik.emmber.Emmber;
import me.pvpnik.emmber.api.item.EmmberItem;
import org.bukkit.ChatColor;
import org.bukkit.Material;

public class armorlv8pants extends EmmberItem {

    public armorlv8pants() {
        super(Material.LEATHER_LEGGINGS, ChatColor.AQUA + Emmber.getInstance().localFileManager.messages.getMessage("items.armor.armor-lv8-pants"));

        setArmor(14);
        setDmg(14);
        setHp(25);
        setMinLevel(8);
        setPrice(198);

        reloadLore();
    }

}
