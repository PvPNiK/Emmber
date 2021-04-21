package me.pvpnik.emmber.core.shops.npcs.shop;

import me.mattstudios.mfgui.gui.guis.Gui;
import me.pvpnik.emmber.core.shops.npcs.ShopNPC;
import me.pvpnik.emmber.items.food.CookedBeef;
import me.pvpnik.emmber.items.food.Food;
import me.pvpnik.emmber.items.food.Foods;
import me.pvpnik.emmber.items.stores.weapon.emmberCharlie.*;
import org.bukkit.entity.Player;

public class ShopNPCAvi implements ShopNPC {

    private Gui gui;

    public ShopNPCAvi() {
		// 3 - inventories rows(1-6), essverbakery is the path in messages.yml it not a full path and the code completes the full path,
		// the full path would be stores.names.essverbakery
        gui = new Gui(3, getInvTitle("royalshop"));

        gui.setDefaultClickAction(event -> {
            event.setCancelled(true);
            buy(event.getCurrentItem(), (Player) event.getWhoClicked());
        });
		
		// adding items to the shop
		// first number is the row, seconds number is the column and the last thing is your item to sell (class name)
        gui.setItem(2, 4, new Food(Foods.COOKED_BEEF, "items.food.cookedbeef").setPrice(4).reloadLore().asGuiItem());
        gui.setItem(2, 5, new Food(Foods.BAKED_POTATO, "items.food.bakedpotato").setPrice(2).reloadLore().asGuiItem());
    }

    @Override
    public void openShop(Player player) {
        gui.open(player);
    }

}
