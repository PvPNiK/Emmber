package me.pvpnik.emmber.items.stores.food.emmberBakery;

import me.pvpnik.emmber.Emmber;
import me.pvpnik.emmber.api.item.EmmberItem;
import org.bukkit.Material;

public class EmmberBakeryPumpkinPie extends EmmberItem {
    public EmmberBakeryPumpkinPie() {
        super(Material.PUMPKIN_PIE, Emmber.getInstance().localFileManager.messages.getMessage("items.food.pumpkinpie"));
        setPrice(3).reloadLore();
    }
}
