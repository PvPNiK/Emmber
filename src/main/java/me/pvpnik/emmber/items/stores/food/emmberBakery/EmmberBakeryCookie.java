package me.pvpnik.emmber.items.stores.food.emmberBakery;

import me.pvpnik.emmber.Emmber;
import me.pvpnik.emmber.api.item.EmmberItem;
import org.bukkit.Material;

public class EmmberBakeryCookie extends EmmberItem {
    public EmmberBakeryCookie() {
        super(Material.COOKIE, Emmber.getInstance().localFileManager.messages.getMessage("items.food.cookie"));
        setPrice(1).reloadLore();
    }
}
