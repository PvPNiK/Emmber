package me.pvpnik.emmber.items.stores.weapon.emmberCharlie;

import me.pvpnik.emmber.Emmber;
import me.pvpnik.emmber.api.item.EmmberWeapon;
import me.pvpnik.emmber.api.item.ItemAtkSpeed;
import org.bukkit.Material;

public class StarterBowLevelThree extends EmmberWeapon {
    public StarterBowLevelThree() {
        super(Material.BOW, Emmber.getInstance().localFileManager.messages.getMessage("items.stores.emmbercharlie.bow"), ItemAtkSpeed.REGULAR);
        setPrice(138).setMinLevel(3).setDmg(45).setArmor(4).setHp(37).reloadLore();
    }
}
