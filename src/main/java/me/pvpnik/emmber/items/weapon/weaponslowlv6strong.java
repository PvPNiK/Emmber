package me.pvpnik.emmber.items.weapon;

import me.pvpnik.emmber.Emmber;
import me.pvpnik.emmber.api.item.EmmberWeapon;
import me.pvpnik.emmber.api.item.ItemAtkSpeed;
import org.bukkit.ChatColor;
import org.bukkit.Material;

public class weaponslowlv6strong extends EmmberWeapon {
    public weaponslowlv6strong() {
        super(Material.DIAMOND_SWORD, ChatColor.DARK_AQUA + Emmber.getInstance().localFileManager.messages.getMessage("items.weapons.slow-lv6-strong"),
                ItemAtkSpeed.SLOW); // SLOW, REGULAR, FAST

        setArmor(9);
        setDmg(243);
        setHp(31);
        setMinLevel(6);
        setPrice(512);
		
		setDurability((short) 15);

        reloadLore();

    }
}
