package me.pvpnik.emmber.items.weapon;

import me.pvpnik.emmber.Emmber;
import me.pvpnik.emmber.api.item.EmmberWeapon;
import me.pvpnik.emmber.api.item.ItemAtkSpeed;
import org.bukkit.ChatColor;
import org.bukkit.Material;

public class weaponfastlv17strong extends EmmberWeapon {
    public weaponfastlv17strong() {
        super(Material.DIAMOND_SWORD, ChatColor.DARK_AQUA + Emmber.getInstance().localFileManager.messages.getMessage("items.weapons.fast-lv17-strong"),
                ItemAtkSpeed.FAST); // SLOW, REGULAR, FAST

        setArmor(27);
        setDmg(559);
        setHp(171);
        setMinLevel(17);
        setPrice(1671);
		
		setDurability((short) 139);

        reloadLore();

    }
}
