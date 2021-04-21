package me.pvpnik.emmber.items.armor;

import me.pvpnik.emmber.Emmber;
import me.pvpnik.emmber.api.item.EmmberItem;
import org.bukkit.ChatColor;
import org.bukkit.Material;

public class armorlv8boots extends EmmberItem {

    public armorlv8boots() {
        super(Material.LEATHER_BOOTS, ChatColor.AQUA + Emmber.getInstance().localFileManager.messages.getMessage("items.armor.armor-lv8-boots"));

        setArmor(7);
        setDmg(5);
        setHp(8);
        setMinLevel(8);
        setPrice(77);

        reloadLore();
    }

}
