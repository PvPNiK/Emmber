package me.pvpnik.emmber.items;

import me.pvpnik.emmber.Emmber;
import me.pvpnik.emmber.api.item.EmmberItem;
import org.bukkit.ChatColor;
import org.bukkit.Material;

public class armorlvl15bow extends EmmberItem {

    public armorlvl15bow() {
        super(Material.BOW, ChatColor.GREEN + Emmber.getInstance().localFileManager.messages.getMessage("items.armor.armor-lvl15-bow"));

        setDmg(50);
        setMinLevel(15);
        setPrice(650);

        reloadLore();
    }

}
