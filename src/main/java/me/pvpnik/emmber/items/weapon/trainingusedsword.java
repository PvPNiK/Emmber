package me.pvpnik.emmber.items.weapon;

import me.pvpnik.emmber.Emmber;
import me.pvpnik.emmber.api.item.EmmberWeapon;
import me.pvpnik.emmber.api.item.ItemAtkSpeed;
import org.bukkit.ChatColor;
import org.bukkit.Material;

public class trainingusedsword extends EmmberWeapon {
    public trainingusedsword() {
        super(Material.DIAMOND_SWORD, ChatColor.GREEN + Emmber.getInstance().localFileManager.messages.getMessage("items.weapons.training_used_sword"),
                ItemAtkSpeed.REGULAR); // SLOW, REGULAR, FAST

        setArmor(2);
        setDmg(7);
        setHp(10);
        setMinLevel(1);
        setPrice(5);
		
		setDurability((short) 100);

        reloadLore();

    }
}
