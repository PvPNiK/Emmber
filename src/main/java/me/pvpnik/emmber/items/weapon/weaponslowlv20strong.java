package me.pvpnik.emmber.items.weapon;

import me.pvpnik.emmber.Emmber;
import me.pvpnik.emmber.api.item.EmmberWeapon;
import me.pvpnik.emmber.api.item.ItemAtkSpeed;
import org.bukkit.ChatColor;
import org.bukkit.Material;

public class weaponslowlv20strong extends EmmberWeapon {
    public weaponslowlv20strong() {
        super(Material.DIAMOND_SWORD, ChatColor.DARK_AQUA + Emmber.getInstance().localFileManager.messages.getMessage("items.weapons.slow-lv20-strong"),
                ItemAtkSpeed.SLOW); // SLOW, REGULAR, FAST

        setArmor(51);
        setDmg(1671);
        setHp(212);
        setMinLevel(20);
        setPrice(3412);
		
		setDurability((short) 144);

        reloadLore();

    }
}
