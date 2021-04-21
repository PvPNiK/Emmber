package me.pvpnik.emmber.items.stores.weapon.emmberCharlie;

import me.pvpnik.emmber.Emmber;
import me.pvpnik.emmber.api.item.EmmberWeapon;
import me.pvpnik.emmber.api.item.ItemAtkSpeed;
import org.bukkit.Material;

public class WoodenKnifeSharper extends EmmberWeapon {
    public WoodenKnifeSharper() {
        super(Material.DIAMOND_SWORD, Emmber.getInstance().localFileManager.messages.getMessage("items.stores.emmbercharlie.two"), ItemAtkSpeed.FAST);
        setDurability((short) 135);
        setPrice(160).setMinLevel(4).setDmg(79).setArmor(6).setHp(21).reloadLore();
    }
}
