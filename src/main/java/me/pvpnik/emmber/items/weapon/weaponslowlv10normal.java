package me.pvpnik.emmber.items.weapon;

import me.pvpnik.emmber.Emmber;
import me.pvpnik.emmber.api.item.EmmberWeapon;
import me.pvpnik.emmber.api.item.ItemAtkSpeed;
import org.bukkit.ChatColor;
import org.bukkit.Material;

public class weaponslowlv10normal extends EmmberWeapon {
    public weaponslowlv10normal() {
        super(Material.DIAMOND_SWORD, ChatColor.DARK_AQUA + Emmber.getInstance().localFileManager.messages.getMessage("items.weapons.slow-lv10-normal"),
                ItemAtkSpeed.SLOW); // SLOW, REGULAR, FAST

        setArmor(15);
        setDmg(384);
        setHp(55);
        setMinLevel(10);
        setPrice(547);
		
		setDurability((short) 150);

        reloadLore();

    }
}
