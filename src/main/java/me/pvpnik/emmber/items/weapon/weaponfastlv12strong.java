package me.pvpnik.emmber.items.weapon;

import me.pvpnik.emmber.Emmber;
import me.pvpnik.emmber.api.item.EmmberWeapon;
import me.pvpnik.emmber.api.item.ItemAtkSpeed;
import org.bukkit.ChatColor;
import org.bukkit.Material;

public class weaponfastlv12strong extends EmmberWeapon {
    public weaponfastlv12strong() {
        super(Material.DIAMOND_SWORD, ChatColor.DARK_AQUA + Emmber.getInstance().localFileManager.messages.getMessage("items.weapons.fast-lv12-strong"),
                ItemAtkSpeed.FAST); // SLOW, REGULAR, FAST

        setArmor(15);
        setDmg(243);
        setHp(74);
        setMinLevel(12);
        setPrice(731);
		
		setDurability((short) 140);

        reloadLore();

    }
}
