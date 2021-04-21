package me.pvpnik.emmber.items.armor;

import me.pvpnik.emmber.Emmber;
import me.pvpnik.emmber.api.item.EmmberItem;
import org.bukkit.ChatColor;
import org.bukkit.Material;

public class armorlv2boots extends EmmberItem {

    public armorlv2boots() {
        super(Material.LEATHER_BOOTS, ChatColor.AQUA + Emmber.getInstance().localFileManager.messages.getMessage("items.armor.armor-lv2-boots"));

        setArmor(7);
        setDmg(5);
        setHp(7);
        setMinLevel(2);
        setPrice(22);

        reloadLore();
    }

}
