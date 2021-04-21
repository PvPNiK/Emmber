package me.pvpnik.emmber.core.shops.npcs.shop;

import me.mattstudios.mfgui.gui.guis.Gui;
import me.pvpnik.emmber.core.shops.npcs.ShopNPC;
import me.pvpnik.emmber.items.weapon.*;
import org.bukkit.entity.Player;

public class ShopNPCWeapons implements ShopNPC {

    private Gui gui;

    public ShopNPCWeapons() {
		// 3 - inventories rows(1-6), essverbakery is the path in messages.yml it not a full path and the code completes the full path,
		// the full path would be stores.names.essverbakery
        gui = new Gui(3, getInvTitle("weapons"));

        gui.setDefaultClickAction(event -> {
            event.setCancelled(true);
            buy(event.getCurrentItem(), (Player) event.getWhoClicked());
        });
		
		// adding items to the shop
		// first number is the row, seconds number is the column and the last thing is your item to sell (class name)
        gui.setItem(2, 2, new weaponregularlv1normal().asGuiItem());
        gui.setItem(2, 3, new weaponregularlv5strong().asGuiItem());
        gui.setItem(2, 5, new weaponfastlv1normal().asGuiItem());
        gui.setItem(2, 6, new weaponfastlv4normal().asGuiItem());
        //gui.setItem(2, 8, new slowlv1normal().asGuiItem());
    }

    @Override
    public void openShop(Player player) {
        gui.open(player);
    }

}
