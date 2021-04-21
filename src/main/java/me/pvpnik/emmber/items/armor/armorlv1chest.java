package me.pvpnik.emmber.items.armor;

import me.pvpnik.emmber.Emmber;
import me.pvpnik.emmber.api.item.EmmberItem;
import org.bukkit.ChatColor;
import org.bukkit.Material;

public class armorlv1chest extends EmmberItem {

    public armorlv1chest() {
        super(Material.LEATHER_CHESTPLATE, ChatColor.AQUA + Emmber.getInstance().localFileManager.messages.getMessage("items.armor.armor-lv1-chest"));

        setArmor(9);
        setDmg(5);
        setHp(6);
        setMinLevel(1);
        setPrice(62);

        reloadLore();
    }

}
