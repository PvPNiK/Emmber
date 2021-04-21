package me.pvpnik.emmber.items.weapon;

import me.pvpnik.emmber.Emmber;
import me.pvpnik.emmber.api.item.EmmberWeapon;
import me.pvpnik.emmber.api.item.ItemAtkSpeed;
import org.bukkit.ChatColor;
import org.bukkit.Material;

public class weaponfastlv4normal extends EmmberWeapon {
    public weaponfastlv4normal() {
        super(Material.DIAMOND_SWORD, ChatColor.DARK_AQUA + Emmber.getInstance().localFileManager.messages.getMessage("items.weapons.fast-lv4-normal"),
                ItemAtkSpeed.FAST); // SLOW, REGULAR, FAST

        setArmor(6);
        setDmg(79);
        setHp(21);
        setMinLevel(4);
        setPrice(160);
		
		setDurability((short) 135);

        reloadLore();

    }
}
