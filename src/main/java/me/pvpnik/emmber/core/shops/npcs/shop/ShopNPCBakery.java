package me.pvpnik.emmber.core.shops.npcs.shop;

import me.mattstudios.mfgui.gui.guis.Gui;
import me.pvpnik.emmber.core.shops.npcs.ShopNPC;
import me.pvpnik.emmber.items.food.Food;
import me.pvpnik.emmber.items.food.Foods;
import me.pvpnik.emmber.items.stores.weapon.emmberCharlie.*;
import org.bukkit.entity.Player;

public class ShopNPCBakery implements ShopNPC {

    private Gui gui;

    public ShopNPCBakery() {
		// 3 - inventories rows(1-6), essverbakery is the path in messages.yml it not a full path and the code completes the full path,
		// the full path would be stores.names.essverbakery
        gui = new Gui(3, getInvTitle("bakery"));

        gui.setDefaultClickAction(event -> {
            event.setCancelled(true);
            buy(event.getCurrentItem(), (Player) event.getWhoClicked());
        });
		
		// adding items to the shop
		// first number is the row, seconds number is the column and the last thing is your item to sell (class name)
        gui.setItem(2, 3, new Food(Foods.APPLE, "items.food.apple").setPrice(1).reloadLore().asGuiItem());
        gui.setItem(2, 4, new Food(Foods.PUMPKIN_PIE, "items.food.pumpkinpie").setPrice(3).reloadLore().asGuiItem());
        gui.setItem(2, 5, new Food(Foods.BREAD, "items.food.bread").setPrice(2).reloadLore().asGuiItem());
        gui.setItem(2, 6, new Food(Foods.COOKIE, "items.food.cookie").setPrice(1).reloadLore().asGuiItem());
    }

    @Override
    public void openShop(Player player) {
        gui.open(player);
    }

}
