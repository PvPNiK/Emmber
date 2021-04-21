package me.pvpnik.emmber.items.weapon;

import me.pvpnik.emmber.Emmber;
import me.pvpnik.emmber.api.item.EmmberWeapon;
import me.pvpnik.emmber.api.item.ItemAtkSpeed;
import org.bukkit.ChatColor;
import org.bukkit.Material;

public class weaponfastlv17normal extends EmmberWeapon {
    public weaponfastlv17normal() {
        super(Material.DIAMOND_SWORD, ChatColor.DARK_AQUA + Emmber.getInstance().localFileManager.messages.getMessage("items.weapons.fast-lv17-normal"),
                ItemAtkSpeed.FAST); // SLOW, REGULAR, FAST

        setArmor(24);
        setDmg(504);
        setHp(186);
        setMinLevel(17);
        setPrice(1337);
		
		setDurability((short) 133);

        reloadLore();

    }
}
