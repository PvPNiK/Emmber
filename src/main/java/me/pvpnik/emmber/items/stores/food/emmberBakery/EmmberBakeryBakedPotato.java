package me.pvpnik.emmber.items.stores.food.emmberBakery;

import me.pvpnik.emmber.Emmber;
import me.pvpnik.emmber.api.item.EmmberItem;
import org.bukkit.Material;

public class EmmberBakeryBakedPotato extends EmmberItem {
    public EmmberBakeryBakedPotato() {
        super(Material.BAKED_POTATO, Emmber.getInstance().localFileManager.messages.getMessage("items.food.bakedpotato"));
        setPrice(1).reloadLore();
    }
}
