package me.pvpnik.emmber.items.weapon;

import me.pvpnik.emmber.Emmber;
import me.pvpnik.emmber.api.item.EmmberWeapon;
import me.pvpnik.emmber.api.item.ItemAtkSpeed;
import org.bukkit.ChatColor;
import org.bukkit.Material;

public class weaponregularlv5strong extends EmmberWeapon {
    public weaponregularlv5strong() {
        super(Material.DIAMOND_SWORD, ChatColor.DARK_AQUA + Emmber.getInstance().localFileManager.messages.getMessage("items.weapons.regular-lv5-strong"),
                ItemAtkSpeed.REGULAR); // SLOW, REGULAR, FAST

        setArmor(8);
        setDmg(158);
        setHp(22);
        setMinLevel(5);
        setPrice(296);
		
		setDurability((short) 64);

        reloadLore();

    }
}
