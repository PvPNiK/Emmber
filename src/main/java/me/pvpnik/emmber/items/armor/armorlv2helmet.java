package me.pvpnik.emmber.items.armor;

import me.pvpnik.emmber.Emmber;
import me.pvpnik.emmber.api.item.EmmberItem;
import org.bukkit.ChatColor;
import org.bukkit.Material;

public class armorlv2helmet extends EmmberItem {

    public armorlv2helmet() {
        super(Material.LEATHER_HELMET, ChatColor.AQUA + Emmber.getInstance().localFileManager.messages.getMessage("items.armor.armor-lv2-helmet"));

        setArmor(9);
        setDmg(6);
        setHp(16);
        setMinLevel(2);
        setPrice(47);

        reloadLore();
    }

}
