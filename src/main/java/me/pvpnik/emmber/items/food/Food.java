package me.pvpnik.emmber.items.food;

import me.pvpnik.emmber.Emmber;
import me.pvpnik.emmber.api.item.EmmberFood;
import org.bukkit.Material;

public class Food extends EmmberFood {

    public Food(Foods foods, String path) {
        super(Material.valueOf(foods.name()), Emmber.getInstance().localFileManager.messages.getMessage(path));
    }
}
