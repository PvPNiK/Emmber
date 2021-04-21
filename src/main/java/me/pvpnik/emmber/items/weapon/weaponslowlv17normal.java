package me.pvpnik.emmber.items.weapon;

import me.pvpnik.emmber.Emmber;
import me.pvpnik.emmber.api.item.EmmberWeapon;
import me.pvpnik.emmber.api.item.ItemAtkSpeed;
import org.bukkit.ChatColor;
import org.bukkit.Material;

public class weaponslowlv17normal extends EmmberWeapon {
    public weaponslowlv17normal() {
        super(Material.DIAMOND_SWORD, ChatColor.DARK_AQUA + Emmber.getInstance().localFileManager.messages.getMessage("items.weapons.slow-lv17-normal"),
                ItemAtkSpeed.SLOW); // SLOW, REGULAR, FAST

        setArmor(33);
        setDmg(1087);
        setHp(152);
        setMinLevel(17);
        setPrice(1548);
		
		setDurability((short) 149);

        reloadLore();

    }
}
