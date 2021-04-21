package me.pvpnik.emmber.items.armor;

import me.pvpnik.emmber.Emmber;
import me.pvpnik.emmber.api.item.EmmberItem;
import org.bukkit.ChatColor;
import org.bukkit.Material;

public class armorlvl11leggings extends EmmberItem {

    public armorlvl11leggings() {
        super(Material.CHAINMAIL_LEGGINGS, ChatColor.WHITE + Emmber.getInstance().localFileManager.messages.getMessage("items.armor.armor-lvl11-leggings"));

        setArmor(23);
        setDmg(3);
        setHp(4);
        setMinLevel(11);
        setPrice(155);

        reloadLore();
    }

}
