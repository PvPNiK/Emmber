package me.pvpnik.emmber.items.stores.food.emmberBakery;

import me.pvpnik.emmber.Emmber;
import me.pvpnik.emmber.api.item.EmmberItem;
import org.bukkit.Material;

public class EmmberBakeryBread extends EmmberItem {
    public EmmberBakeryBread() {
        super(Material.BREAD, Emmber.getInstance().localFileManager.messages.getMessage("items.food.bread"));
        setPrice(1).reloadLore();
    }
}
