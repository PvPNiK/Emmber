package me.pvpnik.emmber.items.startingItems;

import me.pvpnik.emmber.Emmber;
import me.pvpnik.emmber.api.item.EmmberItem;
import org.bukkit.ChatColor;
import org.bukkit.Material;

public class StartingBoots extends EmmberItem {

    public StartingBoots() {
        super(Material.LEATHER_BOOTS, ChatColor.AQUA + Emmber.getInstance().localFileManager.messages.getMessage("items.startingitems.boots"));
        setPrice(17).setArmor(7).setDmg(7).setHp(7).setMinLevel(1).reloadLore();
    }

}