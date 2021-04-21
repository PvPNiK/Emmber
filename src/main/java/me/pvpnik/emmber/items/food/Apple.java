package me.pvpnik.emmber.items.food;

import me.pvpnik.emmber.Emmber;
import me.pvpnik.emmber.api.item.EmmberItem;
import org.bukkit.Material;

public class Apple extends EmmberItem {

    public Apple() {
        super(Material.APPLE, Emmber.getInstance().localFileManager.messages.getMessage("items.food.apple"));
        setAmount(12);
    }

}
