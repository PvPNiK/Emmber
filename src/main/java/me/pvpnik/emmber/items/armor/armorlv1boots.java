package me.pvpnik.emmber.items.armor;

import me.pvpnik.emmber.Emmber;
import me.pvpnik.emmber.api.item.EmmberItem;
import org.bukkit.ChatColor;
import org.bukkit.Material;

public class armorlv1boots extends EmmberItem {

    public armorlv1boots() {
        super(Material.LEATHER_BOOTS, ChatColor.AQUA + Emmber.getInstance().localFileManager.messages.getMessage("items.armor.armor-lv1-boots"));

        setArmor(2);
        setDmg(2);
        setHp(1);
        setMinLevel(1);
        setPrice(17);

        reloadLore();
    }

}
