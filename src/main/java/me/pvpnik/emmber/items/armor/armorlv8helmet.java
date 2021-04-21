package me.pvpnik.emmber.items.armor;

import me.pvpnik.emmber.Emmber;
import me.pvpnik.emmber.api.item.EmmberItem;
import org.bukkit.ChatColor;
import org.bukkit.Material;

public class armorlv8helmet extends EmmberItem {

    public armorlv8helmet() {
        super(Material.LEATHER_HELMET, ChatColor.AQUA + Emmber.getInstance().localFileManager.messages.getMessage("items.armor.armor-lv8-helmet"));

        setArmor(18);
        setDmg(16);
        setHp(50);
        setMinLevel(8);
        setPrice(165);

        reloadLore();
    }

}
