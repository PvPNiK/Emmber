package me.pvpnik.emmber.items.weapon;

import me.pvpnik.emmber.Emmber;
import me.pvpnik.emmber.api.item.EmmberWeapon;
import me.pvpnik.emmber.api.item.ItemAtkSpeed;
import org.bukkit.ChatColor;
import org.bukkit.Material;

public class weaponregularlv13strong extends EmmberWeapon {
    public weaponregularlv13strong() {
        super(Material.DIAMOND_SWORD, ChatColor.DARK_AQUA + Emmber.getInstance().localFileManager.messages.getMessage("items.weapons.regular-lv13-strong"),
                ItemAtkSpeed.REGULAR); // SLOW, REGULAR, FAST

        setArmor(16);
        setDmg(485);
        setHp(125);
        setMinLevel(13);
        setPrice(978);
		
		setDurability((short) 123);

        reloadLore();

    }
}
