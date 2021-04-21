package me.pvpnik.emmber.items.weapon;

import me.pvpnik.emmber.Emmber;
import me.pvpnik.emmber.api.item.EmmberWeapon;
import me.pvpnik.emmber.api.item.ItemAtkSpeed;
import org.bukkit.ChatColor;
import org.bukkit.Material;

public class weaponfastlv1normal extends EmmberWeapon {
    public weaponfastlv1normal() {
        super(Material.DIAMOND_SWORD, ChatColor.DARK_AQUA + Emmber.getInstance().localFileManager.messages.getMessage("items.weapons.fast-lv1-normal"),
                ItemAtkSpeed.FAST); // SLOW, REGULAR, FAST

        setArmor(4);
        setDmg(41);
        setHp(10);
        setMinLevel(1);
        setPrice(84);
		
		setDurability((short) 135);

        reloadLore();

    }
}
