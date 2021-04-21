package me.pvpnik.emmber.items.armor;

import me.pvpnik.emmber.Emmber;
import me.pvpnik.emmber.api.item.EmmberItem;
import org.bukkit.ChatColor;
import org.bukkit.Material;

public class armorlv10helmet extends EmmberItem {

    public armorlv10helmet() {
        super(Material.LEATHER_HELMET, ChatColor.AQUA + Emmber.getInstance().localFileManager.messages.getMessage("items.armor.armor-lv10-helmet"));

        setArmor(22);
        setDmg(21);
        setHp(61);
        setMinLevel(10);
        setPrice(213);

        reloadLore();
    }

}
