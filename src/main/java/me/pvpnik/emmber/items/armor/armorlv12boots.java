package me.pvpnik.emmber.items.armor;

import me.pvpnik.emmber.Emmber;
import me.pvpnik.emmber.api.item.EmmberItem;
import org.bukkit.ChatColor;
import org.bukkit.Material;

public class armorlv12boots extends EmmberItem {

    public armorlv12boots() {
        super(Material.CHAINMAIL_BOOTS, ChatColor.AQUA + Emmber.getInstance().localFileManager.messages.getMessage("items.armor.armor-lv12-boots"));

        setArmor(11);
        setDmg(7);
        setHp(13);
        setMinLevel(12);
        setPrice(123);

        reloadLore();
    }

}
