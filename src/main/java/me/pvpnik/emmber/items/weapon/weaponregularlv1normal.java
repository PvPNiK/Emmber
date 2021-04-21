package me.pvpnik.emmber.items.weapon;

import me.pvpnik.emmber.Emmber;
import me.pvpnik.emmber.api.item.EmmberWeapon;
import me.pvpnik.emmber.api.item.ItemAtkSpeed;
import org.bukkit.ChatColor;
import org.bukkit.Material;

public class weaponregularlv1normal extends EmmberWeapon {
    public weaponregularlv1normal() {
        super(Material.DIAMOND_SWORD, ChatColor.DARK_AQUA + Emmber.getInstance().localFileManager.messages.getMessage("items.weapons.regular-lv1-normal"),
                ItemAtkSpeed.REGULAR); // SLOW, REGULAR, FAST

        setArmor(5);
        setDmg(68);
        setHp(9);
        setMinLevel(1);
        setPrice(95);
		
		setDurability((short) 130);

        reloadLore();

    }
}
