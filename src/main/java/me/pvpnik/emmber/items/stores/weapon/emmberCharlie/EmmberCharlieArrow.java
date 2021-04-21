package me.pvpnik.emmber.items.stores.weapon.emmberCharlie;

import me.pvpnik.emmber.Emmber;
import me.pvpnik.emmber.api.item.EmmberItem;
import org.bukkit.Material;

public class EmmberCharlieArrow extends EmmberItem {
    public EmmberCharlieArrow() {
        super(Material.ARROW, Emmber.getInstance().localFileManager.messages.getMessage("items.stores.emmbercharlie.arrow"));
        setAmount(3);
        setPrice(15).reloadLore();
    }
}
