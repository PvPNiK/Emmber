package me.pvpnik.emmber.items.armor;

import me.pvpnik.emmber.Emmber;
import me.pvpnik.emmber.api.item.EmmberItem;
import org.bukkit.ChatColor;
import org.bukkit.Material;

public class armorlvl11helmet extends EmmberItem {

    public armorlvl11helmet() {
        super(Material.CHAINMAIL_HELMET, ChatColor.WHITE + Emmber.getInstance().localFileManager.messages.getMessage("items.armor.armor-lvl11-helmet"));

        setArmor(12);
        setDmg(2);
        setHp(4);
        setMinLevel(11);
        setPrice(120);

        reloadLore();
    }

}
