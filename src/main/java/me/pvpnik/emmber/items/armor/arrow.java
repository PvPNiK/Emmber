package me.pvpnik.emmber.items;

import me.pvpnik.emmber.Emmber;
import me.pvpnik.emmber.api.item.EmmberItem;
import org.bukkit.ChatColor;
import org.bukkit.Material;

public class arrow extends EmmberItem {

    public arrow() {
        super(Material.ARROW, ChatColor.WHITE + Emmber.getInstance().localFileManager.messages.getMessage("items.armor.arrow"));

        setMinLevel(3);
        setPrice(3);

        reloadLore();
    }

}
