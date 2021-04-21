package me.pvpnik.emmber.items.armor;

import me.pvpnik.emmber.Emmber;
import me.pvpnik.emmber.api.item.EmmberItem;
import org.bukkit.ChatColor;
import org.bukkit.Material;

public class armorlv10boots extends EmmberItem {

    public armorlv10boots() {
        super(Material.LEATHER_BOOTS, ChatColor.AQUA + Emmber.getInstance().localFileManager.messages.getMessage("items.armor.armor-lv10-boots"));

        setArmor(9);
        setDmg(7);
        setHp(10);
        setMinLevel(10);
        setPrice(99);

        reloadLore();
    }

}