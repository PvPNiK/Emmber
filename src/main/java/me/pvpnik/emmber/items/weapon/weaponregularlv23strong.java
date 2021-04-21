package me.pvpnik.emmber.items.weapon;

import me.pvpnik.emmber.Emmber;
import me.pvpnik.emmber.api.item.EmmberWeapon;
import me.pvpnik.emmber.api.item.ItemAtkSpeed;
import org.bukkit.ChatColor;
import org.bukkit.Material;

public class weaponregularlv23strong extends EmmberWeapon {
    public weaponregularlv23strong() {
        super(Material.DIAMOND_SWORD, ChatColor.DARK_AQUA + Emmber.getInstance().localFileManager.messages.getMessage("items.weapons.regular-lv23-strong"),
                ItemAtkSpeed.REGULAR); // SLOW, REGULAR, FAST

        setArmor(56);
        setDmg(1692);
        setHp(402);
        setMinLevel(23);
        setPrice(3415);
		
		setDurability((short) 122);

        reloadLore();

    }
}
