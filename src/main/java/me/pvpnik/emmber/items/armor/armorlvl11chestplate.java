package me.pvpnik.emmber.items.armor;

import me.pvpnik.emmber.Emmber;
import me.pvpnik.emmber.api.item.EmmberItem;
import org.bukkit.ChatColor;
import org.bukkit.Material;

public class armorlvl11chestplate extends EmmberItem {

    public armorlvl11chestplate() {
        super(Material.CHAINMAIL_CHESTPLATE, ChatColor.WHITE + Emmber.getInstance().localFileManager.messages.getMessage("items.armor.armor-lvl11-chestplate"));

        setArmor(8);
        setDmg(34);
        setHp(43);
        setMinLevel(11);
        setPrice(300);

        reloadLore();
    }

}
