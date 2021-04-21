package me.pvpnik.emmber.items;

import me.pvpnik.emmber.Emmber;
import me.pvpnik.emmber.api.item.EmmberWeapon;
import me.pvpnik.emmber.api.item.ItemAtkSpeed;
import org.bukkit.ChatColor;
import org.bukkit.Material;

public class WeaponExample extends EmmberWeapon {
    public WeaponExample() {
        super(Material.LEATHER_HELMET, ChatColor.AQUA + Emmber.getInstance().localFileManager.messages.getMessage("items.startingitems.helmet"),
                ItemAtkSpeed.REGULAR); // SLOW, REGULAR, FAST

        setArmor(0);
        setDmg(0);
        setHp(0);
        setMinLevel(0);
        setPrice(0);

        reloadLore();
    }
}
