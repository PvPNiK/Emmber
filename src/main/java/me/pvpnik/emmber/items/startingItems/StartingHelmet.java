package me.pvpnik.emmber.items.startingItems;

import me.pvpnik.emmber.Emmber;
import me.pvpnik.emmber.api.item.EmmberItem;
import org.bukkit.ChatColor;
import org.bukkit.Material;

public class StartingHelmet extends EmmberItem {

    public StartingHelmet() {
        super(Material.LEATHER_HELMET, ChatColor.AQUA + Emmber.getInstance().localFileManager.messages.getMessage("items.startingitems.helmet"));
        setPrice(37).setArmor(7).setDmg(7).setHp(7).setMinLevel(1).reloadLore();
    }

}