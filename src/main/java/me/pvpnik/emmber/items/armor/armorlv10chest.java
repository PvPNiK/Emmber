package me.pvpnik.emmber.items.armor;

import me.pvpnik.emmber.Emmber;
import me.pvpnik.emmber.api.item.EmmberItem;
import org.bukkit.ChatColor;
import org.bukkit.Material;

public class armorlv10chest extends EmmberItem {

    public armorlv10chest() {
        super(Material.LEATHER_CHESTPLATE, ChatColor.AQUA + Emmber.getInstance().localFileManager.messages.getMessage("items.armor.armor-lv10-chest"));

        setArmor(27);
        setDmg(24);
        setHp(40);
        setMinLevel(10);
        setPrice(355);

        reloadLore();
    }

}
