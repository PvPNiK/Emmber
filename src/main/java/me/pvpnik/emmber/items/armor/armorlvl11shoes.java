package me.pvpnik.emmber.items.armor;

import me.pvpnik.emmber.Emmber;
import me.pvpnik.emmber.api.item.EmmberItem;
import org.bukkit.ChatColor;
import org.bukkit.Material;

public class armorlvl11shoes extends EmmberItem {

    public armorlvl11shoes() {
        super(Material.CHAINMAIL_BOOTS, ChatColor.WHITE + Emmber.getInstance().localFileManager.messages.getMessage("items.armor.armor-lvl11-shoes"));

        setArmor(10);
        setDmg(2);
        setHp(3);
        setMinLevel(11);
        setPrice(100);

        reloadLore();
    }

}
