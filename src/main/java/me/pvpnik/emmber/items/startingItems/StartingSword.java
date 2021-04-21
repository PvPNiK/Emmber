package me.pvpnik.emmber.items.startingItems;

import me.pvpnik.emmber.Emmber;
import me.pvpnik.emmber.api.item.EmmberWeapon;
import me.pvpnik.emmber.api.item.ItemAtkSpeed;
import org.bukkit.ChatColor;
import org.bukkit.Material;

public class StartingSword extends EmmberWeapon {

    public StartingSword() {
        super(Material.WOOD_SWORD, ChatColor.AQUA + Emmber.getInstance().localFileManager.messages.getMessage("items.startingitems.weapon"), ItemAtkSpeed.REGULAR);
        setPrice(7).setArmor(7).setDmg(67).setHp(7).setMinLevel(1).reloadLore();
    }

}