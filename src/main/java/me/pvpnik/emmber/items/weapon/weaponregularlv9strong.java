package me.pvpnik.emmber.items.weapon;

import me.pvpnik.emmber.Emmber;
import me.pvpnik.emmber.api.item.EmmberWeapon;
import me.pvpnik.emmber.api.item.ItemAtkSpeed;
import org.bukkit.ChatColor;
import org.bukkit.Material;

public class weaponregularlv9strong extends EmmberWeapon {
    public weaponregularlv9strong() {
        super(Material.DIAMOND_SWORD, ChatColor.DARK_AQUA + Emmber.getInstance().localFileManager.messages.getMessage("items.weapons.regular-lv9-strong"),
                ItemAtkSpeed.REGULAR); // SLOW, REGULAR, FAST

        setArmor(10);
        setDmg(284);
        setHp(66);
        setMinLevel(9);
        setPrice(573);
		
		setDurability((short) 124);

        reloadLore();

    }
}
