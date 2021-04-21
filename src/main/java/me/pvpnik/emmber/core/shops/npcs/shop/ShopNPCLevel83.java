package me.pvpnik.emmber.core.shops.npcs.shop;

import me.mattstudios.mfgui.gui.guis.Gui;
import me.pvpnik.emmber.core.shops.npcs.ShopNPC;
import me.pvpnik.emmber.items.armorlvl15bow;
import me.pvpnik.emmber.items.armorlvl5bow;
import me.pvpnik.emmber.items.arrow;
import me.pvpnik.emmber.items.weapon.weaponslowlv6strong;
import org.bukkit.entity.Player;

public class ShopNPCLevel83 implements ShopNPC {

    private Gui gui;

    public ShopNPCLevel83() {
		// 3 - inventories rows(1-6), essverbakery is the path in messages.yml it not a full path and the code completes the full path,
		// the full path would be stores.names.essverbakery
        gui = new Gui(3, getInvTitle("level83"));

        gui.setDefaultClickAction(event -> {
            event.setCancelled(true);
            buy(event.getCurrentItem(), (Player) event.getWhoClicked());
        });
		
		// adding items to the shop
		// first number is the row, seconds number is the column and the last thing is your item to sell (class name)
        gui.setItem(2, 3, new armorlvl5bow().asGuiItem());
        gui.setItem(2, 4, new armorlvl15bow().asGuiItem());
        gui.setItem(2, 6, new arrow().asGuiItem());
        gui.setItem(2, 7, new weaponslowlv6strong().asGuiItem());
    }

    @Override
    public void openShop(Player player) {
        gui.open(player);
    }

}
