package me.pvpnik.emmber.items.weapon;

import me.pvpnik.emmber.Emmber;
import me.pvpnik.emmber.api.item.EmmberWeapon;
import me.pvpnik.emmber.api.item.ItemAtkSpeed;
import org.bukkit.ChatColor;
import org.bukkit.Material;

public class weaponregularlv20strong extends EmmberWeapon {
    public weaponregularlv20strong() {
        super(Material.DIAMOND_SWORD, ChatColor.DARK_AQUA + Emmber.getInstance().localFileManager.messages.getMessage("items.weapons.regular-lv20-strong"),
                ItemAtkSpeed.REGULAR); // SLOW, REGULAR, FAST

        setArmor(38);
        setDmg(1170);
        setHp(255);
        setMinLevel(20);
        setPrice(2364);
		
		setDurability((short) 67);

        reloadLore();

    }
}
