package me.pvpnik.emmber.items;

import me.pvpnik.emmber.Emmber;
import me.pvpnik.emmber.api.item.EmmberItem;
import org.bukkit.ChatColor;
import org.bukkit.Material;

public class armorlvl5bow extends EmmberItem {

    public armorlvl5bow() {
        super(Material.BOW, ChatColor.GREEN + Emmber.getInstance().localFileManager.messages.getMessage("items.armor.armor-lvl5-bow"));

        setDmg(20);
        setMinLevel(5);
        setPrice(90);

        reloadLore();
    }

}
