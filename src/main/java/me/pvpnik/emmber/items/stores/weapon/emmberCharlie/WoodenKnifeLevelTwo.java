package me.pvpnik.emmber.items.stores.weapon.emmberCharlie;

import me.pvpnik.emmber.Emmber;
import me.pvpnik.emmber.api.item.EmmberWeapon;
import me.pvpnik.emmber.api.item.ItemAtkSpeed;
import org.bukkit.Material;

public class WoodenKnifeLevelTwo extends EmmberWeapon {
    public WoodenKnifeLevelTwo() {
        super(Material.DIAMOND_SWORD, Emmber.getInstance().localFileManager.messages.getMessage("items.stores.emmbercharlie.one"), ItemAtkSpeed.FAST);
        setDurability((short) 135);
        setPrice(104).setMinLevel(2).setDmg(52).setArmor(5).setHp(15).reloadLore();
    }
}
