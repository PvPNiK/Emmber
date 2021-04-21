package me.pvpnik.emmber.items.startingItems;

import me.pvpnik.emmber.Emmber;
import me.pvpnik.emmber.api.item.EmmberItem;
import org.bukkit.ChatColor;
import org.bukkit.Material;

public class StartingLeggings extends EmmberItem {

    public StartingLeggings() {
        super(Material.LEATHER_LEGGINGS, ChatColor.AQUA + Emmber.getInstance().localFileManager.messages.getMessage("items.startingitems.leggings"));
        setPrice(47).setArmor(7).setDmg(7).setHp(7).setMinLevel(1).reloadLore();
    }

}