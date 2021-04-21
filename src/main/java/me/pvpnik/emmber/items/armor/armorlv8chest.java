package me.pvpnik.emmber.items.armor;

import me.pvpnik.emmber.Emmber;
import me.pvpnik.emmber.api.item.EmmberItem;
import org.bukkit.ChatColor;
import org.bukkit.Material;

public class armorlv8chest extends EmmberItem {

    public armorlv8chest() {
        super(Material.LEATHER_CHESTPLATE, ChatColor.AQUA + Emmber.getInstance().localFileManager.messages.getMessage("items.armor.armor-lv8-chest"));

        setArmor(22);
        setDmg(19);
        setHp(33);
        setMinLevel(8);
        setPrice(275);

        reloadLore();
    }

}
