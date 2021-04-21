package me.pvpnik.emmber.items.armor;

import me.pvpnik.emmber.Emmber;
import me.pvpnik.emmber.api.item.EmmberItem;
import org.bukkit.ChatColor;
import org.bukkit.Material;

public class armorlv12chest extends EmmberItem {

    public armorlv12chest() {
        super(Material.CHAINMAIL_CHESTPLATE, ChatColor.AQUA + Emmber.getInstance().localFileManager.messages.getMessage("items.armor.armor-lv12-chest"));

        setArmor(34);
        setDmg(31);
        setHp(50);
        setMinLevel(12);
        setPrice(440);

        reloadLore();
    }

}
