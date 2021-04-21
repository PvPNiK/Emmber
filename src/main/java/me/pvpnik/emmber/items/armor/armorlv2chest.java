package me.pvpnik.emmber.items.armor;

import me.pvpnik.emmber.Emmber;
import me.pvpnik.emmber.api.item.EmmberItem;
import org.bukkit.ChatColor;
import org.bukkit.Material;

public class armorlv2chest extends EmmberItem {

    public armorlv2chest() {
        super(Material.LEATHER_CHESTPLATE, ChatColor.AQUA + Emmber.getInstance().localFileManager.messages.getMessage("items.armor.armor-lv2-chest"));

        setArmor(11);
        setDmg(7);
        setHp(10);
        setMinLevel(2);
        setPrice(77);

        reloadLore();
    }

}
