package me.pvpnik.emmber.items.startingItems;

import me.pvpnik.emmber.Emmber;
import me.pvpnik.emmber.api.item.EmmberItem;
import org.bukkit.ChatColor;
import org.bukkit.Material;

public class StartingChestplate extends EmmberItem {

    public StartingChestplate() {
        super(Material.LEATHER_CHESTPLATE, ChatColor.AQUA + Emmber.getInstance().localFileManager.messages.getMessage("items.startingitems.chestplate"));
        setPrice(67).setArmor(7).setDmg(7).setHp(7).setMinLevel(1).reloadLore();
    }

}