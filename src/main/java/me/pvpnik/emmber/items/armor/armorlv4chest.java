package me.pvpnik.emmber.items.armor;

import me.pvpnik.emmber.Emmber;
import me.pvpnik.emmber.api.item.EmmberItem;
import org.bukkit.ChatColor;
import org.bukkit.Material;

public class armorlv4chest extends EmmberItem {

    public armorlv4chest() {
        super(Material.LEATHER_CHESTPLATE, ChatColor.AQUA + Emmber.getInstance().localFileManager.messages.getMessage("items.armor.armor-lv4-chest"));

        setArmor(13);
        setDmg(11);
        setHp(13);
        setMinLevel(4);
        setPrice(120);

        reloadLore();
    }

}
