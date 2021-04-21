package me.pvpnik.emmber.items.food;

import me.pvpnik.emmber.Emmber;
import me.pvpnik.emmber.api.item.EmmberItem;
import org.bukkit.Material;

public class CookedBeef extends EmmberItem {
    public CookedBeef() {
        super(Material.COOKED_BEEF, Emmber.getInstance().localFileManager.messages.getMessage("items.food.cookedbeef"));
        setAmount(9);
    }
}
