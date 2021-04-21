package me.pvpnik.emmber.items.armor;

import me.pvpnik.emmber.Emmber;
import me.pvpnik.emmber.api.item.EmmberItem;
import org.bukkit.ChatColor;
import org.bukkit.Material;

public class armorlv12helmet extends EmmberItem {

    public armorlv12helmet() {
        super(Material.CHAINMAIL_HELMET, ChatColor.AQUA + Emmber.getInstance().localFileManager.messages.getMessage("items.armor.armor-lv12-helmet"));

        setArmor(28);
        setDmg(26);
        setHp(75);
        setMinLevel(12);
        setPrice(264);

        reloadLore();
    }

}
