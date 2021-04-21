package me.pvpnik.emmber.items.weapon;

import me.pvpnik.emmber.Emmber;
import me.pvpnik.emmber.api.item.EmmberWeapon;
import me.pvpnik.emmber.api.item.ItemAtkSpeed;
import org.bukkit.ChatColor;
import org.bukkit.Material;

public class weaponslowlv10strong extends EmmberWeapon {
    public weaponslowlv10strong() {
        super(Material.DIAMOND_SWORD, ChatColor.DARK_AQUA + Emmber.getInstance().localFileManager.messages.getMessage("items.weapons.slow-lv10-strong"),
                ItemAtkSpeed.SLOW); // SLOW, REGULAR, FAST

        setArmor(17);
        setDmg(459);
        setHp(60);
        setMinLevel(10);
        setPrice(875);
		
		setDurability((short) 145);

        reloadLore();

    }
}
