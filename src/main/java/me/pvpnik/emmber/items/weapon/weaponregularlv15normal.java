package me.pvpnik.emmber.items.weapon;

import me.pvpnik.emmber.Emmber;
import me.pvpnik.emmber.api.item.EmmberWeapon;
import me.pvpnik.emmber.api.item.ItemAtkSpeed;
import org.bukkit.ChatColor;
import org.bukkit.Material;

public class weaponregularlv15normal extends EmmberWeapon {
    public weaponregularlv15normal() {
        super(Material.DIAMOND_SWORD, ChatColor.DARK_AQUA + Emmber.getInstance().localFileManager.messages.getMessage("items.weapons.regular-lv15-normal"),
                ItemAtkSpeed.REGULAR); // SLOW, REGULAR, FAST

        setArmor(30);
        setDmg(788);
        setHp(169);
        setMinLevel(15);
        setPrice(1407);
		
		setDurability((short) 128);

        reloadLore();

    }
}
