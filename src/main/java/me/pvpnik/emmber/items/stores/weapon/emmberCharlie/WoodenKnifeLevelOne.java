package me.pvpnik.emmber.items.stores.weapon.emmberCharlie;

import me.pvpnik.emmber.Emmber;
import me.pvpnik.emmber.api.item.EmmberWeapon;
import me.pvpnik.emmber.api.item.ItemAtkSpeed;
import org.bukkit.Material;

public class WoodenKnifeLevelOne extends EmmberWeapon {
    public WoodenKnifeLevelOne() {
        super(Material.DIAMOND_SWORD, Emmber.getInstance().localFileManager.messages.getMessage("items.stores.emmbercharlie.one"), ItemAtkSpeed.FAST);
        setDurability((short) 135);
        setPrice(84).setMinLevel(1).setDmg(41).setArmor(4).setHp(10).reloadLore();
    }
}
