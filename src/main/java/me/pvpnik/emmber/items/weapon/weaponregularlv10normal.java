package me.pvpnik.emmber.items.weapon;

import me.pvpnik.emmber.Emmber;
import me.pvpnik.emmber.api.item.EmmberWeapon;
import me.pvpnik.emmber.api.item.ItemAtkSpeed;
import org.bukkit.ChatColor;
import org.bukkit.Material;

public class weaponregularlv10normal extends EmmberWeapon {
    public weaponregularlv10normal() {
        super(Material.DIAMOND_SWORD, ChatColor.DARK_AQUA + Emmber.getInstance().localFileManager.messages.getMessage("items.weapons.regular-lv10-normal"),
                ItemAtkSpeed.REGULAR); // SLOW, REGULAR, FAST

        setArmor(14);
        setDmg(278);
        setHp(61);
        setMinLevel(10);
        setPrice(497);
		
		setDurability((short) 129);

        reloadLore();

    }
}
