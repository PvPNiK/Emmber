package me.pvpnik.emmber.items.armor;

import me.pvpnik.emmber.Emmber;
import me.pvpnik.emmber.api.item.EmmberItem;
import org.bukkit.ChatColor;
import org.bukkit.Material;

public class armorlv12pants extends EmmberItem {

    public armorlv12pants() {
        super(Material.CHAINMAIL_LEGGINGS, ChatColor.AQUA + Emmber.getInstance().localFileManager.messages.getMessage("items.armor.armor-lv12-pants"));

        setArmor(23);
        setDmg(22);
        setHp(38);
        setMinLevel(12);
        setPrice(317);

        reloadLore();
    }

}
