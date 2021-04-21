package me.pvpnik.emmber.core.shops.npcs.shop;

import me.mattstudios.mfgui.gui.guis.Gui;
import me.pvpnik.emmber.core.shops.npcs.ShopNPC;
import me.pvpnik.emmber.items.armor.*;
import org.bukkit.entity.Player;

public class ShopNPCLevel82 implements ShopNPC {

    private Gui gui;

    public ShopNPCLevel82() {
		// 3 - inventories rows(1-6), essverbakery is the path in messages.yml it not a full path and the code completes the full path,
		// the full path would be stores.names.essverbakery
        gui = new Gui(6, getInvTitle("level82"));

        gui.setDefaultClickAction(event -> {
            event.setCancelled(true);
            buy(event.getCurrentItem(), (Player) event.getWhoClicked());
        });
		
		// adding items to the shop
		// first number is the row, seconds number is the column and the last thing is your item to sell (class name)
        gui.setItem(5, 4, new armorlv8boots().asGuiItem());
        gui.setItem(3, 4, new armorlv8chest().asGuiItem());
        gui.setItem(2, 4, new armorlv8helmet().asGuiItem());
        gui.setItem(4, 4, new armorlv8pants().asGuiItem());
        gui.setItem(5, 6, new armorlv12boots().asGuiItem());
        gui.setItem(3, 6, new armorlv12chest().asGuiItem());
        gui.setItem(2, 6, new armorlv12helmet().asGuiItem());
        gui.setItem(4, 6, new armorlv12pants().asGuiItem());
    }

    @Override
    public void openShop(Player player) {
        gui.open(player);
    }

}
