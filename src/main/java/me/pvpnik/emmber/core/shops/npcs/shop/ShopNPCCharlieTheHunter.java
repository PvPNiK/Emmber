package me.pvpnik.emmber.core.shops.npcs.shop;

import me.mattstudios.mfgui.gui.guis.Gui;
import me.pvpnik.emmber.core.shops.npcs.ShopNPC;
import me.pvpnik.emmber.items.stores.weapon.emmberCharlie.*;
import org.bukkit.entity.Player;

public class ShopNPCCharlieTheHunter implements ShopNPC {

    private Gui gui;

    public ShopNPCCharlieTheHunter() {
        gui = new Gui(3, getInvTitle("essverbakery"));

        gui.setDefaultClickAction(event -> {
            event.setCancelled(true);
            buy(event.getCurrentItem(), (Player) event.getWhoClicked());
        });

        gui.setItem(2, 3, new WoodenKnifeLevelOne().asGuiItem());
        gui.setItem(2, 4, new WoodenKnifeLevelTwo().asGuiItem());
        gui.setItem(2, 5, new WoodenKnifeSharper().asGuiItem());
        gui.setItem(2, 6, new StarterBowLevelThree().asGuiItem());
        gui.setItem(2, 7, new EmmberCharlieArrow().asGuiItem());
    }

    @Override
    public void openShop(Player player) {
        gui.open(player);
    }

}
