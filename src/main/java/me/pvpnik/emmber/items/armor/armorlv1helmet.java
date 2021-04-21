package me.pvpnik.emmber.items.armor;

import me.pvpnik.emmber.Emmber;
import me.pvpnik.emmber.api.item.EmmberItem;
import org.bukkit.ChatColor;
import org.bukkit.Material;

public class armorlv1helmet extends EmmberItem {

    public armorlv1helmet() {
        super(Material.LEATHER_HELMET, ChatColor.AQUA + Emmber.getInstance().localFileManager.messages.getMessage("items.armor.armor-lv1-helmet"));

        setArmor(8);
        setDmg(4);
        setHp(9);
        setMinLevel(1);
        setPrice(38);

        reloadLore();
    }

}
