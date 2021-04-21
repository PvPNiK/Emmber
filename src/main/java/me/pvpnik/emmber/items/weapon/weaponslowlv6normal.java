package me.pvpnik.emmber.items.weapon;

import me.pvpnik.emmber.Emmber;
import me.pvpnik.emmber.api.item.EmmberWeapon;
import me.pvpnik.emmber.api.item.ItemAtkSpeed;
import org.bukkit.ChatColor;
import org.bukkit.Material;

public class weaponslowlv6normal extends EmmberWeapon {
    public weaponslowlv6normal() {
        super(Material.DIAMOND_SWORD, ChatColor.DARK_AQUA + Emmber.getInstance().localFileManager.messages.getMessage("items.weapons.slow-lv6-normal"),
                ItemAtkSpeed.SLOW); // SLOW, REGULAR, FAST

        setArmor(79);
        setDmg(199);
        setHp(20);
        setMinLevel(6);
        setPrice(216);
		
		setDurability((short) 79);

        reloadLore();

    }
}
