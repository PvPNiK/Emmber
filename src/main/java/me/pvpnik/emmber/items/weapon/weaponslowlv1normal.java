package me.pvpnik.emmber.items.weapon;

import me.pvpnik.emmber.Emmber;
import me.pvpnik.emmber.api.item.EmmberWeapon;
import me.pvpnik.emmber.api.item.ItemAtkSpeed;
import org.bukkit.ChatColor;
import org.bukkit.Material;

public class weaponslowlv1normal extends EmmberWeapon {
    public weaponslowlv1normal() {
        super(Material.DIAMOND_SWORD, ChatColor.DARK_AQUA + Emmber.getInstance().localFileManager.messages.getMessage("items.weapons.slow-lv1-normal"),
                ItemAtkSpeed.SLOW); // SLOW, REGULAR, FAST

        setArmor(6);
        setDmg(88);
        setHp(8);
        setMinLevel(1);
        setPrice(97);
		
		setDurability((short) 151);

        reloadLore();

    }
}
