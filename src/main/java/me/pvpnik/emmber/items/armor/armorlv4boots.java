package me.pvpnik.emmber.items.armor;

import me.pvpnik.emmber.Emmber;
import me.pvpnik.emmber.api.item.EmmberItem;
import org.bukkit.ChatColor;
import org.bukkit.Material;

public class armorlv4boots extends EmmberItem {

    public armorlv4boots() {
        super(Material.LEATHER_BOOTS, ChatColor.AQUA + Emmber.getInstance().localFileManager.messages.getMessage("items.armor.armor-lv4-boots"));

        setArmor(5);
        setDmg(3);
        setHp(3);
        setMinLevel(4);
        setPrice(34);

        reloadLore();
    }

}
