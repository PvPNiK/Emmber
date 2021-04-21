package me.pvpnik.emmber.core.shops.npcs.shop;

import me.mattstudios.mfgui.gui.guis.Gui;
import me.pvpnik.emmber.core.shops.npcs.ShopNPC;
import me.pvpnik.emmber.items.armor.*;
import org.bukkit.entity.Player;

public class ShopNPCArmor implements ShopNPC {

    private Gui gui;

    public ShopNPCArmor() {
		// 3 - inventories rows(1-6), essverbakery is the path in messages.yml it not a full path and the code completes the full path,
		// the full path would be stores.names.essverbakery
        gui = new Gui(6, getInvTitle("armor"));

        gui.setDefaultClickAction(event -> {
            event.setCancelled(true);
            buy(event.getCurrentItem(), (Player) event.getWhoClicked());
        });
		
		// adding items to the shop
		// first number is the row, seconds number is the column and the last thing is your item to sell (class name)
        gui.setItem(5, 3, new armorlv1boots().asGuiItem());
        gui.setItem(3, 3, new armorlv1chest().asGuiItem());
        gui.setItem(2, 3, new armorlv1helmet().asGuiItem());
        gui.setItem(4, 3, new armorlv1pants().asGuiItem());
        gui.setItem(5, 5, new armorlv2boots().asGuiItem());
        gui.setItem(3, 5, new armorlv2chest().asGuiItem());
        gui.setItem(2, 5, new armorlv2helmet().asGuiItem());
        gui.setItem(4, 5, new armorlv2pants().asGuiItem());
        gui.setItem(5, 7, new armorlv4boots().asGuiItem());
        gui.setItem(3, 7, new armorlv4chest().asGuiItem());
        gui.setItem(2, 7, new armorlv4helmet().asGuiItem());
        gui.setItem(4, 7, new armorlv4pants().asGuiItem());
    }

    @Override
    public void openShop(Player player) {
        gui.open(player);
    }

}
