package me.pvpnik.emmber.items.weapon;

import me.pvpnik.emmber.Emmber;
import me.pvpnik.emmber.api.item.EmmberWeapon;
import me.pvpnik.emmber.api.item.ItemAtkSpeed;
import org.bukkit.ChatColor;
import org.bukkit.Material;

public class weaponfastlv6strong extends EmmberWeapon {
    public weaponfastlv6strong() {
        super(Material.DIAMOND_SWORD, ChatColor.DARK_AQUA + Emmber.getInstance().localFileManager.messages.getMessage("items.weapons.fast-lv6-strong"),
                ItemAtkSpeed.FAST); // SLOW, REGULAR, FAST

        setArmor(8);
        setDmg(109);
        setHp(27);
        setMinLevel(6);
        setPrice(375);
		
		setDurability((short) 87);

        reloadLore();

    }
}
