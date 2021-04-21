package me.pvpnik.emmber.items.weapon;

import me.pvpnik.emmber.Emmber;
import me.pvpnik.emmber.api.item.EmmberWeapon;
import me.pvpnik.emmber.api.item.ItemAtkSpeed;
import org.bukkit.ChatColor;
import org.bukkit.Material;

public class weaponfastlv10normal extends EmmberWeapon {
    public weaponfastlv10normal() {
        super(Material.DIAMOND_SWORD, ChatColor.DARK_AQUA + Emmber.getInstance().localFileManager.messages.getMessage("items.weapons.fast-lv10-normal"),
                ItemAtkSpeed.FAST); // SLOW, REGULAR, FAST

        setArmor(11);
        setDmg(178);
        setHp(67);
        setMinLevel(10);
        setPrice(472);
		
		setDurability((short) 134);

        reloadLore();

    }
}
